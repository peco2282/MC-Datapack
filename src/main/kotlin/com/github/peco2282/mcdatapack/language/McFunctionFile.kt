package com.github.peco2282.mcdatapack.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class McFunctionFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, McFunctionLanguage.INSTANCE) {
    override fun getFileType(): FileType = McFunctionFileType.INSTANCE
    override fun toString(): String = "Minecraft Function File"
}
