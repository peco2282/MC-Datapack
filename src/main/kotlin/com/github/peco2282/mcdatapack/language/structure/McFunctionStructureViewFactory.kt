package com.github.peco2282.mcdatapack.language.structure

import com.github.peco2282.mcdatapack.language.highlighting.McFunctionIcons
import com.github.peco2282.mcdatapack.language.psi.McFunctionCommandLine
import com.github.peco2282.mcdatapack.language.psi.McFunctionExecuteCommand
import com.github.peco2282.mcdatapack.language.psi.McFunctionGenericCommand
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import javax.swing.Icon

class McFunctionStructureViewFactory : PsiStructureViewFactory {
  override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
    return object : TreeBasedStructureViewBuilder() {
      override fun createStructureViewModel(editor: Editor?): StructureViewModel {
        return McFunctionStructureViewModel(psiFile)
      }
    }
  }
}

class McFunctionStructureViewModel(psiFile: PsiFile) :
  StructureViewModelBase(psiFile, McFunctionStructureViewElement(psiFile)),
  StructureViewModel.ElementInfoProvider {
  override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean = false
  override fun isAlwaysLeaf(element: StructureViewTreeElement?): Boolean = false
}

class McFunctionStructureViewElement(private val element: PsiElement) : StructureViewTreeElement, NavigationItem {
  override fun getValue(): Any = element

  override fun navigate(requestFocus: Boolean) {
    (element as? NavigationItem)?.navigate(requestFocus)
  }

  override fun canNavigate(): Boolean = (element as? NavigationItem)?.canNavigate() ?: false

  override fun canNavigateToSource(): Boolean = (element as? NavigationItem)?.canNavigateToSource() ?: false

  override fun getName(): String? {
    if (element is PsiFile) return element.name
    return element.text
  }

  override fun getPresentation(): ItemPresentation {
    return object : ItemPresentation {
      override fun getPresentableText(): String? {
        if (element is PsiFile) return element.name
        if (element is McFunctionCommandLine) {
          val text = element.text.trim()
          return text.take(50).let { if (it.length == 50) "$it..." else it }
        }
        if (element.node.elementType == McFunctionTypes.COMMENT_TOKEN) {
          val text = element.text.trim()
          return text.take(50).let { if (it.length == 50) "$it..." else it }
        }
        return element.text
      }

      override fun getLocationString(): String? = null

      override fun getIcon(unused: Boolean): Icon? {
        if (element is PsiFile) return McFunctionIcons.FILE
        if (element.node.elementType == McFunctionTypes.COMMENT_TOKEN) return McFunctionIcons.COMMAND
        if (element is McFunctionCommandLine) {
          val generic = element.genericCommand
          if (generic != null) {
            val commandText = generic.command?.text?.lowercase() ?: ""
            return getIconForCommand(commandText)
          }
          if (element.executeCommand != null) {
            return McFunctionIcons.EXECUTE
          }
          return McFunctionIcons.COMMAND
        }
        return null
      }

      private fun getIconForCommand(text: String): Icon {
        return when {
          text == "execute" -> McFunctionIcons.EXECUTE
          text == "function" -> McFunctionIcons.FUNCTION
          text == "give" -> McFunctionIcons.GIVE
          text.contains("effect") -> McFunctionIcons.EFFECT
          text.contains("scoreboard") -> McFunctionIcons.SCOREBOARD
          text.startsWith("@") -> McFunctionIcons.SELECTOR
          else -> McFunctionIcons.COMMAND
        }
      }
    }
  }

  override fun getChildren(): Array<TreeElement> {
    if (element is PsiFile) {
      val children = mutableListOf<TreeElement>()
      var child = element.firstChild
      while (child != null) {
        when {
          child is McFunctionCommandLine -> children.add(McFunctionStructureViewElement(child))
          child.node.elementType == McFunctionTypes.COMMENT_TOKEN -> children.add(McFunctionStructureViewElement(child))
        }
        child = child.nextSibling
      }
      return children.toTypedArray()
    }
    // execute ... run ... の多段ネストを再帰的に表示する
    if (element is McFunctionCommandLine) {
      val executeCommand = element.executeCommand ?: return emptyArray()
      val commandLine = executeCommand.commandLine ?: return emptyArray()
      return arrayOf(McFunctionStructureViewElement(commandLine))
    }
    return emptyArray()
  }
}
