package com.github.peco2282.mcdatapack.language

import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType

class McFunctionSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val COMMENT = TextAttributesKey.createTextAttributesKey("MCFUNCTION_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val COMMAND = TextAttributesKey.createTextAttributesKey("MCFUNCTION_COMMAND", DefaultLanguageHighlighterColors.KEYWORD)
    }

    override fun getHighlightingLexer(): Lexer = McFunctionLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            McFunctionTypes.COMMENT_TOKEN -> arrayOf(COMMENT)
            McFunctionTypes.COMMAND_TOKEN,
            McFunctionTypes.EXECUTE_TOKEN,
            McFunctionTypes.RUN_TOKEN,
            McFunctionTypes.AS_TOKEN,
            McFunctionTypes.AT_TOKEN,
            McFunctionTypes.STORE_TOKEN,
            McFunctionTypes.RESULT_TOKEN,
            McFunctionTypes.SCORE_TOKEN,
            McFunctionTypes.IF_TOKEN,
            McFunctionTypes.ENTITY_TOKEN -> arrayOf(COMMAND)
            else -> emptyArray()
        }
    }
}

class McFunctionSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter = McFunctionSyntaxHighlighter()
}
