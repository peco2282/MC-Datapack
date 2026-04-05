package com.github.peco2282.mcdatapack.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement

class McFunctionFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, McFunctionLanguage.INSTANCE), PsiNamedElement {
  override fun getFileType(): FileType = INSTANCE
  override fun toString(): String = "Minecraft Function File"

  override fun getName(): String = virtualFile?.nameWithoutExtension ?: super.getName()

  override fun setName(name: String): PsiElement {
    val newName = if (name.endsWith(".mcfunction")) name else "$name.mcfunction"
    return super.setName(newName)
  }
}
