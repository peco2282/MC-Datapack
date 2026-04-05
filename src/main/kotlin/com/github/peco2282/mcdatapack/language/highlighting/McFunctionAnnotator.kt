package com.github.peco2282.mcdatapack.language.highlighting

import com.github.peco2282.mcdatapack.language.psi.*
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.tree.IElementType

class McFunctionAnnotator : Annotator {
  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    if (element is McFunctionNamespacedId) {
      annotateNamespacedId(element, holder)
      checkFunctionFileExists(element, holder)
    }
    
    if (element is McFunctionCommand) {
      annotateCommand(element, holder)
    }

    if (element is McFunctionCommandLine) {
      validateCommandArguments(element, holder)
    }

    if (element is McFunctionKeyword) {
      annotateKeyword(element, holder)
    }

    val type = element.node.elementType
    if (type in McFunctionSyntaxHighlighter.FLOW_KEYWORD_TOKENS || type in McFunctionSyntaxHighlighter.SUB_COMMAND_TOKENS) {
      val attributes = when {
        type in McFunctionSyntaxHighlighter.FLOW_KEYWORD_TOKENS -> McFunctionSyntaxHighlighter.FLOW_KEYWORD
        type in McFunctionSyntaxHighlighter.SUB_COMMAND_TOKENS -> McFunctionSyntaxHighlighter.SUB_COMMAND
        else -> null
      }
      if (attributes != null) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.textRange)
          .textAttributes(attributes)
          .create()
      }
    }

    if (element is McFunctionCoordinate) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(element.textRange)
        .textAttributes(McFunctionSyntaxHighlighter.COORDINATE)
        .create()
      return
    }

    if (type == McFunctionTypes.ARGUMENT_TOKEN || type == McFunctionTypes.COMMAND_TOKEN || type == McFunctionTypes.STRING_TOKEN) {
      annotateJsonValue(element, holder)

      // キーの判定: 次に ':' か '=' が来るか
      if (isJsonKey(element)) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.textRange)
          .textAttributes(McFunctionSyntaxHighlighter.JSON_KEY)
          .create()
      } else if (type == McFunctionTypes.ARGUMENT_TOKEN && isItemNameBeforeJson(element)) {
        // minecraft:stone[...] の minecraft:stone 部分をハイライト
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.textRange)
          .textAttributes(McFunctionSyntaxHighlighter.JSON_KEY)
          .create()
      }
    }
  }

  // コマンドトークンとそれに対応するデータパス・拡張子のマッピング
  private data class NamespacedIdContext(val subDir: String, val extension: String, val label: String)

  private val NAMESPACED_ID_CONTEXTS = mapOf(
    McFunctionTypes.FUNCTION_TOKEN to NamespacedIdContext("functions", ".mcfunction", "Function"),
    McFunctionTypes.ADVANCEMENT_TOKEN to NamespacedIdContext("advancements", ".json", "Advancement"),
    McFunctionTypes.RECIPE_TOKEN to NamespacedIdContext("recipes", ".json", "Recipe"),
    McFunctionTypes.LOOT_TOKEN to NamespacedIdContext("loot_tables", ".json", "Loot table")
  )

  private val TEXT_BASED_CONTEXTS = mapOf(
    "predicate" to NamespacedIdContext("predicates", ".json", "Predicate"),
    "item_modifier" to NamespacedIdContext("item_modifiers", ".json", "Item modifier"),
    "dimension" to NamespacedIdContext("dimension", ".json", "Dimension"),
    "structure" to NamespacedIdContext("structures", ".nbt", "Structure")
  )

  private val PREDICATE_PATTERNS = listOf(Regex("\\bpredicate\\s+$"))
  private val DIMENSION_PATTERNS = listOf(Regex("execute\\s+in\\s+$"), Regex("\\bin\\s+$"))
  private val ITEM_MODIFIER_PATTERNS = listOf(Regex("\\bitem\\s+modify\\s+\\S+\\s+\\S+\\s+\\S+\\s+$"))
  private val STRUCTURE_PATTERNS = listOf(Regex("\\bplace\\s+structure\\s+$"))

  private fun checkFunctionFileExists(element: McFunctionNamespacedId, holder: AnnotationHolder) {
    val ctx = resolveNamespacedIdContext(element) ?: return

    val fullText = element.text
    val parts = fullText.split(":")
    val namespace = if (parts.size > 1) parts[0] else "minecraft"
    val path = if (parts.size > 1) parts[1] else parts[0]

    val fileName = path.substringAfterLast("/") + ctx.extension
    val files = FilenameIndex.getFilesByName(
      element.project, fileName, GlobalSearchScope.allScope(element.project)
    )
    val normalizedPath = path.replace("\\", "/")
    val expectedSuffix = "data/$namespace/${ctx.subDir}/$normalizedPath${ctx.extension}"
    val found = files.any { file ->
      file.virtualFile.path.replace("\\", "/").endsWith(expectedSuffix)
    }
    if (!found) {
      holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "${ctx.label} '$fullText' が見つかりません")
        .range(element.textRange)
        .create()
    }
  }

  private fun resolveNamespacedIdContext(element: McFunctionNamespacedId): NamespacedIdContext? {
    // 前の兄弟ノード（空白を除く）を取得
    var prev = element.prevSibling
    while (prev != null && prev.node.elementType == TokenType.WHITE_SPACE) {
      prev = prev.prevSibling
    }
    val prevType = prev?.node?.elementType ?: return null

    // 直前トークンが対応コマンドトークンの場合
    NAMESPACED_ID_CONTEXTS[prevType]?.let { return it }

    // execute run function <id> などで親をさかのぼって確認
    // 親コマンドの最初のトークンを確認
    val parentCommand = element.parent
    if (parentCommand != null) {
      var child = parentCommand.firstChild
      while (child != null) {
        val childType = child.node.elementType
        if (childType == TokenType.WHITE_SPACE) {
          child = child.nextSibling
          continue
        }
        NAMESPACED_ID_CONTEXTS[childType]?.let { return it }
        break
      }
    }

    // テキストベースで前のコマンドコンテキストを確認
    return resolveTextBasedContext(element)
  }

  private fun resolveTextBasedContext(element: McFunctionNamespacedId): NamespacedIdContext? {
    val containingFile = element.containingFile ?: return null
    val elementOffset = element.textOffset
    val fileText = containingFile.text ?: return null
    var lineStart = elementOffset - 1
    while (lineStart >= 0 && fileText[lineStart] != '\n' && fileText[lineStart] != '\r') lineStart--
    lineStart++
    val textBefore = fileText.substring(lineStart, elementOffset)
    for (pattern in PREDICATE_PATTERNS) { if (pattern.containsMatchIn(textBefore)) return TEXT_BASED_CONTEXTS["predicate"] }
    for (pattern in DIMENSION_PATTERNS) { if (pattern.containsMatchIn(textBefore)) return TEXT_BASED_CONTEXTS["dimension"] }
    for (pattern in ITEM_MODIFIER_PATTERNS) { if (pattern.containsMatchIn(textBefore)) return TEXT_BASED_CONTEXTS["item_modifier"] }
    for (pattern in STRUCTURE_PATTERNS) { if (pattern.containsMatchIn(textBefore)) return TEXT_BASED_CONTEXTS["structure"] }
    return null
  }

  private fun isFunctionNamespacedId(element: McFunctionNamespacedId): Boolean {
    return resolveNamespacedIdContext(element) != null
  }

  private fun annotateNamespacedId(element: McFunctionNamespacedId, holder: AnnotationHolder) {
    val colon = element.node.findChildByType(McFunctionTypes.COLON)
    if (colon != null) {
      // コロンの前の部分を NAMESPACE でハイライト
      val ns = element.firstChild
      if (ns != null && ns.node.startOffset < colon.startOffset) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(TextRange(element.textRange.startOffset, colon.startOffset))
          .textAttributes(McFunctionSyntaxHighlighter.NAMESPACE)
          .create()
      }

      // コロン自体を NAMESPACE_COLON でハイライト
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(colon.textRange)
        .textAttributes(McFunctionSyntaxHighlighter.NAMESPACE_COLON)
        .create()

      // コロンの後ろの部分を ARGUMENT でハイライト
      if (colon.startOffset + 1 < element.textRange.endOffset) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(TextRange(colon.startOffset + 1, element.textRange.endOffset))
          .textAttributes(McFunctionSyntaxHighlighter.ARGUMENT)
          .create()
      }
    } else {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(element.textRange)
        .textAttributes(McFunctionSyntaxHighlighter.ARGUMENT)
        .create()
    }
  }

  private fun annotateCommand(element: McFunctionCommand, holder: AnnotationHolder) {
    val token = element.firstChild ?: return
    val tokenType = token.node.elementType

    val attributes = when {
      tokenType in McFunctionSyntaxHighlighter.MAJOR_COMMAND_TOKENS || tokenType == McFunctionTypes.COMMAND_TOKEN -> {
        if (isSubVerb(tokenType) && !isAfterRun(element)) {
          McFunctionSyntaxHighlighter.SUB_COMMAND
        } else {
          McFunctionSyntaxHighlighter.MAJOR_COMMAND
        }
      }
      else -> McFunctionSyntaxHighlighter.ARGUMENT
    }

    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
      .range(token.textRange)
      .textAttributes(attributes)
      .create()
  }

  private fun annotateKeyword(element: McFunctionKeyword, holder: AnnotationHolder) {
    val token = element.firstChild ?: return
    val tokenType = token.node.elementType

    val attributes = when (tokenType) {
      in McFunctionSyntaxHighlighter.SUB_COMMAND_TOKENS ->
        McFunctionSyntaxHighlighter.SUB_COMMAND

      in McFunctionSyntaxHighlighter.FLOW_KEYWORD_TOKENS ->
        McFunctionSyntaxHighlighter.FLOW_KEYWORD

      else -> return
    }

    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
      .range(token.textRange)
      .textAttributes(attributes)
      .create()
  }

  private fun isItemNameBeforeJson(element: PsiElement): Boolean {
    // ARGUMENT_TOKEN の中にある可能性を考慮し、親を辿るか、単一の PsiElement ととしてチェック
    val target = element.parent as? McFunctionArgument ?: element
    val next = target.nextSibling
    if (next != null) {
      // 次の要素が [ か { か、あるいは McFunctionArgument でその中身が [ か { か
      val nextType = next.node.elementType
      if (nextType == McFunctionTypes.LBRACK || nextType == McFunctionTypes.LBRACE) {
        return true
      }
      if (next is McFunctionArgument) {
        val firstChild = next.firstChild
        if (firstChild != null) {
          val firstType = firstChild.node.elementType
          if (firstType == McFunctionTypes.LBRACK || firstType == McFunctionTypes.LBRACE) {
            return true
          }
        }
      }
    }
    return false
  }

  private fun isInJsonStructure(element: PsiElement): Boolean {
    return false // 使わない
  }

  private fun isJsonKey(element: PsiElement): Boolean {
    // まず、自身が ':' か '=' で終わるか、次の要素が ':' か '=' であるか
    val text = element.text
    if (text.endsWith(":") || text.endsWith("=")) {
      // 自身が ':' か '=' で終わる場合も、キーとしての文脈を確認
      val keyPart = text.dropLast(1)
      if (keyPart.isEmpty()) return false
      
      var prev = element.prevSibling
      while (prev != null && (prev.node.elementType == TokenType.WHITE_SPACE || prev.node.elementType == McFunctionTypes.CRLF_TOKEN)) {
        prev = prev.prevSibling
      }
      return prev == null || 
          prev.node.elementType == McFunctionTypes.LBRACK ||
          prev.node.elementType == McFunctionTypes.LBRACE || 
          prev.node.elementType == McFunctionTypes.COMMA ||
          (prev is McFunctionArgument && (prev.lastChild?.node?.elementType == McFunctionTypes.LBRACK || prev.lastChild?.node?.elementType == McFunctionTypes.LBRACE || prev.lastChild?.node?.elementType == McFunctionTypes.COMMA))
    }

    // セレクター引数 ([tag=...]) や JSON ([custom_name={...}]) のキー判定
    // 前後に '[' や ',' がある場合も考慮する
    var prev = element.prevSibling
    while (prev != null && (prev.node.elementType == TokenType.WHITE_SPACE || prev.node.elementType == McFunctionTypes.CRLF_TOKEN)) {
      prev = prev.prevSibling
    }
    val isAfterOpeningOrSeparator = prev == null || 
        prev.node.elementType == McFunctionTypes.LBRACK || 
        prev.node.elementType == McFunctionTypes.LBRACE || 
        prev.node.elementType == McFunctionTypes.COMMA ||
        (prev is McFunctionArgument && (prev.lastChild?.node?.elementType == McFunctionTypes.LBRACK || prev.lastChild?.node?.elementType == McFunctionTypes.LBRACE || prev.lastChild?.node?.elementType == McFunctionTypes.COMMA))

    var next = element.nextSibling
    while (next != null) {
      val nextType = next.node.elementType
      if (nextType == TokenType.WHITE_SPACE || nextType == McFunctionTypes.CRLF_TOKEN) {
        next = next.nextSibling
        continue
      }
      // 直接 ':' か '=' が来る場合、あるいは McFunctionArgument の中の最初の子要素が ':' か '=' の場合
      if (nextType == McFunctionTypes.COLON || nextType == McFunctionTypes.EQUALS ||
        next.text.startsWith(":") || next.text.startsWith("=")
      ) {
        return isAfterOpeningOrSeparator
      }
      break
    }
    // 親要素の隣も見る (PSI構造が ARGUMENT(KEY), ARGUMENT(:) に分かれている場合)
    if (element.parent is McFunctionArgument && element.parent.firstChild == element) {
      // 親が McFunctionArgument の場合、その親 (更なる上位) の文脈をチェックする必要がある
      return isJsonKey(element.parent)
    }
    return false
  }

  private fun annotateJson(element: PsiElement, holder: AnnotationHolder) {
    // 何もしない
  }

  private fun annotateJsonValue(element: PsiElement, holder: AnnotationHolder) {
    val text = element.text
    if (text.isEmpty()) return

    val attributes = when {
      text.startsWith("\"") || text.startsWith("'") -> McFunctionSyntaxHighlighter.JSON_STRING
      text == "true" || text == "false" || text.matches(Regex("-?\\d+b")) || text.matches(Regex("-?\\d+B")) -> McFunctionSyntaxHighlighter.JSON_BOOLEAN
      text.matches(Regex("-?\\d+(\\.\\d+)?([dfslDFSL])?")) -> McFunctionSyntaxHighlighter.JSON_NUMBER
      else -> {
        // 部分的なマッチ（:false や =false のような場合）
        if (text.startsWith(":") || text.startsWith("=")) {
          val valPart = text.substring(1).trim()
          val valAttr = when {
            valPart == "true" || valPart == "false" || valPart.matches(Regex("-?\\d+[bB]")) -> McFunctionSyntaxHighlighter.JSON_BOOLEAN
            valPart.matches(Regex("-?\\d+(\\.\\d+)?([dfslDFSL])?")) -> McFunctionSyntaxHighlighter.JSON_NUMBER
            else -> null
          }
          if (valAttr != null) {
            val startOffset = text.indexOf(valPart)
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
              .range(
                TextRange(
                  element.textRange.startOffset + startOffset,
                  element.textRange.endOffset
                )
              )
              .textAttributes(valAttr)
              .create()
          }
        }
        return
      }
    }

    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
      .range(element.textRange)
      .textAttributes(attributes)
      .create()
  }

  private fun validateCommandArguments(element: McFunctionCommandLine, holder: AnnotationHolder) {
    val text = element.text.trim()
    if (text.startsWith("#")) return

    // トークンに分割（文字列リテラルを考慮した簡易分割）
    val tokens = splitCommandTokens(text)
    if (tokens.isEmpty()) return

    val cmd = tokens[0].lowercase()

    when (cmd) {
      "give" -> {
        // give <targets> <item> [<count>]
        // count は整数でなければならない
        if (tokens.size >= 4) {
          val countToken = tokens[3]
          if (!countToken.matches(Regex("-?\\d+"))) {
            annotateArgumentError(element, text, countToken, "give コマンドの count は整数でなければなりません: '$countToken'", holder)
          } else {
            val count = countToken.toIntOrNull()
            if (count != null && count < 1) {
              annotateArgumentError(element, text, countToken, "give コマンドの count は1以上でなければなりません: '$countToken'", holder)
            }
          }
        }
      }
      "effect" -> {
        // effect give <targets> <effect> [<seconds> [<amplifier> [<hideParticles>]]]
        if (tokens.size >= 2 && tokens[1].lowercase() == "give") {
          if (tokens.size >= 5) {
            val secondsToken = tokens[4]
            if (!secondsToken.matches(Regex("\\d+"))) {
              annotateArgumentError(element, text, secondsToken, "effect give の seconds は0以上の整数でなければなりません: '$secondsToken'", holder)
            }
          }
          if (tokens.size >= 6) {
            val amplifierToken = tokens[5]
            if (!amplifierToken.matches(Regex("\\d+"))) {
              annotateArgumentError(element, text, amplifierToken, "effect give の amplifier は0以上の整数でなければなりません: '$amplifierToken'", holder)
            } else {
              val amplifier = amplifierToken.toIntOrNull()
              if (amplifier != null && amplifier > 255) {
                annotateArgumentError(element, text, amplifierToken, "effect give の amplifier は255以下でなければなりません: '$amplifierToken'", holder)
              }
            }
          }
          if (tokens.size >= 7) {
            val hideParticlesToken = tokens[6]
            if (hideParticlesToken != "true" && hideParticlesToken != "false") {
              annotateArgumentError(element, text, hideParticlesToken, "effect give の hideParticles は true または false でなければなりません: '$hideParticlesToken'", holder)
            }
          }
        }
      }
      "enchant" -> {
        // enchant <targets> <enchantment> [<level>]
        if (tokens.size >= 4) {
          val levelToken = tokens[3]
          if (!levelToken.matches(Regex("\\d+"))) {
            annotateArgumentError(element, text, levelToken, "enchant コマンドの level は0以上の整数でなければなりません: '$levelToken'", holder)
          } else {
            val level = levelToken.toIntOrNull()
            if (level != null && level > 255) {
              annotateArgumentError(element, text, levelToken, "enchant コマンドの level は255以下でなければなりません: '$levelToken'", holder)
            }
          }
        }
      }
      "experience", "xp" -> {
        // experience add|set|query <targets> <amount> (points|levels)
        if (tokens.size >= 2 && (tokens[1].lowercase() == "add" || tokens[1].lowercase() == "set")) {
          if (tokens.size >= 4) {
            val amountToken = tokens[3]
            if (!amountToken.matches(Regex("-?\\d+"))) {
              annotateArgumentError(element, text, amountToken, "${cmd} の amount は整数でなければなりません: '$amountToken'", holder)
            }
          }
          if (tokens.size >= 5) {
            val unitToken = tokens[4].lowercase()
            if (unitToken != "points" && unitToken != "levels") {
              annotateArgumentError(element, text, tokens[4], "${cmd} の単位は 'points' または 'levels' でなければなりません: '${tokens[4]}'", holder)
            }
          }
        }
      }
      "time" -> {
        // time add|set <value>
        if (tokens.size >= 2 && (tokens[1].lowercase() == "add" || tokens[1].lowercase() == "set")) {
          if (tokens.size >= 3) {
            val valueToken = tokens[2]
            // 数値または数値+単位(d/s/t)
            if (!valueToken.matches(Regex("-?\\d+[dst]?")) && !valueToken.matches(Regex("(noon|midnight|day|night)"))) {
              annotateArgumentError(element, text, valueToken, "time の value は整数（またはd/s/t単位付き）でなければなりません: '$valueToken'", holder)
            }
          }
        }
      }
      "difficulty" -> {
        // difficulty [peaceful|easy|normal|hard]
        if (tokens.size >= 2) {
          val diffToken = tokens[1].lowercase()
          val validDiffs = setOf("peaceful", "easy", "normal", "hard", "p", "e", "n", "h", "0", "1", "2", "3")
          if (!validDiffs.contains(diffToken)) {
            annotateArgumentError(element, text, tokens[1], "difficulty の値は peaceful/easy/normal/hard でなければなりません: '${tokens[1]}'", holder)
          }
        }
      }
      "gamemode" -> {
        // gamemode <mode> [<target>]
        if (tokens.size >= 2) {
          val modeToken = tokens[1].lowercase()
          val validModes = setOf("survival", "creative", "adventure", "spectator", "s", "c", "a", "sp", "0", "1", "2", "3")
          if (!validModes.contains(modeToken)) {
            annotateArgumentError(element, text, tokens[1], "gamemode の値は survival/creative/adventure/spectator でなければなりません: '${tokens[1]}'", holder)
          }
        }
      }
      "weather" -> {
        // weather clear|rain|thunder [<duration>]
        if (tokens.size >= 2) {
          val weatherToken = tokens[1].lowercase()
          val validWeathers = setOf("clear", "rain", "thunder")
          if (!validWeathers.contains(weatherToken)) {
            annotateArgumentError(element, text, tokens[1], "weather の値は clear/rain/thunder でなければなりません: '${tokens[1]}'", holder)
          }
        }
        if (tokens.size >= 3) {
          val durationToken = tokens[2]
          if (!durationToken.matches(Regex("\\d+"))) {
            annotateArgumentError(element, text, durationToken, "weather の duration は0以上の整数でなければなりません: '$durationToken'", holder)
          }
        }
      }
    }
  }

  // コマンドテキストをトークンに分割（文字列リテラルや{}[]を考慮）
  private fun splitCommandTokens(text: String): List<String> {
    val tokens = mutableListOf<String>()
    val current = StringBuilder()
    var depth = 0
    var inString = false
    var stringChar = ' '

    for (ch in text) {
      when {
        inString -> {
          current.append(ch)
          if (ch == stringChar) inString = false
        }
        ch == '"' || ch == '\'' -> {
          inString = true
          stringChar = ch
          current.append(ch)
        }
        ch == '{' || ch == '[' -> {
          depth++
          current.append(ch)
        }
        ch == '}' || ch == ']' -> {
          depth--
          current.append(ch)
        }
        ch == ' ' || ch == '\t' -> {
          if (depth == 0) {
            if (current.isNotEmpty()) {
              tokens.add(current.toString())
              current.clear()
            }
          } else {
            current.append(ch)
          }
        }
        else -> current.append(ch)
      }
    }
    if (current.isNotEmpty()) tokens.add(current.toString())
    return tokens
  }

  // 特定のトークンに対してエラーアノテーションを付与する
  private fun annotateArgumentError(
    element: McFunctionCommandLine,
    fullText: String,
    token: String,
    message: String,
    holder: AnnotationHolder
  ) {
    val tokenIndex = fullText.indexOf(token)
    if (tokenIndex < 0) return
    val startOffset = element.textRange.startOffset + tokenIndex
    val endOffset = startOffset + token.length
    if (endOffset > element.textRange.endOffset) return
    holder.newAnnotation(HighlightSeverity.ERROR, message)
      .range(TextRange(startOffset, endOffset))
      .create()
  }

  private fun isSubVerb(tokenType: IElementType): Boolean {
    return tokenType == McFunctionTypes.MODIFY_TOKEN ||
        tokenType == McFunctionTypes.SET_TOKEN ||
        tokenType == McFunctionTypes.ADD_TOKEN ||
        tokenType == McFunctionTypes.REMOVE_TOKEN ||
        tokenType == McFunctionTypes.MERGE_TOKEN ||
        tokenType == McFunctionTypes.ENABLE_TOKEN ||
        tokenType == McFunctionTypes.DISABLE_TOKEN ||
        tokenType == McFunctionTypes.QUERY_TOKEN ||
        tokenType == McFunctionTypes.GRANT_TOKEN ||
        tokenType == McFunctionTypes.REVOKE_TOKEN
  }

  private fun isAfterRun(element: PsiElement): Boolean {
    // execute ... run <command> の構造を確認する
    // element(McFunctionCommand) -> McFunctionGenericCommand -> McFunctionCommandLine -> McFunctionExecuteCommand
    
    val genericCommand = element.parent as? McFunctionGenericCommand ?: return false
    val commandLine = genericCommand.parent as? McFunctionCommandLine ?: return false
    val executeCommand = commandLine.parent as? McFunctionExecuteCommand ?: return false

    // executeCommand の commandLine が current commandLine かつ RUN_TOKEN が存在するか
    return executeCommand.commandLine == commandLine && 
           executeCommand.node.findChildByType(McFunctionTypes.RUN_TOKEN) != null
  }
}
