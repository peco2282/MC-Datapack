package com.github.peco2282.mcdatapack

import com.github.peco2282.mcdatapack.language.highlighting.McFunctionAnnotator
import com.github.peco2282.mcdatapack.language.highlighting.McFunctionSyntaxHighlighter
import com.intellij.lang.annotation.Annotation
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test

class McFunctionHighlightingTest : BasePlatformTestCase() {
    @Test
    fun testJsonHighlighting() {
        val input = """
            item replace entity @s enderchest.0 with minecraft:stone[custom_name={"italic":false,"text":"木の剣"}]
        """.trimIndent()
        myFixture.configureByText("test.mcfunction", input)
        
        val annotations = myFixture.doHighlighting(HighlightSeverity.INFORMATION)
        
        val itemKeyAnn = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.JSON_KEY && myFixture.file.text.substring(it.startOffset, it.endOffset).let { t -> t == "minecraft:stone" || t == "minecraft" || t == "stone" } }
        assertTrue("Should have 'minecraft:stone' or its parts highlighted as JSON_KEY", itemKeyAnn.isNotEmpty())

        val jsonKeyAnn = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.JSON_KEY }
        val jsonStringAnn = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.JSON_STRING }
        val jsonBooleanAnn = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.JSON_BOOLEAN }

        assertTrue("Should have JSON keys highlighted", jsonKeyAnn.isNotEmpty())
        assertTrue("Should have JSON strings highlighted", jsonStringAnn.isNotEmpty())
        assertTrue("Should have JSON booleans highlighted", jsonBooleanAnn.isNotEmpty())

        // 重複チェック: 同じ範囲に同じ属性のアノテーションが複数ないか
        val distinctKeys = jsonKeyAnn.map { it.startOffset to it.endOffset }.distinct()
        assertEquals("Should not have duplicate JSON key annotations", distinctKeys.size, jsonKeyAnn.size)
    }

    @Test
    fun testCommandHighlighting() {
        val input = """
            execute as @s run say hello
            scoreboard players set @s dummy 1
        """.trimIndent()
        myFixture.configureByText("test.mcfunction", input)
        
        val annotations = myFixture.doHighlighting(HighlightSeverity.INFORMATION)
        
        // 'execute' should be FLOW_KEYWORD
        val executeAnn = annotations.find { myFixture.file.text.substring(it.startOffset, it.endOffset) == "execute" }
        assertEquals(McFunctionSyntaxHighlighter.FLOW_KEYWORD, executeAnn?.forcedTextAttributesKey)
        
        // 'as' should be SUB_COMMAND
        val asAnn = annotations.find { myFixture.file.text.substring(it.startOffset, it.endOffset) == "as" }
        assertEquals(McFunctionSyntaxHighlighter.SUB_COMMAND, asAnn?.forcedTextAttributesKey)

        // 'run' should be FLOW_KEYWORD
        val runAnn = annotations.find { myFixture.file.text.substring(it.startOffset, it.endOffset) == "run" }
        assertEquals(McFunctionSyntaxHighlighter.FLOW_KEYWORD, runAnn?.forcedTextAttributesKey)

        // 'say' after 'run' should be MAJOR_COMMAND
        val sayAnn = annotations.find { myFixture.file.text.substring(it.startOffset, it.endOffset) == "say" }
        assertEquals(McFunctionSyntaxHighlighter.MAJOR_COMMAND, sayAnn?.forcedTextAttributesKey)

        // 'scoreboard' should be MAJOR_COMMAND
        val scoreboardAnn = annotations.find { myFixture.file.text.substring(it.startOffset, it.endOffset) == "scoreboard" }
        assertEquals(McFunctionSyntaxHighlighter.MAJOR_COMMAND, scoreboardAnn?.forcedTextAttributesKey)

        // 'set' in 'scoreboard' should be SUB_COMMAND (not Major because it's a sub-verb)
        val setAnn = annotations.find { myFixture.file.text.substring(it.startOffset, it.endOffset) == "set" }
        assertEquals(McFunctionSyntaxHighlighter.SUB_COMMAND, setAnn?.forcedTextAttributesKey)
    }
}
