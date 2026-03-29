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
        assertNotNull(lookupElementStrings)
        assertTrue("Expected 'run' in $lookupElementStrings", lookupElementStrings!!.contains("run"))
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
