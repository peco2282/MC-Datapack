package com.github.peco2282.mcdatapack

import com.github.peco2282.mcdatapack.language.psi.McFunctionLexerAdapter
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test

class McFunctionLexerTest : BasePlatformTestCase() {
    @Test
    fun testExecuteRunTokens() {
        val adapter = McFunctionLexerAdapter()
        val input = "execute as @a at @s anchored eyes run execute facing entity @s eyes run function my:namespace"
        adapter.start(input)
        while (adapter.tokenType != null) {
            println("[DEBUG_LOG] Token: ${adapter.tokenType}, text: '${adapter.tokenText}'")
            adapter.advance()
        }
    }

    @Test
    fun testLexerCrash() {
        val adapter = McFunctionLexerAdapter()
        // We try common characters that might be missed or causing issues.
        // Also try some non-ASCII characters.
        val inputs = listOf(
            "say hello",
            "tp @a ~ ~ ~",
            "execute if score @s test matches 1..",
            "# comment",
            "あいうえお", // Japanese characters
            "😀",        // Emoji
            "\u0000",    // Null character
            "\u001F",    // Control character
            "   ",       // Only spaces
            "\r\n\r\n",  // Only CRLF
            "\\\\",      // Backslashes
            "\"quoted string\"",
            "'single quoted'",
            "[ ] { } , : . .. >= <= > <",
            "$"          // Lone dollar sign (might be missed by MACRO_TOKEN)
        )
        
        for (input in inputs) {
            println("[DEBUG_LOG] Testing input: '$input'")
            try {
                adapter.start(input)
                while (adapter.tokenType != null) {
                    println("[DEBUG_LOG]   Token: ${adapter.tokenType}, text: '${adapter.tokenText}'")
                    adapter.advance()
                }
            } catch (e: Throwable) {
                println("[DEBUG_LOG] CRASHED on input: '$input'")
                e.printStackTrace()
                throw e
            }
        }
    }
}
