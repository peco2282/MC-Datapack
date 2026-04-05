package com.github.peco2282.mcdatapack.language.highlighting

import com.intellij.spellchecker.inspections.BaseSplitter
import com.intellij.util.Consumer

object McFunctionIdentifierSplitter : BaseSplitter() {
  override fun split(text: String?, range: com.intellij.openapi.util.TextRange, consumer: Consumer<com.intellij.openapi.util.TextRange>) {
    if (text == null || range.isEmpty) return
    val sub = range.substring(text)
    // namespace:path/to/function -> split on ':', '/', '_', '.'
    val parts = sub.split(':', '/', '_', '.')
    var offset = range.startOffset
    for (part in parts) {
      if (part.length >= 3) {
        consumer.consume(com.intellij.openapi.util.TextRange(offset, offset + part.length))
      }
      offset += part.length + 1
    }
  }
}
