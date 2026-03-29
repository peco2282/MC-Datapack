package com.github.peco2282.mcdatapack.project

import com.github.peco2282.mcdatapack.MyBundle
import com.github.peco2282.mcdatapack.language.highlighting.McFunctionIcons
import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.Disposable
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class McDatapackModuleBuilder : ModuleBuilder() {
  override fun getGroupName(): String = MyBundle.message("project.group.name")
  override fun getPresentableName(): String = MyBundle.message("project.generator.name")
  override fun getNodeIcon(): Icon = McFunctionIcons.FILE
  override fun getModuleType(): ModuleType<*> = ModuleType.EMPTY

  var namespace: String = "mynamespace"
  var packFormat: Int = 48
  var packDescription: String = "Minecraft Datapack Project"
  var includeLoadTick: Boolean = true

  override fun setupRootModel(modifiableRootModel: ModifiableRootModel) {
    val root = doAddContentEntry(modifiableRootModel)?.file ?: return
    generateProject(root)
  }

  private fun generateProject(baseDir: VirtualFile) {
    val namespaceStr = namespace.ifBlank { "mynamespace" }

    // pack.mcmeta
    val mcmetaFile = baseDir.createChildData(this, "pack.mcmeta")
    mcmetaFile.setBinaryContent(
      """
            {
                "pack": {
                    "pack_format": $packFormat,
                    "description": "$packDescription"
                }
            }
            """.trimIndent().toByteArray()
    )

    // data/namespace/function/
    val dataDir = baseDir.createChildDirectory(this, "data")
    val namespaceDir = dataDir.createChildDirectory(this, namespaceStr)
    val functionDir = namespaceDir.createChildDirectory(this, "function")

    if (includeLoadTick) {
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
                        "$namespaceStr:tick"
                    ]
                }
                """.trimIndent().toByteArray()
      )

      val loadJson = tagsFunctionDir.createChildData(this, "load.json")
      loadJson.setBinaryContent(
        """
                {
                    "values": [
                        "$namespaceStr:load"
                    ]
                }
                """.trimIndent().toByteArray()
      )
    }
  }

  override fun getCustomOptionsStep(context: WizardContext?, parentDisposable: Disposable?): ModuleWizardStep {
    return McDatapackModuleWizardStep(this)
  }
}
