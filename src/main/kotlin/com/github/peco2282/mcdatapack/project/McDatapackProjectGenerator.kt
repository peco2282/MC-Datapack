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
import javax.swing.DefaultComboBoxModel
import javax.swing.Icon
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JPanel

/** Minecraft バージョンと対応する pack_format のマッピング */
enum class MinecraftVersion(val displayName: String, val packFormat: IntArray) {
  V1_13("1.13", 4),
  V1_14("1.14", 4),
  V1_15("1.15", 5),
  V1_16("1.16", 6),
  V1_17("1.17", 7),
  V1_18("1.18", 8),
  V1_18_2("1.18.2", 9),
  V1_19("1.19", 10),
  V1_19_4("1.19.4", 12),
  V1_20("1.20", 15),
  V1_20_2("1.20.2", 18),
  V1_20_3("1.20.3", 26),
  V1_20_4("1.20.4", 26),
  V1_20_5("1.20.5", 41),
  V1_20_6("1.20.6", 41),
  V1_21("1.21", 48),
  V1_21_1("1.21.1", 48),
  V1_21_2("1.21.2", 57),
  V1_21_3("1.21.3", 57),
  V1_21_4("1.21.4", 61),
  V1_21_5("1.21.5", 71),
  V1_21_6("1.21.6", 80),
  V1_21_7("1.21.7", 81),
  V1_21_8("1.21.8", 81),
  V1_21_9("1.21.9", 88.0),
  V1_21_10("1.21.10", 94.1),
  V1_21_11("1.21.11", 101.1),;

  init {
    // packFormat は Minecraft のバージョンによって異なるため、整数配列で保持
    require(packFormat.isNotEmpty()) { "packFormat must have at least one value" }
    require(packFormat.all { it > 0 }) { "packFormat must be positive integers" }
    require(packFormat.size <= 2) { "packFormat must have at most two values" }
  }

  constructor(displayName: String, packFormat: Int) : this(displayName, intArrayOf(packFormat))
  constructor(displayName: String, packFormat: Double) : this(displayName, packFormat.toString().split(".").map { it.toInt() }.toIntArray())

  override fun toString(): String = displayName
}

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
                    "pack_format": ${settings.pack},
                    "description": "${settings.description}"
                }
            }
        """.trimIndent().toByteArray()
    )

    // data/namespace/functions/ (1.21以降は "functions")
    val dataDir = baseDir.createChildDirectory(this, "data")
    val namespaceDir = dataDir.createChildDirectory(this, namespace)
    val functionDir = namespaceDir.createChildDirectory(this, "functions")

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
  private val descriptionField = JBTextField(MyBundle.message("template.description"))
  private val includeLoadTickCheckBox = JBCheckBox(MyBundle.message("label.project.include_load_tick"), true)
  private val versionComboBox = JComboBox(DefaultComboBoxModel(MinecraftVersion.entries.toTypedArray())).apply {
    selectedItem = MinecraftVersion.V1_21_4
  }

  private val settings = McDatapackProjectSettings()

  override fun getSettings(): McDatapackProjectSettings {
    settings.namespace = namespaceField.text
    settings.packFormat = (versionComboBox.selectedItem as? MinecraftVersion)?.packFormat ?: MinecraftVersion.V1_21_4.packFormat
    settings.description = descriptionField.text
    settings.includeLoadTick = includeLoadTickCheckBox.isSelected
    return settings
  }

  override fun getComponent(): JComponent {
    val panel = JPanel(GridLayout(4, 1))
    panel.add(LabeledComponent.create(namespaceField, MyBundle.message("label.project.namespace")))
    panel.add(LabeledComponent.create(versionComboBox, MyBundle.message("label.project.minecraft_version")))
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
        versionComboBox,
        MyBundle.message("label.project.minecraft_version")
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

  override fun validate(): ValidationInfo? {
    val namespace = namespaceField.text.trim()
    if (namespace.isBlank()) {
      return ValidationInfo(MyBundle.message("validation.namespace.empty"), namespaceField)
    }
    if (!namespace.matches(Regex("[a-z0-9_\\-.:]+"))) {
      return ValidationInfo(MyBundle.message("validation.namespace.invalid"), namespaceField)
    }
    return null
  }

  override fun isBackgroundJobRunning(): Boolean = false
  override fun addSettingsListener(p0: ProjectGeneratorPeer.SettingsListener) {
  }
}

data class McDatapackProjectSettings(
  var namespace: String = "mynamespace",
  var packFormat: IntArray = MinecraftVersion.V1_21_4.packFormat,
  var description: String = "Minecraft Datapack Project",
  var includeLoadTick: Boolean = true
) {
  val pack: String = if (packFormat.size == 1) {
    packFormat[0].toString()
  } else {
    "[ ${packFormat[0]}, ${packFormat[1]} ]"
  }
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as McDatapackProjectSettings

    if (includeLoadTick != other.includeLoadTick) return false
    if (namespace != other.namespace) return false
    if (!packFormat.contentEquals(other.packFormat)) return false
    if (description != other.description) return false

    return true
  }

  override fun hashCode(): Int {
    var result = includeLoadTick.hashCode()
    result = 31 * result + namespace.hashCode()
    result = 31 * result + packFormat.contentHashCode()
    result = 31 * result + description.hashCode()
    return result
  }
}
