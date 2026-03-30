package com.github.peco2282.mcdatapack.language.formatting

import com.github.peco2282.mcdatapack.language.psi.McFunctionLanguage
import com.intellij.application.options.IndentOptionsEditor
import com.intellij.application.options.SmartIndentOptionsEditor
import com.intellij.lang.Language
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider

class McFunctionLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
  override fun getLanguage(): Language = McFunctionLanguage.INSTANCE

  override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
    if (settingsType == SettingsType.INDENT_SETTINGS) {
      consumer.showAllStandardOptions()
    }
  }

  override fun getIndentOptionsEditor(): IndentOptionsEditor = SmartIndentOptionsEditor()

  override fun getDefaultCommonSettings(): CommonCodeStyleSettings {
    val defaultSettings = CommonCodeStyleSettings(language)
    val indentOptions = defaultSettings.initIndentOptions()
    indentOptions.INDENT_SIZE = 0
    indentOptions.TAB_SIZE = 0
    indentOptions.CONTINUATION_INDENT_SIZE = 0
    return defaultSettings
  }

  override fun getCodeSample(settingsType: SettingsType): String {
    return """
      # Example function
      execute as @a at @s run say hello
      give @p diamond 1
      
      execute as @e[type=zombie] run {
          say I am a zombie
      }
    """.trimIndent()
  }
}
