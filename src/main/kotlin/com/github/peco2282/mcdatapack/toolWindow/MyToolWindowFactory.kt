package com.github.peco2282.mcdatapack.toolWindow

import com.github.peco2282.mcdatapack.language.highlighting.McFunctionIcons
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.content.ContentFactory
import java.awt.BorderLayout
import java.awt.Component
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

class MyToolWindowFactory : ToolWindowFactory {

  override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
    val panel = McFunctionToolWindowPanel(project)
    val content = ContentFactory.getInstance().createContent(panel.getContent(), "Functions", false)
    toolWindow.contentManager.addContent(content)
  }

  override fun shouldBeAvailable(project: Project) = true

  class McFunctionToolWindowPanel(private val project: Project) {

    fun getContent(): JComponent {
      val panel = JBPanel<JBPanel<*>>(BorderLayout())

      val titleLabel = JBLabel("  .mcfunction Files")
      titleLabel.border = BorderFactory.createEmptyBorder(4, 4, 4, 4)
      panel.add(titleLabel, BorderLayout.NORTH)

      val listModel = DefaultListModel<VirtualFile>()
      val fileList = JList(listModel)
      fileList.cellRenderer = McFunctionListCellRenderer()

      refreshFiles(listModel)

      fileList.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
          if (e.clickCount == 2) {
            val selected = fileList.selectedValue ?: return
            FileEditorManager.getInstance(project).openFile(selected, true)
          }
        }
      })

      val scrollPane = JBScrollPane(fileList)
      panel.add(scrollPane, BorderLayout.CENTER)

      val refreshButton = JButton("Refresh")
      refreshButton.addActionListener {
        refreshFiles(listModel)
      }
      panel.add(refreshButton, BorderLayout.SOUTH)

      return panel
    }

    private fun refreshFiles(listModel: DefaultListModel<VirtualFile>) {
      listModel.clear()
      val files = FilenameIndex.getAllFilesByExt(
        project, "mcfunction", GlobalSearchScope.projectScope(project)
      ).sortedBy { it.path }
      files.forEach { listModel.addElement(it) }
    }
  }

  private class McFunctionListCellRenderer : DefaultListCellRenderer() {
    override fun getListCellRendererComponent(
      list: JList<*>?,
      value: Any?,
      index: Int,
      isSelected: Boolean,
      cellHasFocus: Boolean
    ): Component {
      val component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
      if (value is VirtualFile && component is JLabel) {
        component.text = value.name
        component.toolTipText = value.path
        component.icon = McFunctionIcons.FILE
      }
      return component
    }
  }
}
