package com.github.peco2282.mcdatapack.language.documentation

import com.github.peco2282.mcdatapack.language.completion.McFunctionConstants
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

class McFunctionDocumentationProvider : AbstractDocumentationProvider() {

    override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? {
        val commandName = resolveCommandName(element) ?: return null
        val doc = McFunctionConstants.COMMAND_DOCS[commandName] ?: return null
        val (syntax, description) = doc
        return buildDocHtml(commandName, syntax, description)
    }

    override fun getQuickNavigateInfo(element: PsiElement, originalElement: PsiElement?): String? {
        val commandName = resolveCommandName(element) ?: return null
        val doc = McFunctionConstants.COMMAND_DOCS[commandName] ?: return null
        return doc.first
    }

    private fun resolveCommandName(element: PsiElement): String? {
        // COMMAND_TOKEN トークンそのもの
        if (element.elementType == McFunctionTypes.COMMAND_TOKEN) {
            return element.text
        }
        // 親がCOMMAND_TOKENを持つ場合（McFunctionCommand PSI要素）
        val commandToken = element.firstChild
            ?.takeIf { it.elementType == McFunctionTypes.COMMAND_TOKEN }
            ?: return null
        return commandToken.text
    }

    private fun buildDocHtml(command: String, syntax: String, description: String): String {
        return buildString {
            append("<html><body>")
            append("<b>").append(command).append("</b><br/>")
            append("<code>").append(syntax).append("</code>")
            append("<hr/>")
            append(description)
            append("</body></html>")
        }
    }
}
