package com.github.peco2282.mcdatapack.language.highlighting

import com.github.peco2282.mcdatapack.language.psi.McFunctionLanguage
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.psi.PsiElement
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy
import com.intellij.spellchecker.tokenizer.Tokenizer
import com.intellij.spellchecker.tokenizer.TokenizerBase

class McFunctionSpellcheckingStrategy : SpellcheckingStrategy() {
  override fun isMyContext(element: PsiElement): Boolean =
    element.language == McFunctionLanguage.INSTANCE

  override fun getTokenizer(element: PsiElement): Tokenizer<PsiElement> {
    val type = element.node?.elementType
    return when (type) {
      McFunctionTypes.COMMENT_TOKEN -> TEXT_TOKENIZER
      McFunctionTypes.STRING_TOKEN -> TEXT_TOKENIZER
      McFunctionTypes.ARGUMENT_TOKEN -> TokenizerBase.create(McFunctionIdentifierSplitter)
      else -> EMPTY_TOKENIZER
    }
  }
}
