package com.github.peco2282.mcdatapack.language.reference

import com.github.peco2282.mcdatapack.language.psi.McFunctionFile
import com.github.peco2282.mcdatapack.language.psi.McFunctionNamespacedId
import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.github.peco2282.mcdatapack.language.psi.McFunctionLexerAdapter

class McFunctionFindUsagesProvider : FindUsagesProvider {
  override fun getWordsScanner(): WordsScanner = DefaultWordsScanner(
    McFunctionLexerAdapter(),
    TokenSet.create(McFunctionTypes.NAMESPACED_ID),
    TokenSet.create(McFunctionTypes.COMMENT_TOKEN),
    TokenSet.EMPTY
  )

  override fun canFindUsagesFor(psiElement: PsiElement): Boolean =
    psiElement is McFunctionFile || psiElement is McFunctionNamespacedId

  override fun getHelpId(psiElement: PsiElement): String? = null

  override fun getType(element: PsiElement): String = when (element) {
    is McFunctionFile -> "mcfunction file"
    is McFunctionNamespacedId -> "namespaced id"
    else -> ""
  }

  override fun getDescriptiveName(element: PsiElement): String = when (element) {
    is McFunctionFile -> element.name
    is McFunctionNamespacedId -> element.text
    is PsiNamedElement -> element.name ?: ""
    else -> ""
  }

  override fun getNodeText(element: PsiElement, useFullName: Boolean): String =
    getDescriptiveName(element)
}
