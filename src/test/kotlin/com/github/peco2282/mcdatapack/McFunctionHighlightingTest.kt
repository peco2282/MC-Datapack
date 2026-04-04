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
}
