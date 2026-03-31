package com.github.peco2282.mcdatapack

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.PsiErrorElement
import org.junit.Test

class McFunctionIssueReproductionTest : BasePlatformTestCase() {
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

    private fun dumpPsi(element: com.intellij.psi.PsiElement, sb: StringBuilder, indent: String = "") {
        sb.append(indent).append(element.toString()).append(" ('").append(element.text.replace("\n", "\\n")).append("')").append("\n")
        for (child in element.children) {
            dumpPsi(child, sb, indent + "  ")
        }
    }
}
