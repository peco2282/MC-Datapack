package com.github.peco2282.mcdatapack.toolWindow

import com.github.peco2282.mcdatapack.language.completion.McFunctionConstants
import com.github.peco2282.mcdatapack.language.highlighting.McFunctionIcons
import com.intellij.openapi.application.ReadAction
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
import java.awt.Font
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

class MyToolWindowFactory : ToolWindowFactory {

  override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
    val contentFactory = ContentFactory.getInstance()

    // タブ1: .mcfunction ファイル一覧
    val filesPanel = McFunctionFilesPanel(project)
    val filesContent = contentFactory.createContent(filesPanel.getContent(), "Functions", false)
    toolWindow.contentManager.addContent(filesContent)

    // タブ2: コマンド一覧
    val commandsPanel = McFunctionCommandsPanel()
    val commandsContent = contentFactory.createContent(commandsPanel.getContent(), "Commands", false)
    toolWindow.contentManager.addContent(commandsContent)

    // タブ3: ファイル構造ビュー
    val structurePanel = McFunctionStructurePanel(project)
    val structureContent = contentFactory.createContent(structurePanel.getContent(), "Structure", false)
    toolWindow.contentManager.addContent(structureContent)
  }

  override fun shouldBeAvailable(project: Project) = true

  // ---- タブ1: ファイル一覧 ----
  class McFunctionFilesPanel(private val project: Project) {

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
      refreshButton.addActionListener { refreshFiles(listModel) }
      panel.add(refreshButton, BorderLayout.SOUTH)

      return panel
    }

    private fun refreshFiles(listModel: DefaultListModel<VirtualFile>) {
      listModel.clear()
      val files = ReadAction.compute<Collection<VirtualFile>, Throwable> {
        FilenameIndex.getAllFilesByExt(
          project, "mcfunction", GlobalSearchScope.projectScope(project)
        ).sortedBy { it.path }
      }
      files.forEach { listModel.addElement(it) }
    }
  }

  // ---- タブ2: コマンド一覧 ----
  class McFunctionCommandsPanel {

    fun getContent(): JComponent {
      val panel = JBPanel<JBPanel<*>>(BorderLayout())

      val titleLabel = JBLabel("  Minecraft Commands")
      titleLabel.border = BorderFactory.createEmptyBorder(4, 4, 4, 4)
      panel.add(titleLabel, BorderLayout.NORTH)

      // 検索フィールド
      val searchField = JTextField()
      searchField.toolTipText = "コマンドを検索..."
      panel.add(searchField, BorderLayout.NORTH)

      val splitPane = JSplitPane(JSplitPane.VERTICAL_SPLIT)
      splitPane.resizeWeight = 0.4

      // コマンドリスト
      val listModel = DefaultListModel<String>()
      McFunctionConstants.COMMANDS.sorted().forEach { listModel.addElement(it) }
      val commandList = JList(listModel)
      commandList.selectionMode = ListSelectionModel.SINGLE_SELECTION
      splitPane.topComponent = JBScrollPane(commandList)

      // ドキュメント表示エリア
      val docArea = JTextArea()
      docArea.isEditable = false
      docArea.lineWrap = true
      docArea.wrapStyleWord = true
      docArea.border = BorderFactory.createEmptyBorder(4, 4, 4, 4)
      docArea.font = Font(Font.MONOSPACED, Font.PLAIN, 12)
      splitPane.bottomComponent = JBScrollPane(docArea)

      // コマンド選択時にドキュメントを表示
      commandList.addListSelectionListener {
        val selected = commandList.selectedValue ?: return@addListSelectionListener
        val doc = McFunctionConstants.COMMAND_DOCS[selected]
        if (doc != null) {
          docArea.text = "構文: ${doc.first}\n\n説明: ${doc.second}"
        } else {
          docArea.text = "/$selected\n\n(ドキュメントなし)"
        }
      }

      // 検索フィルタ
      searchField.document.addDocumentListener(object : javax.swing.event.DocumentListener {
        override fun insertUpdate(e: javax.swing.event.DocumentEvent) = filter()
        override fun removeUpdate(e: javax.swing.event.DocumentEvent) = filter()
        override fun changedUpdate(e: javax.swing.event.DocumentEvent) = filter()

        fun filter() {
          val query = searchField.text.lowercase()
          listModel.clear()
          McFunctionConstants.COMMANDS.sorted()
            .filter { it.contains(query) }
            .forEach { listModel.addElement(it) }
        }
      })

      panel.add(splitPane, BorderLayout.CENTER)
      return panel
    }
  }

  // ---- タブ3: ファイル構造ビュー ----
  class McFunctionStructurePanel(private val project: Project) {

    fun getContent(): JComponent {
      val panel = JBPanel<JBPanel<*>>(BorderLayout())

      val titleLabel = JBLabel("  Datapack Structure")
      titleLabel.border = BorderFactory.createEmptyBorder(4, 4, 4, 4)
      panel.add(titleLabel, BorderLayout.NORTH)

      val root = DefaultMutableTreeNode("Datapack")
      val treeModel = DefaultTreeModel(root)
      val tree = JTree(treeModel)
      tree.isRootVisible = true

      buildTree(root, treeModel)

      tree.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
          if (e.clickCount == 2) {
            val path = tree.selectionPath ?: return
            val node = path.lastPathComponent as? DefaultMutableTreeNode ?: return
            val userObj = node.userObject
            if (userObj is VirtualFile) {
              FileEditorManager.getInstance(project).openFile(userObj, true)
            }
          }
        }
      })

      val scrollPane = JBScrollPane(tree)
      panel.add(scrollPane, BorderLayout.CENTER)

      val refreshButton = JButton("Refresh")
      refreshButton.addActionListener {
        root.removeAllChildren()
        buildTree(root, treeModel)
        treeModel.reload()
        expandAll(tree)
      }
      panel.add(refreshButton, BorderLayout.SOUTH)

      expandAll(tree)
      return panel
    }

    private fun buildTree(root: DefaultMutableTreeNode, treeModel: DefaultTreeModel) {
      val files = ReadAction.compute<Collection<VirtualFile>, Throwable> {
        FilenameIndex.getAllFilesByExt(
          project, "mcfunction", GlobalSearchScope.projectScope(project)
        )
      }.sortedBy { it.path }

      // namespace -> functions path でグループ化
      val namespaceMap = mutableMapOf<String, MutableMap<String, VirtualFile>>()
      for (file in files) {
        val path = file.path.replace("\\", "/")
        val dataIndex = path.indexOf("/data/")
        if (dataIndex != -1) {
          val relativePath = path.substring(dataIndex + 6)
          val parts = relativePath.split("/")
          if (parts.size >= 3 && parts[1] == "functions") {
            val namespace = parts[0]
            val funcPath = parts.drop(2).joinToString("/").removeSuffix(".mcfunction")
            namespaceMap.getOrPut(namespace) { mutableMapOf() }[funcPath] = file
          }
        } else {
          // data/ 以外のファイルは "Other" グループへ
          namespaceMap.getOrPut("(other)") { mutableMapOf() }[file.name] = file
        }
      }

      for ((namespace, funcMap) in namespaceMap.entries.sortedBy { it.key }) {
        val nsNode = DefaultMutableTreeNode(namespace)
        root.add(nsNode)
        for ((funcPath, vFile) in funcMap.entries.sortedBy { it.key }) {
          val fileNode = object : DefaultMutableTreeNode(vFile) {
            override fun toString(): String = funcPath
          }
          nsNode.add(fileNode)
        }
      }
    }

    private fun expandAll(tree: JTree) {
      var i = 0
      while (i < tree.rowCount) {
        tree.expandRow(i)
        i++
      }
    }
  }

  // ---- セルレンダラー ----
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
