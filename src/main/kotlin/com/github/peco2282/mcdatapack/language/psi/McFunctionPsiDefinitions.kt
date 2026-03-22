package com.github.peco2282.mcdatapack.language.psi

import com.github.peco2282.mcdatapack.language.McFunctionLanguage
import com.intellij.psi.tree.IElementType

class McFunctionElementType(debugName: String) : IElementType(debugName, McFunctionLanguage.INSTANCE)

class McFunctionTokenType(debugName: String) : IElementType(debugName, McFunctionLanguage.INSTANCE) {
    override fun toString(): String = "McFunctionTokenType." + super.toString()
}
