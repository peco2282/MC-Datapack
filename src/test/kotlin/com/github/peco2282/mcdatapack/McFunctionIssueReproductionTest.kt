package com.github.peco2282.mcdatapack

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.PsiErrorElement
import org.junit.Test

class McFunctionIssueReproductionTest : BasePlatformTestCase() {
    @Test
    fun testGiveWithComponents() {
        val input = """give @s netherite_sword[attribute_modifiers={modifiers:[{type:"minecraft:generic.attack_damage",amount:1000000000,operation:"add_value",id:"base_damage",slot:"mainhand"}]},enchantments={levels:{"minecraft:sharpness":255,"minecraft:looting":10}},rarity="epic",custom_name='{"text":"神の裁き","color":"gold","bold":true}']"""
        val file = myFixture.configureByText("give.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            System.err.println("[DEBUG_LOG] PSI Tree:\n$sb")
            for (error in errors) {
                System.err.println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        assertTrue("give command should not have parse errors, but found ${errors.size}", errors.isEmpty())
    }

    @Test
    fun testSummonWithNbtStringList() {
        val input = """summon text_display ~ ~2 ~ {tag:["hp_bar"],billboard:"center"}"""
        val file = myFixture.configureByText("summon.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            System.err.println("[DEBUG_LOG] PSI Tree:\n$sb")
            for (error in errors) {
                System.err.println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        assertTrue("summon command should not have parse errors, but found ${errors.size}", errors.isEmpty())
    }

    @Test
    fun testExecuteRunExecute() {
        val input = "execute as @a at @s anchored eyes run execute facing entity @s eyes run function my:namespace"
        val file = myFixture.configureByText("execute_run_execute.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            System.err.println("[DEBUG_LOG] PSI Tree:\n$sb")
            for (error in errors) {
                System.err.println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        assertTrue("execute run execute should not have parse errors, but found ${errors.size}", errors.isEmpty())
        // execute_command が正しく認識されているか確認
        val executeCommands = PsiTreeUtil.findChildrenOfType(file, com.github.peco2282.mcdatapack.language.psi.McFunctionExecuteCommand::class.java)
        assertTrue("Should have at least 2 execute commands (nested), but found ${executeCommands.size}", executeCommands.size >= 2)
    }

    @Test
    fun testIssueReproduction() {
        val input = """
            minecraft:stone[minecraft:custom_name={italic:0b,text:"黄の探知機"},minecraft:lore=[{color:"aqua",italic:0b,text:"価格 : 32コイン"},{color:"white",italic:0b,text:"黄色コレクションアイテムのすべての所有者がわかる"}],minecraft:custom_data={shop_item:true},minecraft:item_model="minecraft:music_disc_13"]

            give @s netherite_sword[attribute_modifiers={modifiers:[{type:"minecraft:generic.attack_damage",amount:1000000000,operation:"add_value",id:"base_damage",slot:"mainhand"}]},enchantments={levels:{"minecraft:sharpness":255,"minecraft:looting":10}},rarity="epic",custom_name='{"text":"神の裁き","color":"gold","bold":true}']
        """.trimIndent()
        
        val file = myFixture.configureByText("issue.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            val logMsg = "[DEBUG_LOG] PSI Tree:\n$sb"
            System.err.println(logMsg)
            for (error in errors) {
                System.err.println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        
        assertTrue("Should not have parse errors, but found ${errors.size} errors", errors.isEmpty())
    }

    @Test
    fun testAttributeModifierAdd() {
        val input = "execute if data storage my_namespace:main {temp_val:1s} run attribute @s minecraft:generic.attack_damage modifier add 1-2-3-4-5 \"PowerBoost\" 5.0 add_value"
        val file = myFixture.configureByText("attribute_modifier.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            System.err.println("[DEBUG_LOG] PSI Tree:\n$sb")
            for (error in errors) {
                System.err.println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        assertTrue("attribute modifier add command should not have parse errors, but found ${errors.size}", errors.isEmpty())
    }

    private fun dumpPsi(element: com.intellij.psi.PsiElement, sb: StringBuilder, indent: String = "") {
        sb.append(indent).append(element.toString()).append(" ('").append(element.text.replace("\n", "\\n")).append("')").append("\n")
        for (child in element.children) {
            dumpPsi(child, sb, indent + "  ")
        }
    }
}
