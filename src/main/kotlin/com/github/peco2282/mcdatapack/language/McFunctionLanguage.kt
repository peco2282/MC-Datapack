package com.github.peco2282.mcdatapack.language

import com.intellij.lang.Language
import com.intellij.psi.tree.IFileElementType

class McFunctionLanguage : Language("McFunction") {
  companion object {
    @JvmStatic
    val INSTANCE = McFunctionLanguage()

    @JvmStatic
    val FILE = IFileElementType(INSTANCE)
  }

  override fun getDisplayName(): String = "Minecraft Function"
}
