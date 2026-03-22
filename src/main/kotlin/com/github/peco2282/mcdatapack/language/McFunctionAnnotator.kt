package com.github.peco2282.mcdatapack.language

import com.github.peco2282.mcdatapack.language.psi.McFunctionCommand
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

class McFunctionAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is McFunctionCommand) {
            val token = element.firstChild ?: return
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(token.textRange)
                .textAttributes(McFunctionSyntaxHighlighter.COMMAND)
                .create()
        }
    }
}
