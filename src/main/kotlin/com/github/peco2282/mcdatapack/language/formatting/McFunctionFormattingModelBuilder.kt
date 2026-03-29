package com.github.peco2282.mcdatapack.language.formatting

import com.github.peco2282.mcdatapack.language.psi.McFunctionLanguage
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings

class McFunctionFormattingModelBuilder : FormattingModelBuilder {
  override fun createModel(formattingContext: FormattingContext): FormattingModel {
    val settings = formattingContext.codeStyleSettings
    val spacingBuilder = createSpacingBuilder(settings)
    return FormattingModelProvider.createFormattingModelForPsiFile(
      formattingContext.containingFile,
      McFunctionBlock(
        formattingContext.node,
        Wrap.createWrap(WrapType.NONE, false),
        Alignment.createAlignment(),
        spacingBuilder
      ),
      settings
    )
  }

  private fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
    return SpacingBuilder(settings, McFunctionLanguage.INSTANCE)
      // コロンの後はスペースを入れる（JSON等）
      .after(McFunctionTypes.COLON).spaceIf(true)
      // カンマの後はスペースを入れる
      .after(McFunctionTypes.COMMA).spaceIf(true)
      // 演算子（.. など）の周りはスペースを入れない（MC的な慣習）
      .around(McFunctionTypes.DOTDOT_TOKEN).none()
      // [ ] { } の内側は適宜（今回はシンプルにスペースなし）
      .after(McFunctionTypes.LBRACE).none()
      .before(McFunctionTypes.RBRACE).none()
      .after(McFunctionTypes.LBRACK).none()
      .before(McFunctionTypes.RBRACK).none()
  }
}
