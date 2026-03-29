package com.github.peco2282.mcdatapack.language

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

      val attributes = when {
        tokenType in McFunctionSyntaxHighlighter.SUB_COMMAND_TOKENS ->
          McFunctionSyntaxHighlighter.SUB_COMMAND

        tokenType in McFunctionSyntaxHighlighter.FLOW_KEYWORD_TOKENS ->
          McFunctionSyntaxHighlighter.FLOW_KEYWORD

        else -> return // デフォルト（白）
      }

      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(token.textRange)
        .textAttributes(attributes)
        .create()
    }
  }

  private fun isSubVerb(tokenType: IElementType): Boolean {
    return tokenType == McFunctionTypes.MODIFY_TOKEN ||
        tokenType == McFunctionTypes.SET_TOKEN ||
        tokenType == McFunctionTypes.ADD_TOKEN ||
        tokenType == McFunctionTypes.REMOVE_TOKEN ||
        tokenType == McFunctionTypes.MERGE_TOKEN
  }

  private fun isAfterRun(element: PsiElement): Boolean {
    // execute ... run <command> の構造を確認する
    // BNF: execute_command ::= EXECUTE_TOKEN ... (SPACE_TOKEN RUN_TOKEN SPACE_TOKEN command_line)?
    // command_line ::= execute_command | generic_command
    // generic_command ::= command ...
    // element(McFunctionCommand) の親が generic_command で、その親が command_line、
    // さらにその親が execute_command であれば、その直前に RUN_TOKEN があるかチェック
    val commandLine = element.parent as? McFunctionCommandLine ?: return false
    val executeCommand = commandLine.parent as? McFunctionCommand ?: return false
    // executeCommand の子要素を調べて、RUN_TOKEN の後にこの commandLine が来ているか確認
    var runFound = false
    var child = executeCommand.firstChild
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

  companion object
}
