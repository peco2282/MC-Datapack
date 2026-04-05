package com.github.peco2282.mcdatapack.language

import com.github.peco2282.mcdatapack.language.psi.McFunctionLexerAdapter
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.lexer.Lexer
import com.intellij.psi.TokenType
import org.junit.Test

class McFunctionLexerTest : BasePlatformTestCase() {

    @Test
    fun testBasicCommand() {
        val lexer = McFunctionLexerAdapter()
        doTest(lexer, "say hello", 
            McFunctionTypes.SAY_TOKEN, "say",
            McFunctionTypes.COMMAND_TOKEN, "hello")
    }

    @Test
    fun testCoordinates() {
        val lexer = McFunctionLexerAdapter()
        doTest(lexer, "tp ~ ~ ~",
            McFunctionTypes.TP_TOKEN, "tp",
            McFunctionTypes.COORD_TOKEN, "~",
            McFunctionTypes.COORD_TOKEN, "~",
            McFunctionTypes.COORD_TOKEN, "~")
    }

    @Test
    fun testCoordinatesWithValues() {
        val lexer = McFunctionLexerAdapter()
        doTest(lexer, "tp ~10 ^-5 ~",
            McFunctionTypes.TP_TOKEN, "tp",
            McFunctionTypes.COORD_TOKEN, "~10",
            McFunctionTypes.COORD_TOKEN, "^-5",
            McFunctionTypes.COORD_TOKEN, "~")
    }

    @Test
    fun testComment() {
        val lexer = McFunctionLexerAdapter()
        doTest(lexer, "# this is a comment",
            McFunctionTypes.COMMENT_TOKEN, "# this is a comment")
    }

    @Test
    fun testMacro() {
        val lexer = McFunctionLexerAdapter()
        doTest(lexer, "\$say \$(message)",
            McFunctionTypes.MACRO_TOKEN, "\$say",
            McFunctionTypes.MACRO_VAR_TOKEN, "\$(message)")
    }

    @Test
    fun testExecuteCommand() {
        val lexer = McFunctionLexerAdapter()
        doTest(lexer, "execute as @a run say hi",
            McFunctionTypes.EXECUTE_TOKEN, "execute",
            McFunctionTypes.AS_TOKEN, "as",
            McFunctionTypes.SELECTOR_A, "@a",
            McFunctionTypes.RUN_TOKEN, "run",
            McFunctionTypes.SAY_TOKEN, "say",
            McFunctionTypes.COMMAND_TOKEN, "hi")
    }

    private fun doTest(lexer: Lexer, text: String, vararg expectedTokens: Any) {
        lexer.start(text)
        var i = 0
        while (lexer.tokenType != null) {
            if (lexer.tokenType == TokenType.WHITE_SPACE) {
                lexer.advance()
                continue
            }

            if (i >= expectedTokens.size) {
                fail("Too many tokens for text: '$text'. Found '${lexer.tokenType}' at ${lexer.tokenStart}")
            }
            
            val expectedType = expectedTokens[i++]
            val expectedText = expectedTokens[i++]
            
            // System.out.println("[DEBUG_LOG] Expected type: $expectedType, Actual type: ${lexer.tokenType}")
            // System.out.println("[DEBUG_LOG] Expected text: $expectedText, Actual text: ${lexer.tokenText}")
            assertEquals("Token type mismatch at index ${i/2 - 1} for '$text': Expected $expectedType, but got ${lexer.tokenType}", expectedType, lexer.tokenType)
            assertEquals("Token text mismatch at index ${i/2 - 1} for '$text': Expected $expectedText, but got ${lexer.tokenText}", expectedText, lexer.tokenText)
            
            lexer.advance()
        }
        
        if (i < expectedTokens.size) {
            fail("Not enough tokens for text: '$text'. Expected ${expectedTokens.size / 2}, found ${i / 2}")
        }
    }
}
