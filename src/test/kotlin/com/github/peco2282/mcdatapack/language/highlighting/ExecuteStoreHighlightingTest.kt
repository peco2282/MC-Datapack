package com.github.peco2282.mcdatapack.language.highlighting

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test

class ExecuteStoreHighlightingTest : BasePlatformTestCase() {
    @Test
    fun testExecuteStoreStorageHighlighting() {
        val input = """
            execute as @e[type=zombie,distance=..10,limit=1] store result storage my_namespace:main temp_val int 1 run data get entity @s Fire
        """.trimIndent()
        myFixture.configureByText("test.mcfunction", input)
        
        val annotations = myFixture.doHighlighting(HighlightSeverity.INFORMATION)
        
        // my_namespace:main のハイライトを確認
        val nsAnnotations = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.NAMESPACE }
        val colonAnnotations = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.NAMESPACE_COLON }
        val argAnnotations = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.ARGUMENT }

        System.out.println("[DEBUG_LOG] Annotations for NAMESPACE:")
        nsAnnotations.forEach { 
            System.out.println("[DEBUG_LOG] range: ${it.startOffset}-${it.endOffset}, text: '${myFixture.file.text.substring(it.startOffset, it.endOffset)}'")
        }
        System.out.println("[DEBUG_LOG] Annotations for NAMESPACE_COLON:")
        colonAnnotations.forEach { 
            System.out.println("[DEBUG_LOG] range: ${it.startOffset}-${it.endOffset}, text: '${myFixture.file.text.substring(it.startOffset, it.endOffset)}'")
        }
        System.out.println("[DEBUG_LOG] Annotations for ARGUMENT:")
        argAnnotations.forEach { 
            System.out.println("[DEBUG_LOG] range: ${it.startOffset}-${it.endOffset}, text: '${myFixture.file.text.substring(it.startOffset, it.endOffset)}'")
        }

        // my_namespace:main が正しく分割されているか
        assertTrue("NAMESPACE should contain 'my_namespace'", nsAnnotations.any { myFixture.file.text.substring(it.startOffset, it.endOffset) == "my_namespace" })
        assertTrue("NAMESPACE_COLON should contain ':'", colonAnnotations.any { myFixture.file.text.substring(it.startOffset, it.endOffset) == ":" })
        assertTrue("ARGUMENT should contain 'main'", argAnnotations.any { myFixture.file.text.substring(it.startOffset, it.endOffset) == "main" })
    }

    @Test
    fun testMultipleCommandsNotMergedIntoOne() {
        // #5以降のコマンドが1つのコマンドとして認識されないことを確認
        val input = """
            execute as @e[type=!player,distance=..5,limit=1] run damage @s 20 minecraft:magic by @p
            execute as @e[type=armor_stand,distance=..3,limit=1] run ride @s mount @p
            data remove storage my_namespace:main temp_val
            return 1
            execute as @e[type=minecraft:zombie]
        """.trimIndent()
        myFixture.configureByText("test.mcfunction", input)

        val errors = myFixture.doHighlighting(HighlightSeverity.ERROR)
        System.out.println("[DEBUG_LOG] Error count: ${errors.size}")
        errors.forEach {
            System.out.println("[DEBUG_LOG] Error: '${myFixture.file.text.substring(it.startOffset, it.endOffset)}' at ${it.startOffset}-${it.endOffset}: ${it.description}")
        }

        // 各コマンドが独立してパースされ、エラーがないことを確認
        // (不完全なexecuteはエラーになりうるが、後続コマンドを飲み込まないことが重要)
        val psiFile = myFixture.file
        val children = psiFile.children
        System.out.println("[DEBUG_LOG] PSI children count: ${children.size}")
        children.forEach {
            System.out.println("[DEBUG_LOG] PSI child: ${it.node.elementType} = '${it.text.take(60)}'")
        }

        // 少なくとも5つのcommand_lineノードが存在するはず
        val commandLines = children.filter { it.node.elementType.toString() == "COMMAND_LINE" }
        System.out.println("[DEBUG_LOG] command_line count: ${commandLines.size}")
        assertTrue("Should have at least 5 command lines, got ${commandLines.size}", commandLines.size >= 5)
    }

    @Test
    fun testExecuteIfDataStorageHighlighting() {
        val input = """
            execute if data storage my_namespace:main Fire run data get entity @s Fire
        """.trimIndent()
        myFixture.configureByText("test.mcfunction", input)
        
        val annotations = myFixture.doHighlighting(HighlightSeverity.INFORMATION)
        
        val nsAnnotations = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.NAMESPACE }
        val colonAnnotations = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.NAMESPACE_COLON }
        val argAnnotations = annotations.filter { it.forcedTextAttributesKey == McFunctionSyntaxHighlighter.ARGUMENT }

        System.out.println("[DEBUG_LOG] (if data) Annotations for NAMESPACE:")
        nsAnnotations.forEach { 
            System.out.println("[DEBUG_LOG] range: ${it.startOffset}-${it.endOffset}, text: '${myFixture.file.text.substring(it.startOffset, it.endOffset)}'")
        }

        assertTrue("NAMESPACE should contain 'my_namespace'", nsAnnotations.any { myFixture.file.text.substring(it.startOffset, it.endOffset) == "my_namespace" })
        assertTrue("NAMESPACE_COLON should contain ':'", colonAnnotations.any { myFixture.file.text.substring(it.startOffset, it.endOffset) == ":" })
        assertTrue("ARGUMENT should contain 'main'", argAnnotations.any { myFixture.file.text.substring(it.startOffset, it.endOffset) == "main" })
    }
}
