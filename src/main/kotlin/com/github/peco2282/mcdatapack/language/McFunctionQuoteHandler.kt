package com.github.peco2282.mcdatapack.language

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class McFunctionQuoteHandler : TypedHandlerDelegate() {
  override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
    if (file.language !is McFunctionLanguage) return Result.CONTINUE
    if (c != '"' && c != '\'') return Result.CONTINUE

    val offset = editor.caretModel.offset
    val document = editor.document
    if (offset < document.textLength && document.charsSequence[offset] == c) {
      return Result.CONTINUE
    }

    document.insertString(offset, c.toString())
    return Result.STOP
  }
}
