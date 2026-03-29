package com.github.peco2282.mcdatapack.project

import com.github.peco2282.mcdatapack.MyBundle
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBTextField
import java.awt.GridLayout
import javax.swing.JComponent
import javax.swing.JPanel

class McDatapackModuleWizardStep(private val builder: McDatapackModuleBuilder) : ModuleWizardStep() {
    private val namespaceField = JBTextField(builder.namespace)
    private val packFormatField = JBTextField(builder.packFormat.toString())
    private val descriptionField = JBTextField(builder.packDescription)
    private val includeLoadTickCheckBox = JBCheckBox(MyBundle.message("label.project.include_load_tick"), builder.includeLoadTick)

    override fun getComponent(): JComponent {
        val panel = JPanel(GridLayout(4, 1))
        panel.add(LabeledComponent.create(namespaceField, MyBundle.message("label.project.namespace")))
        panel.add(LabeledComponent.create(packFormatField, MyBundle.message("label.project.pack_format")))
        panel.add(LabeledComponent.create(descriptionField, MyBundle.message("label.project.description")))
        panel.add(includeLoadTickCheckBox)
        return panel
    }

    override fun updateDataModel() {
        builder.namespace = namespaceField.text
        builder.packFormat = packFormatField.text.toIntOrNull() ?: 48
        builder.packDescription = descriptionField.text
        builder.includeLoadTick = includeLoadTickCheckBox.isSelected
    }
}
