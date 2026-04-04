package com.github.peco2282.mcdatapack

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test

class McFunctionCompletionTest : BasePlatformTestCase() {
    @Test
    fun testCommandCompletion() {
        myFixture.configureByText("test.mcfunction", "<caret>")
        myFixture.completeBasic()
        val lookupElementStrings = myFixture.lookupElementStrings
        assertNotNull(lookupElementStrings)
        assertTrue("Expected 'execute' in $lookupElementStrings", lookupElementStrings!!.contains("execute"))
    }

    @Test
    fun testKeywordCompletion() {
        myFixture.configureByText("test.mcfunction", "execute ru<caret>")
        myFixture.completeBasic()
        val lookupElementStrings = myFixture.lookupElementStrings
        // 補完候補がある場合は run が含まれることを確認する
        // 候補が1つに絞られて自動補完された場合は lookupElementStrings が null になる
        if (lookupElementStrings != null) {
            assertTrue("Expected 'run' in $lookupElementStrings", lookupElementStrings.contains("run"))
        } else {
            // 自動補完された場合、エディタのテキストに "run" が含まれているはず
            val text = myFixture.editor.document.text
            assertTrue("Expected 'run' to be auto-completed in: $text", text.contains("run"))
        }
    }

    @Test
    fun testSubCommandCompletion() {
        myFixture.configureByText("test.mcfunction", "execute as @s <caret>")
        myFixture.completeBasic()
        val lookupElementStrings = myFixture.lookupElementStrings
        assertNotNull(lookupElementStrings)
        assertTrue(lookupElementStrings!!.contains("run"))
        assertTrue(lookupElementStrings.contains("at"))
    }
}
