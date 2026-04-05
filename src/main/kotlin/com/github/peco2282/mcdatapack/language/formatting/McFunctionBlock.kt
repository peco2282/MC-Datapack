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
    var prevWasRun = false
    while (child != null) {
      val elementType = child.elementType
      if (elementType != TokenType.WHITE_SPACE) {
        val childIndent = when {
          // JSON/NBT 構造体のインデント
          elementType == McFunctionTypes.JSON_OBJECT ||
          elementType == McFunctionTypes.NBT_COMPOUND ||
          elementType == McFunctionTypes.JSON_ARRAY ||
          elementType == McFunctionTypes.NBT_LIST -> Indent.getNormalIndent()

          // execute_command 直下で RUN_TOKEN の次の COMMAND_LINE はインデント
          myNode.elementType == McFunctionTypes.EXECUTE_COMMAND &&
          elementType == McFunctionTypes.COMMAND_LINE && prevWasRun -> Indent.getNormalIndent()

          else -> Indent.getNoneIndent()
        }
        prevWasRun = elementType == McFunctionTypes.RUN_TOKEN
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
    return when {
      elementType == McFunctionTypes.JSON_OBJECT ||
      elementType == McFunctionTypes.NBT_COMPOUND ||
      elementType == McFunctionTypes.JSON_ARRAY ||
      elementType == McFunctionTypes.NBT_LIST ->
        ChildAttributes(Indent.getNormalIndent(), null)

      // execute_command 内で新しい行を入力した場合はインデント
      elementType == McFunctionTypes.EXECUTE_COMMAND ->
        ChildAttributes(Indent.getNormalIndent(), null)

      else -> ChildAttributes(Indent.getNoneIndent(), null)
    }
  }
}
