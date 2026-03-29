package com.github.peco2282.mcdatapack.language.highlighting

import com.github.peco2282.mcdatapack.language.psi.McFunctionCommand
import com.github.peco2282.mcdatapack.language.psi.McFunctionCommandLine
import com.github.peco2282.mcdatapack.language.psi.McFunctionKeyword
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType

class McFunctionAnnotator : Annotator {
  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    if (element is McFunctionCommand) {
      val token = element.firstChild ?: return
      val tokenType = token.node.elementType

      val attributes = when {
        tokenType in McFunctionSyntaxHighlighter.MAJOR_COMMAND_TOKENS || tokenType == McFunctionTypes.COMMAND_TOKEN -> {
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

    // JSON/NBT highlighting
    val node = element.node
    val type = node.elementType
    if (type == McFunctionTypes.ARGUMENT_TOKEN || type == McFunctionTypes.STRING_TOKEN || type == McFunctionTypes.COMMAND_TOKEN) {
      val text = element.text
      // Check if it's a JSON/NBT literal (true, false, or numbers like 0b, 1.0f, etc)
      if (isJsonLiteral(text)) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.textRange)
          .textAttributes(McFunctionSyntaxHighlighter.JSON_LITERAL)
          .create()
      }
    }

    // Highlight keys (before : or =)
    if (type == McFunctionTypes.COLON || type == McFunctionTypes.EQUALS) {
      val prev = getPrevNonWhitespaceSibling(element)
      if (prev != null && (prev.node.elementType == McFunctionTypes.ARGUMENT_TOKEN || prev.node.elementType == McFunctionTypes.STRING_TOKEN || prev.node.elementType == McFunctionTypes.COMMAND_TOKEN)) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(prev.textRange)
          .textAttributes(McFunctionSyntaxHighlighter.JSON_KEY)
          .create()
      }
    }
  }

  private fun isJsonLiteral(text: String): Boolean {
    if (text == "true" || text == "false") return true
    // Minecraft NBT numbers: 0b, 1s, 2L, 3.0f, 4.0d, etc.
    val nbtNumberRegex = Regex("^-?\\d+(\\.\\d+)?([bslfdBSLFD])?$")
    return nbtNumberRegex.matches(text)
  }

  private fun getPrevNonWhitespaceSibling(element: PsiElement): PsiElement? {
    var prev = element.prevSibling
    while (prev != null && (prev.node.elementType == McFunctionTypes.WHITE_SPACE || prev.node.elementType == com.intellij.psi.TokenType.WHITE_SPACE)) {
      prev = prev.prevSibling
    }
    return prev
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
