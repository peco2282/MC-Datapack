package com.github.peco2282.mcdatapack.language.highlighting

import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test

class McFunctionSyntaxHighlighterTest : BasePlatformTestCase() {

    @Test
    fun testTokenHighlights() {
        val highlighter = McFunctionSyntaxHighlighter()

        // MAJOR_COMMAND
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.COMMAND_TOKEN).toList(), McFunctionSyntaxHighlighter.MAJOR_COMMAND)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.GIVE_TOKEN).toList(), McFunctionSyntaxHighlighter.MAJOR_COMMAND)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.TP_TOKEN).toList(), McFunctionSyntaxHighlighter.MAJOR_COMMAND)

        // FLOW_KEYWORD
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.EXECUTE_TOKEN).toList(), McFunctionSyntaxHighlighter.FLOW_KEYWORD)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.RUN_TOKEN).toList(), McFunctionSyntaxHighlighter.FLOW_KEYWORD)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.IF_TOKEN).toList(), McFunctionSyntaxHighlighter.FLOW_KEYWORD)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.RETURN_TOKEN).toList(), McFunctionSyntaxHighlighter.FLOW_KEYWORD)

        // SUB_COMMAND
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.AS_TOKEN).toList(), McFunctionSyntaxHighlighter.SUB_COMMAND)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.BY_TOKEN).toList(), McFunctionSyntaxHighlighter.SUB_COMMAND)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.MOUNT_TOKEN).toList(), McFunctionSyntaxHighlighter.SUB_COMMAND)

        // STRUCTURE
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.LBRACK).toList(), McFunctionSyntaxHighlighter.STRUCTURE)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.DOT_TOKEN).toList(), McFunctionSyntaxHighlighter.STRUCTURE)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.DOTDOT_TOKEN).toList(), McFunctionSyntaxHighlighter.STRUCTURE)

        // MACRO
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.MACRO_TOKEN).toList(), McFunctionSyntaxHighlighter.MACRO)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.MACRO_VAR_TOKEN).toList(), McFunctionSyntaxHighlighter.MACRO)
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.MACRO_LINE_START).toList(), McFunctionSyntaxHighlighter.MACRO)

        // COORDINATE
        assertContainsElements(highlighter.getTokenHighlights(McFunctionTypes.COORD_TOKEN).toList(), McFunctionSyntaxHighlighter.COORDINATE)
    }
}
