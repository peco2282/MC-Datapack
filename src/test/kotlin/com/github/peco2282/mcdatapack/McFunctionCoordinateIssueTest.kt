package com.github.peco2282.mcdatapack

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.psi.util.PsiTreeUtil
import com.github.peco2282.mcdatapack.language.psi.McFunctionCoordinate
import org.junit.Test

class McFunctionCoordinateIssueTest : BasePlatformTestCase() {

    @Test
    fun testReturnCommandCoordinateIssue() {
        val input = "return 1"
        val file = myFixture.configureByText("test.mcfunction", input)
        
        val sb = StringBuilder()
        dumpPsi(file, sb)
        val psiTree = sb.toString()
        
        val coordinates = PsiTreeUtil.findChildrenOfType(file, McFunctionCoordinate::class.java)
        // 1 は座標として認識されるべきではない
        for (coord in coordinates) {
            if (coord.text == "1") {
                fail("Value '1' in 'return 1' should not be a McFunctionCoordinate. PSI:\n$psiTree")
            }
        }
    }

    @Test
    fun testParticleCommandCoordinateIssue() {
        val input = "particle flame ~ ~1 ~ 0.5 0.5 0.5 0.1 10"
        val file = myFixture.configureByText("test.mcfunction", input)
        
        val sb = StringBuilder()
        dumpPsi(file, sb)
        // エラーメッセージに含める
        val psiTree = sb.toString()
        
        val coordinates = PsiTreeUtil.findChildrenOfType(file, McFunctionCoordinate::class.java)
        
        val coordinateTexts = coordinates.map { it.text }
        
        // 0.5 が座標として誤認されていないか確認
        assertFalse("0.5 should not be a coordinate in particle delta. PSI:\n$psiTree", coordinateTexts.contains("0.5"))
    }

    @Test
    fun testParticleCommandWithAbsoluteCoordinates() {
        // 全く ~ や ^ を含まない座標の場合も、particle の <pos> 部分であれば座標として認識されるべき
        val input = "particle flame 1 2 3 0.5 0.5 0.5 0.1 10"
        val file = myFixture.configureByText("test.mcfunction", input)
        
        val sb = StringBuilder()
        dumpPsi(file, sb)
        val psiTree = sb.toString()
        
        // McFunctionCoordinateOrNumeric クラスが生成されているはずなので、それを探す
        // もしクラス名が異なる場合は dumpPsi の結果を見て調整する
        val elements = PsiTreeUtil.findChildrenOfType(file, com.intellij.psi.PsiElement::class.java)
            .filter { it.toString().contains("CoordinateOrNumeric") }
        
        val elementTexts = elements.map { it.text }
        
        // 1 2 3 は CoordinateOrNumeric であるべき
        assertTrue("1 should be a coordinate_or_numeric in particle pos. PSI:\n$psiTree", elementTexts.contains("1"))
        assertTrue("2 should be a coordinate_or_numeric in particle pos", elementTexts.contains("2"))
        assertTrue("3 should be a coordinate_or_numeric in particle pos", elementTexts.contains("3"))
        
        // 0.5 0.5 0.5 (delta) は CoordinateOrNumeric であるべきではない
        assertFalse("0.5 should not be a coordinate_or_numeric in particle delta", elementTexts.contains("0.5"))
    }

    private fun dumpPsi(element: com.intellij.psi.PsiElement, sb: StringBuilder, indent: String = "") {
        sb.append(indent).append(element.toString()).append(" ('").append(element.text.replace("\n", "\\n")).append("')\n")
        for (child in element.children) {
            dumpPsi(child, sb, "$indent  ")
        }
    }
}
