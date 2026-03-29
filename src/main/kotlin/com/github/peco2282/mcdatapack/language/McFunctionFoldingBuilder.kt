package com.github.peco2282.mcdatapack.language

import com.github.peco2282.mcdatapack.language.psi.McFunctionJsonObject
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class McFunctionFoldingBuilder : FoldingBuilderEx(), DumbAware {
  override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
    val descriptors = mutableListOf<FoldingDescriptor>()

    // JSONオブジェクトの折りたたみ
    val jsonObjects = PsiTreeUtil.findChildrenOfType(root, McFunctionJsonObject::class.java)
    for (jsonObject in jsonObjects) {
      if (jsonObject.textRange.length > 1) {
        descriptors.add(FoldingDescriptor(jsonObject.node, jsonObject.textRange))
      }
    }

    // 連続するコメントの折りたたみ
    var child = root.firstChild
    while (child != null) {
      if (child.node.elementType == McFunctionTypes.COMMENT_TOKEN) {
        val firstComment = child
        var lastComment = child
        var next = child.nextSibling
        while (next != null && (next.node.elementType == McFunctionTypes.COMMENT_TOKEN ||
              next.node.elementType == McFunctionTypes.CRLF_TOKEN ||
              next.node.elementType == McFunctionTypes.SPACE_TOKEN)
        ) {
          if (next.node.elementType == McFunctionTypes.COMMENT_TOKEN) {
            lastComment = next
          }
          next = next.nextSibling
        }

        if (firstComment != lastComment) {
          descriptors.add(FoldingDescriptor(firstComment.node, firstComment.textRange.union(lastComment.textRange)))
          child = next
          continue
        }
      }
      child = child.nextSibling
    }

    return descriptors.toTypedArray()
  }

  override fun getPlaceholderText(node: ASTNode): String {
    val type = node.elementType
    if (type == McFunctionTypes.JSON_OBJECT) return "{...}"
    if (type == McFunctionTypes.COMMENT_TOKEN) return "#..."
    return "..."
  }

  override fun isCollapsedByDefault(node: ASTNode): Boolean = false
}
