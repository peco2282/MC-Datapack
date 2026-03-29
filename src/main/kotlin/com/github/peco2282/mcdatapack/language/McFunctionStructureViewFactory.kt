package com.github.peco2282.mcdatapack.language

import com.github.peco2282.mcdatapack.language.psi.McFunctionCommandLine
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
          return element.text.take(50).let { if (it.length == 50) "$it..." else it }
        }
        return element.text
      }

      override fun getLocationString(): String? = null

      override fun getIcon(unused: Boolean): Icon? {
        if (element is PsiFile) return McFunctionIcons.FILE
        return null
      }
    }
  }

  override fun getChildren(): Array<TreeElement> {
    if (element is PsiFile) {
      return PsiTreeUtil.getChildrenOfType(element, McFunctionCommandLine::class.java)
        ?.map { McFunctionStructureViewElement(it) }
        ?.toTypedArray() ?: emptyArray()
    }
    return emptyArray()
  }
}
