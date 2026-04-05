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

  private fun checkFunctionFileExists(element: McFunctionNamespacedId, holder: AnnotationHolder) {
    if (!isFunctionNamespacedId(element)) return

    val fullText = element.text
    val parts = fullText.split(":")
    val namespace = if (parts.size > 1) parts[0] else "minecraft"
    val path = if (parts.size > 1) parts[1] else parts[0]

    val fileName = path.substringAfterLast("/") + ".mcfunction"
    val files = FilenameIndex.getFilesByName(
      element.project, fileName, GlobalSearchScope.allScope(element.project)
    )
    val normalizedPath = path.replace("\\", "/")
    val expectedSuffix = "data/$namespace/functions/$normalizedPath.mcfunction"
    val found = files.any { file ->
      file.virtualFile.path.replace("\\", "/").endsWith(expectedSuffix)
    }
    if (!found) {
      holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Function '$fullText' が見つかりません")
        .range(element.textRange)
        .create()
    }
  }

  private fun isFunctionNamespacedId(element: McFunctionNamespacedId): Boolean {
    // 前の兄弟ノード（空白を除く）が FUNCTION_TOKEN かどうかを確認
    var prev = element.prevSibling
    while (prev != null) {
      val type = prev.node.elementType
      if (type == TokenType.WHITE_SPACE) {
        prev = prev.prevSibling
        continue
      }
      return type == McFunctionTypes.FUNCTION_TOKEN
    }
    return false
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
