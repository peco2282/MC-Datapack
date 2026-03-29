package com.github.peco2282.mcdatapack.language.formatting

import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class McFunctionPairedBraceMatcher : PairedBraceMatcher {
  override fun getPairs(): Array<BracePair> = arrayOf(
    BracePair(McFunctionTypes.LBRACK, McFunctionTypes.RBRACK, false),
    BracePair(McFunctionTypes.LBRACE, McFunctionTypes.RBRACE, true)
  )

  override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

  override fun getCodeConstructStart(file: PsiFile?, lbraceOffset: Int): Int = lbraceOffset
}
