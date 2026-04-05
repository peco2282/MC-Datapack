package com.github.peco2282.mcdatapack.language.inlay

import com.github.peco2282.mcdatapack.language.psi.*
import com.intellij.codeInsight.hints.*
import com.intellij.codeInsight.hints.presentation.InlayPresentation
import com.intellij.codeInsight.hints.presentation.PresentationFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import javax.swing.JPanel

@Suppress("UnstableApiUsage")
class McFunctionInlayHintsProvider : InlayHintsProvider<McFunctionInlayHintsProvider.Settings> {

  data class Settings(var showCommandHints: Boolean = true, var showCoordinateHints: Boolean = true)

  override val key: SettingsKey<Settings> = SettingsKey("mcfunction.inlay.hints")
  override val name: String = "McFunction Inlay Hints"
  override val previewText: String = "execute as @a at @s run function namespace:path"

  override fun createSettings(): Settings = Settings()

  override fun createConfigurable(settings: Settings): ImmediateConfigurable = object : ImmediateConfigurable {
    override fun createComponent(listener: ChangeListener) = JPanel()
  }

  override fun getCollectorFor(
    file: PsiFile,
    editor: Editor,
    settings: Settings,
    sink: InlayHintsSink
  ): InlayHintsCollector? {
    if (file.language !is McFunctionLanguage) return null
    return McFunctionInlayHintsCollector(editor, settings)
  }

  inner class McFunctionInlayHintsCollector(
    private val editor: Editor,
    private val settings: Settings
  ) : FactoryInlayHintsCollector(editor) {

    override fun collect(element: PsiElement, editor: Editor, sink: InlayHintsSink): Boolean {
      if (settings.showCommandHints) {
        collectCommandHints(element, sink, factory)
      }
      if (settings.showCoordinateHints) {
        collectCoordinateHints(element, sink, factory)
      }
      return true
    }

    private fun collectCommandHints(element: PsiElement, sink: InlayHintsSink, factory: PresentationFactory) {
      if (element !is McFunctionCommand) return

      val firstChild = element.firstChild ?: return
      val tokenType = firstChild.node.elementType

      val hint: String? = when (tokenType) {
        McFunctionTypes.EXECUTE_TOKEN -> buildExecuteHint(element)
        McFunctionTypes.GIVE_TOKEN -> "<selector> <item> [count]"
        McFunctionTypes.SUMMON_TOKEN -> "<entity> <pos> [nbt]"
        McFunctionTypes.SETBLOCK_TOKEN -> "<pos> <block> [mode]"
        McFunctionTypes.FILL_TOKEN -> "<from> <to> <block> [mode]"
        McFunctionTypes.FUNCTION_TOKEN -> "<namespace:path>"
        McFunctionTypes.EFFECT_TOKEN -> "give|clear <selector> <effect> [duration] [amplifier]"
        McFunctionTypes.ENCHANT_TOKEN -> "<selector> <enchantment> [level]"
        McFunctionTypes.KILL_TOKEN -> "[selector]"
        McFunctionTypes.TELEPORT_TOKEN -> "<selector> <pos>|<destination>"
        else -> null
      }

      if (hint != null) {
        val presentation = factory.smallText(hint)
        val offset = element.textRange.endOffset
        sink.addInlineElement(offset, true, presentation, false)
      }
    }

    private fun buildExecuteHint(element: McFunctionCommand): String {
      val text = element.text.lowercase()
      return when {
        text.contains(" as ") && text.contains(" at ") -> "as <selector> at <pos> run <command>"
        text.contains(" as ") -> "as <selector> run <command>"
        text.contains(" at ") -> "at <pos> run <command>"
        text.contains(" if ") -> "if <condition> run <command>"
        text.contains(" unless ") -> "unless <condition> run <command>"
        text.contains(" in ") -> "in <dimension> run <command>"
        else -> "... run <command>"
      }
    }

    private fun collectCoordinateHints(element: PsiElement, sink: InlayHintsSink, factory: PresentationFactory) {
      if (element !is McFunctionCoordinate) return

      val parent = element.parent ?: return
      val siblings = PsiTreeUtil.getChildrenOfType(parent, McFunctionCoordinate::class.java) ?: return
      val index = siblings.indexOf(element)

      val label: String = when (index) {
        0 -> "x"
        1 -> "y"
        2 -> "z"
        else -> return
      }

      val presentation: InlayPresentation = factory.smallText("$label:")
      sink.addInlineElement(element.textRange.startOffset, false, presentation, false)
    }
  }
}
