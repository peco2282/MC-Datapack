package com.github.peco2282.mcdatapack.language

import com.github.peco2282.mcdatapack.language.parser.McFunctionParser
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class McFunctionParserDefinition : ParserDefinition {
  override fun createLexer(project: Project?): Lexer = McFunctionLexerAdapter()

  override fun createParser(project: Project?): PsiParser = McFunctionParser()

  override fun getFileNodeType(): IFileElementType = McFunctionLanguage.FILE

  override fun getCommentTokens(): TokenSet = TokenSet.create(McFunctionTypes.COMMENT_TOKEN)

  override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

  override fun createElement(node: ASTNode?): PsiElement = McFunctionTypes.Factory.createElement(node)

  override fun createFile(viewProvider: FileViewProvider): PsiFile = McFunctionFile(viewProvider)
}
