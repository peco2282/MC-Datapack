package com.github.peco2282.mcdatapack.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class McFunctionFileType : LanguageFileType(McFunctionLanguage.INSTANCE) {

  override fun getName(): String = "Minecraft Function"
  override fun getDescription(): String = "Minecraft Function file"
  override fun getDefaultExtension(): String = "mcfunction"
  override fun getIcon(): Icon = McFunctionIcons.FILE
}

val INSTANCE = McFunctionFileType()