package com.github.peco2282.mcdatapack.language.template

import com.github.peco2282.mcdatapack.language.psi.McFunctionLanguage
import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType

class McFunctionTemplateContextType : TemplateContextType("McFunction") {
  override fun isInContext(templateActionContext: TemplateActionContext): Boolean {
    return templateActionContext.file.language is McFunctionLanguage
  }
}
