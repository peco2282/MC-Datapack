package com.github.peco2282.mcdatapack.language.psi

import com.github.peco2282.mcdatapack.language._McFunctionLexer
import com.intellij.lexer.FlexAdapter
import com.intellij.psi.tree.IElementType

class McFunctionLexerAdapter : FlexAdapter(_McFunctionLexer()) {
  override fun getTokenType(): IElementType? {
    val type = super.getTokenType() ?: return null
    if (type == McFunctionTypes.ARGUMENT_TOKEN) {
      val start = tokenStart
      val end = tokenEnd
      if (end > start) {
        val firstChar = bufferSequence[start]
        if (firstChar == '~' || firstChar == '^') {
          return McFunctionTypes.COORD_TOKEN
        }
      }
    }
    return type
  }
}
