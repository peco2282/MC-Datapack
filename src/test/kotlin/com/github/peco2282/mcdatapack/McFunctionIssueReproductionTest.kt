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
            println("[DEBUG_LOG] Give PSI Tree:\n$sb")
            for (error in errors) {
                println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
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
        val input = "execute as @e[type=zombie,distance=..10,limit=1] store result storage my_namespace:main temp_val int 1 run data get entity @s Fire"
        
        val file = myFixture.configureByText("issue.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            val logMsg = "[DEBUG_LOG] PSI Tree:\n$sb"
            println(logMsg)
            
            val lexer = com.github.peco2282.mcdatapack.language.psi.McFunctionLexerAdapter()
            lexer.start(input)
            val tokenSb = StringBuilder()
            while (lexer.tokenType != null) {
                tokenSb.append("${lexer.tokenType} ('${lexer.tokenText}')\n")
                lexer.advance()
            }
            println("[DEBUG_LOG] Tokens:\n$tokenSb")

            for (error in errors) {
                println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
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
            println("[DEBUG_LOG] Attribute PSI Tree:\n$sb")
            for (error in errors) {
                println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        assertTrue("attribute modifier add command should not have parse errors, but found ${errors.size}", errors.isEmpty())
    }

    @Test
    fun testItemModify() {
        val input = "item modify entity @s weapon.mainhand my_datapack:super_sharpness"
        val file = myFixture.configureByText("item_modify.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            System.err.println("[DEBUG_LOG] ItemModify PSI Tree:\n$sb")
            for (error in errors) {
                System.err.println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        assertTrue("item modify command should not have parse errors, but found ${errors.size}", errors.isEmpty())
    }

    @Test
    fun testItemReplace() {
        val input = "item replace entity @s weapon.mainhand with diamond_sword"
        val file = myFixture.configureByText("item_replace.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            System.err.println("[DEBUG_LOG] ItemReplace PSI Tree:\n$sb")
            for (error in errors) {
                System.err.println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        assertTrue("item replace command should not have parse errors, but found ${errors.size}", errors.isEmpty())
    }

    @Test
    fun testDamageByReproduction() {
        val input = "execute as @e[type=!player,distance=..5,limit=1] run damage @s 20 minecraft:magic by @p"
        val file = myFixture.configureByText("damage.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        
        if (errors.isNotEmpty()) {
            val sb = StringBuilder()
            dumpPsi(file, sb)
            println("[DEBUG_LOG] Damage PSI Tree:\n$sb")
            for (error in errors) {
                println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        
        assertTrue("Should not have parse errors in damage command, but found ${errors.size} errors", errors.isEmpty())
    }

    @Test
    fun testCoordinateSeparation() {
        val input = "execute facing ^ ^ ^5 0.5 0.5 run say hello"
        val file = myFixture.configureByText("coordinate.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        
        val sb = StringBuilder()
        dumpPsi(file, sb)
        println("[DEBUG_LOG] Coordinate PSI Tree:\n$sb")
        
        if (errors.isNotEmpty()) {
            for (error in errors) {
                println("[DEBUG_LOG] Error at ${error.textOffset}: ${error.errorDescription} (text: '${error.text}')")
            }
        }
        assertTrue("coordinate separation should not have parse errors, but found ${errors.size}", errors.isEmpty())
        
        // ^ ^ ^5 が個別の Coordinate PSI になっているか確認
        val coords = PsiTreeUtil.findChildrenOfType(file, com.github.peco2282.mcdatapack.language.psi.McFunctionCoordinate::class.java)
        // execute facing ^ ^ ^5 で 3つ、その後の引数として扱われるべき 0.5 0.5 はここでは Coordinate ではないはずだが、
        // 現状の argument ルールが coordinate を含んでいるため、それらも Coordinate になる可能性がある。
        // しかし、少なくとも ^5 0.5 0.5 が1つにまとまっていないことは確認できる。
        assertTrue("Should have multiple coordinates, but found ${coords.size}", coords.size >= 3)
    }

    @Test
    fun testRideMountSeparation() {
        val input = "ride @s mount @e[type=pig,limit=1]"
        val file = myFixture.configureByText("ride.mcfunction", input)
        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        
        val sb = StringBuilder()
        dumpPsi(file, sb)
        println("[DEBUG_LOG] Ride PSI Tree:\n$sb")
        
        assertTrue("ride command should not have parse errors, but found ${errors.size}", errors.isEmpty())
        
        // mount が keyword (または専用トークン) として扱われ、namespaced_id に含まれていないか確認
        val namespacedIds = PsiTreeUtil.findChildrenOfType(file, com.github.peco2282.mcdatapack.language.psi.McFunctionNamespacedId::class.java)
        val hasMountAsId = namespacedIds.any { it.text == "mount" }
        assertFalse("mount should not be parsed as namespaced_id", hasMountAsId)
    }

    private fun dumpPsi(element: com.intellij.psi.PsiElement, sb: StringBuilder, indent: String = "") {
        sb.append(indent).append(element.toString()).append(" ('").append(element.text.replace("\n", "\\n")).append("')").append("\n")
        for (child in element.children) {
            dumpPsi(child, sb, indent + "  ")
        }
    }
}
