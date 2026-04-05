package com.github.peco2282.mcdatapack.language.formatting

import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock

class McFunctionBlock(
  node: ASTNode,
  wrap: Wrap?,
  alignment: Alignment?,
  private val spacingBuilder: SpacingBuilder,
  private val indent: Indent = Indent.getNoneIndent()
) : AbstractBlock(node, wrap, alignment) {

  override fun buildChildren(): List<Block> {
    val blocks = mutableListOf<Block>()
    var child = myNode.firstChildNode
    while (child != null) {
      val elementType = child.elementType
      if (elementType != TokenType.WHITE_SPACE) {
        val childIndent = when (elementType) {
          McFunctionTypes.JSON_OBJECT, McFunctionTypes.NBT_COMPOUND -> Indent.getNormalIndent()
          McFunctionTypes.JSON_ARRAY, McFunctionTypes.NBT_LIST -> Indent.getNormalIndent()
          else -> Indent.getNoneIndent()
        }
        blocks.add(
          McFunctionBlock(
            child,
            Wrap.createWrap(WrapType.NONE, false),
            null,
            spacingBuilder,
            childIndent
          )
        )
      }
      child = child.treeNext
    }
    return blocks
  }

  override fun getSpacing(child1: Block?, child2: Block): Spacing? = spacingBuilder.getSpacing(this, child1, child2)

  override fun isLeaf(): Boolean = myNode.firstChildNode == null

  override fun getIndent(): Indent = indent

  override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
    val elementType = myNode.elementType
    return when (elementType) {
      McFunctionTypes.JSON_OBJECT, McFunctionTypes.NBT_COMPOUND ->
        ChildAttributes(Indent.getNormalIndent(), null)
      McFunctionTypes.JSON_ARRAY, McFunctionTypes.NBT_LIST ->
        ChildAttributes(Indent.getNormalIndent(), null)
      else -> ChildAttributes(Indent.getNoneIndent(), null)
    }
  }
}
