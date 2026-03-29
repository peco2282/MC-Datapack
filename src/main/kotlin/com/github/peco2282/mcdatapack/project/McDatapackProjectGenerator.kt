package com.github.peco2282.mcdatapack.project

import com.github.peco2282.mcdatapack.MyBundle
import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.DirectoryProjectGeneratorBase
import com.intellij.platform.ProjectGeneratorPeer
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBTextField
import java.awt.GridLayout
import javax.swing.Icon
import javax.swing.JComponent
import javax.swing.JPanel

class McDatapackProjectGenerator : DirectoryProjectGeneratorBase<McDatapackProjectSettings>() {
  override fun getName(): String = MyBundle.message("template.name")

  override fun getLogo(): Icon? = null

  override fun generateProject(
    project: Project,
    baseDir: VirtualFile,
    settings: McDatapackProjectSettings,
    module: Module
  ) {
    val namespace = settings.namespace.ifBlank { "mynamespace" }

    // pack.mcmeta
    val mcmetaFile = baseDir.createChildData(this, "pack.mcmeta")
    mcmetaFile.setBinaryContent(
      """
            {
                "pack": {
                    "pack_format": ${settings.packFormat},
                    "description": "${settings.description}"
                }
            }
        """.trimIndent().toByteArray()
    )

    // data/namespace/function/
    val dataDir = baseDir.createChildDirectory(this, "data")
    val namespaceDir = dataDir.createChildDirectory(this, namespace)
    val functionDir = namespaceDir.createChildDirectory(this, "function")

    if (settings.includeLoadTick) {
      val mainFile = functionDir.createChildData(this, "tick.mcfunction")
      mainFile.setBinaryContent("# Tick function".toByteArray())

      val loadFile = functionDir.createChildData(this, "load.mcfunction")
      loadFile.setBinaryContent("# Load function".toByteArray())

      // data/minecraft/tags/functions/load.json & tick.json
      val minecraftDir = dataDir.createChildDirectory(this, "minecraft")
      val tagsDir = minecraftDir.createChildDirectory(this, "tags")
      val tagsFunctionDir = tagsDir.createChildDirectory(this, "functions")

      val tickJson = tagsFunctionDir.createChildData(this, "tick.json")
      tickJson.setBinaryContent(
        """
                {
                    "values": [
                        "$namespace:tick"
                    ]
                }
            """.trimIndent().toByteArray()
      )

      val loadJson = tagsFunctionDir.createChildData(this, "load.json")
      loadJson.setBinaryContent(
        """
                {
                    "values": [
                        "$namespace:load"
                    ]
                }
            """.trimIndent().toByteArray()
      )
    }
  }

  override fun createPeer(): ProjectGeneratorPeer<McDatapackProjectSettings> = McDatapackProjectGeneratorPeer()
}

class McDatapackProjectGeneratorPeer : ProjectGeneratorPeer<McDatapackProjectSettings> {
  private val namespaceField = JBTextField("mynamespace")
  private val packFormatField = JBTextField("48")
  private val descriptionField = JBTextField(MyBundle.message("template.description"))
  private val includeLoadTickCheckBox = JBCheckBox(MyBundle.message("label.project.include_load_tick"), true)

  private val settings = McDatapackProjectSettings()

  override fun getSettings(): McDatapackProjectSettings {
    settings.namespace = namespaceField.text
    settings.packFormat = packFormatField.text.toIntOrNull() ?: 48
    settings.description = descriptionField.text
    settings.includeLoadTick = includeLoadTickCheckBox.isSelected
    return settings
  }

  override fun getComponent(): JComponent {
    val panel = JPanel(GridLayout(4, 1))
    panel.add(LabeledComponent.create(namespaceField, MyBundle.message("label.project.namespace")))
    panel.add(LabeledComponent.create(packFormatField, MyBundle.message("label.project.pack_format")))
    panel.add(LabeledComponent.create(descriptionField, MyBundle.message("label.project.description")))
    panel.add(includeLoadTickCheckBox)
    return panel
  }

  override fun buildUI(settingsStep: SettingsStep) {
    settingsStep.addSettingsComponent(
      LabeledComponent.create(
        namespaceField,
        MyBundle.message("label.project.namespace")
      )
    )
    settingsStep.addSettingsComponent(
      LabeledComponent.create(
        packFormatField,
        MyBundle.message("label.project.pack_format")
      )
    )
    settingsStep.addSettingsComponent(
      LabeledComponent.create(
        descriptionField,
        MyBundle.message("label.project.description")
      )
    )
    settingsStep.addSettingsComponent(includeLoadTickCheckBox)
  }

  override fun validate(): ValidationInfo? = null
  override fun isBackgroundJobRunning(): Boolean = false
  override fun addSettingsListener(p0: ProjectGeneratorPeer.SettingsListener) {
  }
}

data class McDatapackProjectSettings(
  var namespace: String = "mynamespace",
  var packFormat: Int = 48,
  var description: String = "Minecraft Datapack Project",
  var includeLoadTick: Boolean = true
)
