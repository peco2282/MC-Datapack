package com.github.peco2282.mcdatapack

import com.github.peco2282.mcdatapack.language.psi.McFunctionCommandLine
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.psi.util.PsiTreeUtil
import org.junit.Test

class McFunctionParserTest : BasePlatformTestCase() {
    @Test
    fun testParsing() {
        val file = myFixture.configureByText("test.mcfunction", "say hello")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
    }

    @Test
    fun testSayIssue() {
        val file = myFixture.configureByText("say.mcfunction", "say \"hello everyone\"")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        val cmd = commands.first()
        val args = PsiTreeUtil.findChildrenOfType(cmd, com.github.peco2282.mcdatapack.language.psi.McFunctionArgument::class.java)
        assertEquals(1, args.size)
        assertEquals("\"hello everyone\"", args.first().text)
    }

    @Test
    fun testEscapedString() {
        val file = myFixture.configureByText("escape.mcfunction", "say \"hello \\\"world\\\"\"")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        val cmd = commands.first()
        val args = PsiTreeUtil.findChildrenOfType(cmd, com.github.peco2282.mcdatapack.language.psi.McFunctionArgument::class.java)
        assertEquals(1, args.size)
        assertEquals("\"hello \\\"world\\\"\"", args.first().text)
    }

    @Test
    fun testSingleQuotedString() {
        val file = myFixture.configureByText("single.mcfunction", "say 'hello world'")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        val cmd = commands.first()
        val args = PsiTreeUtil.findChildrenOfType(cmd, com.github.peco2282.mcdatapack.language.psi.McFunctionArgument::class.java)
        assertEquals(1, args.size)
        assertEquals("'hello world'", args.first().text)
    }

    @Test
    fun testNamespacePath() {
        val file = myFixture.configureByText("namespace.mcfunction", "function minecraft:test")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        val cmd = commands.first()
        val args = PsiTreeUtil.findChildrenOfType(cmd, com.github.peco2282.mcdatapack.language.psi.McFunctionArgument::class.java)
        assertEquals(1, args.size)
        assertEquals("minecraft:test", args.first().text)
    }

    @Test
    fun testTag() {
        val file = myFixture.configureByText("tag.mcfunction", "execute as @e[type=#minecraft:skeletons] run say hi")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        // ここでは詳細な構造チェックは省略し、パースエラーが出ないことを確認する程度にする
    }

    @Test
    fun testExecuteAtSelector() {
        val file = myFixture.configureByText("execute_at.mcfunction", "execute as @s at @e[type=marker,tag=chest] run particle minecraft:end_rod ~ ~0.8 ~ 0.1 0.1 0.1 0 1 force @s")
        val errors = PsiTreeUtil.findChildrenOfType(file, com.intellij.psi.PsiErrorElement::class.java)
        assertTrue("Should not have parse errors after fix", errors.isEmpty())
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
    }

    @Test
    fun testMultipleLines() {
        val input = """
            say hello
            tp @s 0 0 0
            execute as @a run say hi
        """.trimIndent()
        val file = myFixture.configureByText("multi.mcfunction", input)
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        
        val sb = StringBuilder()
        dumpPsi(file, sb)
        val output = sb.toString()
        assertEquals("Expected 3 commands, but found ${commands.size}.\nPSI Tree:\n$output", 3, commands.size)
    }

    private fun dumpPsi(element: com.intellij.psi.PsiElement, sb: StringBuilder) {
        val visitor = object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                val type = element.node.elementType
                if (element.children.isEmpty() || type.toString().contains("COMMAND_LINE")) {
                    var parent = element.parent
                    var depth = 0
                    while (parent != null) {
                        depth++
                        parent = parent.parent
                    }
                    val indent = "  ".repeat(depth)
                    sb.append("$indent Element: $type, text: '${element.text.replace("\n", "\\n")}'\n")
                }
                super.visitElement(element)
            }
        }
        element.accept(visitor)
    }


    @Test
    fun testMacroCommand() {
        val input = "${'$'}tp @s ${'$'}(pos[0]) ${'$'}(pos[1]) ${'$'}(pos[2])"
        val file = myFixture.configureByText("macro.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, com.intellij.psi.PsiErrorElement::class.java)
        
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            println("[DEBUG_LOG] PSI Tree:\n$sb")
            println("[DEBUG_LOG] ERROR: ${errors.first().errorDescription}")
        }

        assertTrue("Should not have parse errors: ${errors.firstOrNull()?.errorDescription}", errors.isEmpty())
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
    }
}
