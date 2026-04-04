package com.github.peco2282.mcdatapack.language.highlighting

import com.github.peco2282.mcdatapack.language.psi.*
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class McFunctionAnnotator : Annotator {
  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    if (element.javaClass.simpleName.startsWith("McFunction") && element !is McFunctionFile)
      println("annotate: ${element.javaClass.simpleName} ${element.text}")
    if (element is McFunctionCommand) {
      val token = element.firstChild ?: return
      val tokenType = token.node.elementType

      // 1. オレンジ(MAJOR_COMMAND)の判定
      // generic_command 内の COMMAND_TOKEN は通常オレンジだが、
      // summon や particle などの引数として現れる場合は白にしたい。
      // 現状 BNF では generic_command ::= command ... で、command に COMMAND_TOKEN が含まれている。
      // しかし、引数側の argument にも COMMAND_TOKEN が含まれている。
      // もし element が command (McFunctionCommand) であれば、それは常に先頭のはず。
      // ただし、「第2動詞」をオレンジから紫に下げたいという要望がある。

      val attributes = when {
        tokenType in McFunctionSyntaxHighlighter.MAJOR_COMMAND_TOKENS || tokenType == McFunctionTypes.COMMAND_TOKEN -> {
          // run の直後ならオレンジのまま、それ以外ならサブコマンド色に格下げしたい単語がある
          if (isSubVerb(tokenType)) {
            if (isAfterRun(element)) {
              McFunctionSyntaxHighlighter.MAJOR_COMMAND
            } else {
              McFunctionSyntaxHighlighter.SUB_COMMAND
            }
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

    if (element is McFunctionKeyword) {
      val token = element.firstChild ?: return
      val tokenType = token.node.elementType

      val attributes = when (tokenType) {
        in McFunctionSyntaxHighlighter.SUB_COMMAND_TOKENS ->
          McFunctionSyntaxHighlighter.SUB_COMMAND

        in McFunctionSyntaxHighlighter.FLOW_KEYWORD_TOKENS ->
          McFunctionSyntaxHighlighter.FLOW_KEYWORD

        else -> return // デフォルト（白）
      }

      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(token.textRange)
        .textAttributes(attributes)
        .create()
    }

    if (element is McFunctionItemStack) {
      // netherite_sword[...] の netherite_sword 部分をハイライト
      val namespacedId = element.namespacedId
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(namespacedId.textRange)
        .textAttributes(McFunctionSyntaxHighlighter.JSON_KEY)
        .create()
      return
    }

    if (element is McFunctionComponent) {
      // attribute_modifiers={...} の attribute_modifiers 部分をハイライト
      val namespacedId = element.namespacedId
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(namespacedId.textRange)
        .textAttributes(McFunctionSyntaxHighlighter.JSON_KEY)
        .create()
      return
    }

    if (element is McFunctionJson || element is McFunctionJsonObject || element is McFunctionJsonArray) {
      return
    }

    if (element is McFunctionArgument) {
      // McFunctionArgument 自体へのアノテーションはスキップし、
      // その中のトークン (PsiElement) 単位で処理させる。
      return
    }

    val type = element.node.elementType
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
                com.intellij.openapi.util.TextRange(
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
    // generic_command ::= command ...
    // element(McFunctionCommand) の親が generic_command で、その親が command_line、
    // さらにその親が execute_command (McFunctionCommandLine) であれば、その直前に RUN_TOKEN があるかチェック
    // 
    // 現在の PSI 木を確認すると、execute_command という要素名はなく McFunctionCommandLine として生成されている可能性がある
    // BNF: command_line ::= execute_command | generic_command
    // private execute_command ::= EXECUTE_TOKEN ... (WHITE_SPACE RUN_TOKEN WHITE_SPACE command_line)?

    val genericCommand = element.parent // McFunctionCommand -> McFunctionCommandImpl (generic_command)
    val commandLine = genericCommand?.parent as? McFunctionCommandLine ?: return false
    val parentCommandLine = commandLine.parent as? McFunctionCommandLine ?: return false

    // 親の commandLine の子要素を調べて、RUN_TOKEN の後にこの commandLine が来ているか確認
    var runFound = false
    var child = parentCommandLine.firstChild
    while (child != null) {
      if (child.node.elementType == McFunctionTypes.RUN_TOKEN) {
        runFound = true
      }
      if (runFound && child == commandLine) {
        return true
      }
      child = child.nextSibling
    }
    return false
  }
}
