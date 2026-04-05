package com.github.peco2282.mcdatapack.language.reference

import com.github.peco2282.mcdatapack.language.psi.McFunctionArgument
import com.github.peco2282.mcdatapack.language.psi.McFunctionCommand
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.ProcessingContext

class McFunctionReferenceContributor : PsiReferenceContributor() {
  override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
    registrar.registerReferenceProvider(
      com.intellij.patterns.PlatformPatterns.psiElement(McFunctionArgument::class.java),
      object : PsiReferenceProvider() {
        override fun getReferencesByElement(
          element: PsiElement,
          context: ProcessingContext
        ): Array<PsiReference> {
          val argument = element as McFunctionArgument
          val text = argument.text

          // function <name> の <name> 部分を抽出して参照を作成
          // 簡単のため、親が generic_command で、そのコマンドが "function" かつ 2番目の引数以降の場合を想定
          // 実際には argument 自体の構造や文脈をより厳密にチェックすべき
          val parentCommand = argument.parent?.firstChild as? McFunctionCommand
          if (parentCommand?.text == "function") {
            // 文字列に : が含まれる場合 (namespace:path/to/function) も考慮
            if (text.contains(":") || text.contains("/")) {
              return arrayOf(McFunctionFileReference(argument, TextRange(0, text.length)))
            }
          }
          return emptyArray()
        }
      }
    )
  }
}

class McFunctionFileReference(element: PsiElement, textRange: TextRange) :
  PsiReferenceBase<PsiElement>(element, textRange) {

  override fun resolve(): PsiElement? {
    val fullPath = rangeInElement.substring(element.text)
    val parts = fullPath.split(":")
    val namespace = if (parts.size > 1) parts[0] else "minecraft"
    val path = if (parts.size > 1) parts[1] else parts[0]

    // 実際の Datapack 構造 (data/<namespace>/functions/<path>.mcfunction) を探す
    // path は "/" を含む可能性がある（例: "foo/bar"）、または含まない（例: "foo"）
    val fileName = path.substringAfterLast("/") + ".mcfunction"
    val files = FilenameIndex.getFilesByName(element.project, fileName, GlobalSearchScope.allScope(element.project))

    // パスが一致するものを探す (data/<namespace>/functions/<path>.mcfunction)
    val normalizedPath = path.replace("\\", "/")
    val expectedPathSuffix = "data/$namespace/functions/$normalizedPath.mcfunction"

    return files.firstOrNull { file ->
      val virtualFile = file.virtualFile
      val absolutePath = virtualFile.path.replace("\\", "/")
      absolutePath.endsWith(expectedPathSuffix)
    }
  }
}
