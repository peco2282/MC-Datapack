package com.github.peco2282.mcdatapack.language.reference

import com.github.peco2282.mcdatapack.language.psi.McFunctionNamespacedId
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.tree.IElementType
import com.intellij.util.ProcessingContext

private data class NamespacedIdContext(val subDir: String, val extension: String)

private val NAMESPACED_ID_CONTEXTS: Map<IElementType, NamespacedIdContext> = mapOf(
  McFunctionTypes.FUNCTION_TOKEN to NamespacedIdContext("functions", ".mcfunction"),
  McFunctionTypes.ADVANCEMENT_TOKEN to NamespacedIdContext("advancements", ".json"),
  McFunctionTypes.RECIPE_TOKEN to NamespacedIdContext("recipes", ".json"),
  McFunctionTypes.LOOT_TOKEN to NamespacedIdContext("loot_tables", ".json")
)

private fun resolveNamespacedIdContext(element: McFunctionNamespacedId): NamespacedIdContext? {
  var prev = element.prevSibling
  while (prev != null && prev.node.elementType == TokenType.WHITE_SPACE) {
    prev = prev.prevSibling
  }
  val prevType = prev?.node?.elementType ?: return null

  NAMESPACED_ID_CONTEXTS[prevType]?.let { return it }

  val parentCommand = element.parent
  if (parentCommand != null) {
    var child = parentCommand.firstChild
    while (child != null) {
      val childType = child.node.elementType
      if (childType == TokenType.WHITE_SPACE) {
        child = child.nextSibling
        continue
      }
      NAMESPACED_ID_CONTEXTS[childType]?.let { return it }
      break
    }
  }
  return null
}

class McFunctionReferenceContributor : PsiReferenceContributor() {
  override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
    registrar.registerReferenceProvider(
      PlatformPatterns.psiElement(McFunctionNamespacedId::class.java),
      object : PsiReferenceProvider() {
        override fun getReferencesByElement(
          element: PsiElement,
          context: ProcessingContext
        ): Array<PsiReference> {
          val namespacedId = element as McFunctionNamespacedId
          val text = namespacedId.text
          val ctx = resolveNamespacedIdContext(namespacedId) ?: return emptyArray()
          if (!text.contains(":") && !text.contains("/")) return emptyArray()
          return arrayOf(McFunctionFileReference(namespacedId, TextRange(0, text.length), ctx.subDir, ctx.extension))
        }
      }
    )
  }
}

class McFunctionFileReference(
  element: PsiElement,
  textRange: TextRange,
  private val subDir: String,
  private val extension: String
) : PsiReferenceBase<PsiElement>(element, textRange) {

  override fun resolve(): PsiElement? {
    val fullPath = rangeInElement.substring(element.text)
    val parts = fullPath.split(":")
    val namespace = if (parts.size > 1) parts[0] else "minecraft"
    val path = if (parts.size > 1) parts[1] else parts[0]

    val fileName = path.substringAfterLast("/") + extension
    val files = FilenameIndex.getFilesByName(
      element.project, fileName, GlobalSearchScope.allScope(element.project)
    )

    val normalizedPath = path.replace("\\", "/")
    val expectedPathSuffix = "data/$namespace/$subDir/$normalizedPath$extension"

    return files.firstOrNull { file ->
      file.virtualFile.path.replace("\\", "/").endsWith(expectedPathSuffix)
    }
  }
}
