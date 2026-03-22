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
        // コメント
        val COMMENT = TextAttributesKey.createTextAttributesKey(
            "MCFUNCTION_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT
        )

        // Layer 1: Flow Control (execute, run, return, function, schedule) — Bold
        val FLOW_CONTROL = TextAttributesKey.createTextAttributesKey(
            "MCFUNCTION_FLOW_CONTROL", DefaultLanguageHighlighterColors.KEYWORD
        )

        // Layer 2: Sub-modifiers (if, unless, as, at, data, entity, score, storage, block, items)
        val SUB_MODIFIER = TextAttributesKey.createTextAttributesKey(
            "MCFUNCTION_SUB_MODIFIER", DefaultLanguageHighlighterColors.KEYWORD
        )

        // Layer 3: Target selectors (@s, @a, @p, @e, @r)
        val SELECTOR = TextAttributesKey.createTextAttributesKey(
            "MCFUNCTION_SELECTOR", DefaultLanguageHighlighterColors.METADATA
        )

        // Layer 4: Structural symbols (continuation \, macro $, dot ., comparators)
        val STRUCTURE = TextAttributesKey.createTextAttributesKey(
            "MCFUNCTION_STRUCTURE", DefaultLanguageHighlighterColors.OPERATION_SIGN
        )

        // Layer 4: Macro ($variable)
        val MACRO = TextAttributesKey.createTextAttributesKey(
            "MCFUNCTION_MACRO", DefaultLanguageHighlighterColors.INSTANCE_FIELD
        )

        // コマンド名（行頭 or run 直後）
        val COMMAND = TextAttributesKey.createTextAttributesKey(
            "MCFUNCTION_COMMAND", DefaultLanguageHighlighterColors.FUNCTION_CALL
        )

        // 文字列
        val STRING = TextAttributesKey.createTextAttributesKey(
            "MCFUNCTION_STRING", DefaultLanguageHighlighterColors.STRING
        )
    }

    override fun getHighlightingLexer(): Lexer = McFunctionLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            McFunctionTypes.COMMENT_TOKEN -> arrayOf(COMMENT)

            // Layer 1: Flow Control
            McFunctionTypes.EXECUTE_TOKEN,
            McFunctionTypes.RUN_TOKEN,
            McFunctionTypes.RETURN_TOKEN,
            McFunctionTypes.FUNCTION_TOKEN,
            McFunctionTypes.SCHEDULE_TOKEN -> arrayOf(FLOW_CONTROL)

            // Layer 2: Sub-modifiers
            McFunctionTypes.IF_TOKEN,
            McFunctionTypes.UNLESS_TOKEN,
            McFunctionTypes.AS_TOKEN,
            McFunctionTypes.AT_TOKEN,
            McFunctionTypes.DATA_TOKEN,
            McFunctionTypes.ENTITY_TOKEN,
            McFunctionTypes.SCORE_TOKEN,
            McFunctionTypes.STORAGE_TOKEN,
            McFunctionTypes.BLOCK_TOKEN,
            McFunctionTypes.ITEMS_TOKEN,
            McFunctionTypes.STORE_TOKEN,
            McFunctionTypes.RESULT_TOKEN -> arrayOf(SUB_MODIFIER)

            // Layer 3: Target selectors
            McFunctionTypes.SELECTOR_S,
            McFunctionTypes.SELECTOR_A,
            McFunctionTypes.SELECTOR_P,
            McFunctionTypes.SELECTOR_E,
            McFunctionTypes.SELECTOR_R -> arrayOf(SELECTOR)

            // Layer 4: Structural symbols
            McFunctionTypes.CONTINUATION_TOKEN,
            McFunctionTypes.DOT_TOKEN,
            McFunctionTypes.DOTDOT_TOKEN,
            McFunctionTypes.GTE_TOKEN,
            McFunctionTypes.LTE_TOKEN,
            McFunctionTypes.GT_TOKEN,
            McFunctionTypes.LT_TOKEN,
            McFunctionTypes.MATCHES_TOKEN -> arrayOf(STRUCTURE)

            // Layer 4: Macro
            McFunctionTypes.MACRO_TOKEN -> arrayOf(MACRO)

            // コマンド名トークン
            McFunctionTypes.COMMAND_TOKEN -> arrayOf(COMMAND)

            // 文字列
            McFunctionTypes.STRING_TOKEN -> arrayOf(STRING)

            else -> emptyArray()
        }
    }
}

class McFunctionSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
        McFunctionSyntaxHighlighter()
}
