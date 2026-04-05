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

// テキストベースで前のコマンドコンテキストを確認し、追加のリソースタイプを解決する
private val TEXT_BASED_CONTEXTS: Map<String, NamespacedIdContext> = mapOf(
  "predicate" to NamespacedIdContext("predicates", ".json"),
  "item_modifier" to NamespacedIdContext("item_modifiers", ".json"),
  "dimension" to NamespacedIdContext("dimension", ".json"),
  "structure" to NamespacedIdContext("structures", ".nbt")
)

// execute in <dimension> のような構造でdimensionを検出するためのパターン
private val DIMENSION_COMMAND_PATTERNS = listOf(
  Regex("execute\\s+in\\s+$"),
  Regex("\\bin\\s+$")
)

// execute if predicate <id> のような構造でpredicateを検出するためのパターン
private val PREDICATE_COMMAND_PATTERNS = listOf(
  Regex("\\bpredicate\\s+$")
)

// item modify ... <item_modifier> のような構造でitem_modifierを検出するためのパターン
private val ITEM_MODIFIER_COMMAND_PATTERNS = listOf(
  Regex("\\bitem\\s+modify\\s+\\S+\\s+\\S+\\s+\\S+\\s+$"),
  Regex("\\bmodify\\s+\\S+\\s+\\S+\\s+\\S+\\s+$")
)

// place structure <id> のような構造でstructureを検出するためのパターン
private val STRUCTURE_COMMAND_PATTERNS = listOf(
  Regex("\\bplace\\s+structure\\s+$"),
  Regex("\\bstructure\\s+$")
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

  // テキストベースで前のコマンドコンテキストを確認
  return resolveTextBasedContext(element)
}

private fun resolveTextBasedContext(element: McFunctionNamespacedId): NamespacedIdContext? {
  // 行全体のテキストを取得して、要素の前の部分を確認
  val containingFile = element.containingFile ?: return null
  val elementOffset = element.textOffset
  val fileText = containingFile.text ?: return null

  // 行の先頭を探す
  var lineStart = elementOffset - 1
  while (lineStart >= 0 && fileText[lineStart] != '\n' && fileText[lineStart] != '\r') {
    lineStart--
  }
  lineStart++

  val textBeforeElement = fileText.substring(lineStart, elementOffset)

  // predicate パターンの確認
  for (pattern in PREDICATE_COMMAND_PATTERNS) {
    if (pattern.containsMatchIn(textBeforeElement)) {
      return TEXT_BASED_CONTEXTS["predicate"]
    }
  }

  // dimension パターンの確認
  for (pattern in DIMENSION_COMMAND_PATTERNS) {
    if (pattern.containsMatchIn(textBeforeElement)) {
      return TEXT_BASED_CONTEXTS["dimension"]
    }
  }

  // item_modifier パターンの確認
  for (pattern in ITEM_MODIFIER_COMMAND_PATTERNS) {
    if (pattern.containsMatchIn(textBeforeElement)) {
      return TEXT_BASED_CONTEXTS["item_modifier"]
    }
  }

  // structure パターンの確認
  for (pattern in STRUCTURE_COMMAND_PATTERNS) {
    if (pattern.containsMatchIn(textBeforeElement)) {
      return TEXT_BASED_CONTEXTS["structure"]
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

  override fun handleElementRename(newElementName: String): PsiElement {
    val currentText = rangeInElement.substring(element.text)
    val parts = currentText.split(":")
    val namespace = if (parts.size > 1) parts[0] else "minecraft"
    val path = if (parts.size > 1) parts[1] else parts[0]
    val newBaseName = newElementName.removeSuffix(extension)
    val newPath = if (path.contains("/")) {
      path.substringBeforeLast("/") + "/" + newBaseName
    } else {
      newBaseName
    }
    val newId = "$namespace:$newPath"
    return ElementManipulators.handleContentChange(element, rangeInElement, newId)
  }
}
