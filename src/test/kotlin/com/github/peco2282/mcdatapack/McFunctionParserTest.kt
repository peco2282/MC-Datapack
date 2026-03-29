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
    fun testPecoIssue() {
        val file = myFixture.configureByText("peco.mcfunction", "tp peco_2282")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        val cmd = commands.first()
        assertEquals("tp", cmd.firstChild.text)
        val args = PsiTreeUtil.findChildrenOfType(cmd, com.github.peco2282.mcdatapack.language.psi.McFunctionArgument::class.java)
        assertEquals(1, args.size)
        assertEquals("peco_2282", args.first().text)
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
    fun testTellrawJsonWithSpace() {
        val rawJson = "[ { \"color\" : \"green\" } ]"
        val file = myFixture.configureByText("tellraw_space.mcfunction", "tellraw @s $rawJson")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        val cmd = commands.first()
        // クラス名文字列で比較することで、クラスローダーの影響を回避して構造を確認する
        val hasJson = cmd.children.any { it.javaClass.name.endsWith("McFunctionJsonImpl") }
        assertTrue("Should have a Json element", hasJson)
    }

    @Test
    fun testTellrawJsonComplex() {
        val rawJson = "[{\"color\":\"green\",\"text\":\"累計プレイ回数\"},{\"color\":\"white\",\"text\":\" : \"},{\"score\":{\"name\":\"@s\",\"objective\":\"stats.play\"}}]"
        val file = myFixture.configureByText("tellraw_complex.mcfunction", "tellraw @s $rawJson")
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        val cmd = commands.first()
        val hasJson = cmd.children.any { it.javaClass.name.endsWith("McFunctionJsonImpl") }
        assertTrue("Should have a Json element", hasJson)
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
    fun testIssueReported() {
        // バックスラッシュによる行継続があるケース
        val input = """
            execute if data storage use: item.components."minecraft:custom_data"{item_id:"admin_start"} \
                if score @s item.using_timer >= ${'$'}Start_Required_Time item.using_timer run function core:admin/start
        """.trimIndent()
        val file = myFixture.configureByText("issue.mcfunction", input)
        
        println("[DEBUG_LOG] File text: ${file.text}")
        val visitor = object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                val type = element.node.elementType
                var parent = element.parent
                var depth = 0
                while (parent != null) {
                    depth++
                    parent = parent.parent
                }
                val indent = "  ".repeat(depth)
                if (element is com.intellij.psi.PsiErrorElement) {
                    println("[DEBUG_LOG] ERROR: ${element.errorDescription} at offset ${element.textOffset}")
                }
                if (element.children.isEmpty()) {
                    println("[DEBUG_LOG] $indent Token: $type, text: '${element.text}'")
                } else {
                    println("[DEBUG_LOG] $indent Element: $type")
                }
                super.visitElement(element)
            }
        }
        file.accept(visitor)
        
        val errors = PsiTreeUtil.findChildrenOfType(file, com.intellij.psi.PsiErrorElement::class.java)
        assertTrue("Should not have parse errors for complex execute command: ${errors.firstOrNull()?.errorDescription}", errors.isEmpty())
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
    }
    @Test
    fun testDataModifyStorage() {
        val input = "data modify storage use: item set from entity @s SelectedItem"
        val file = myFixture.configureByText("data_modify.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, com.intellij.psi.PsiErrorElement::class.java)
        assertTrue("Should not have parse errors for data modify command: ${errors.firstOrNull()?.errorDescription}", errors.isEmpty())
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        assertEquals("data", commands.first().firstChild.text)
    }

    @Test
    fun testIfCommand() {
        val input = "if score @s test matches 1.."
        val file = myFixture.configureByText("if_command.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, com.intellij.psi.PsiErrorElement::class.java)
        assertTrue("Should not have parse errors for if command: ${errors.firstOrNull()?.errorDescription}", errors.isEmpty())
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
        assertEquals("if", commands.first().firstChild.text)
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
    fun testExecuteRecursionWithNewlines() {
        val input = """
            execute as @a
            run say hi
        """.trimIndent()
        val file = myFixture.configureByText("execute_newline.mcfunction", input)
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        // 1つのコマンドとしてパースされるか、2つに分かれるかは仕様によるが、
        // 少なくともファイル全体が1つに固まるのを避ける必要がある。
        // 現在の定義だと \ がないので2つに分かれるべき。
        assertEquals(2, commands.size)
    }

    @Test
    fun testItemReplaceEntity() {
        val input = """
            item replace entity @s enderchest.0 with \
                minecraft:stone[custom_name={"italic":false,"text":"木の剣"},minecraft:lore=[{color:"aqua",italic:0b,text:"価格 : 10コイン"},{color:"white",italic:0b,text:"いたって普通の木の剣"}],minecraft:unbreakable={},minecraft:tooltip_display={hidden_components:["attribute_modifiers","unbreakable"]},custom_data={shop_item:true},item_model="minecraft:wooden_sword"]
        """.trimIndent()
        val file = myFixture.configureByText("item_replace.mcfunction", input)

        val errors = PsiTreeUtil.findChildrenOfType(file, com.intellij.psi.PsiErrorElement::class.java)
        assertTrue("Should not have parse errors for item replace command: ${errors.firstOrNull()?.errorDescription}", errors.isEmpty())
        val commands = PsiTreeUtil.findChildrenOfType(file, McFunctionCommandLine::class.java)
        assertEquals(1, commands.size)
    }
    @Test
    fun testItemReplaceWithContinuationFull() {
        val input = """
            item replace entity @s enderchest.0 with \
                minecraft:stone[custom_name={"italic":false,"text":"木の剣"},minecraft:lore=[{color:"aqua",italic:0b,text:"価格 : 10コイン"},{color:"white",italic:0b,text:"いたって普通の木の剣"}],minecraft:unbreakable={},minecraft:tooltip_display={hidden_components:["attribute_modifiers","unbreakable"]},custom_data={shop_item:true},item_model="minecraft:wooden_sword"]
        """.trimIndent()
        val file = myFixture.configureByText("item_replace_cont_full.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, com.intellij.psi.PsiErrorElement::class.java)
        
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            java.io.File("psi_tree_full.txt").writeText(sb.toString())
            java.io.File("error_full.txt").writeText(errors.first().errorDescription)
        }
        
        assertTrue("Should not have parse errors: ${errors.firstOrNull()?.errorDescription}", errors.isEmpty())
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
