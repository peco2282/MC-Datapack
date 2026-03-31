// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.peco2282.mcdatapack.language.psi.*;

public class McFunctionMacroLineImpl extends ASTWrapperPsiElement implements McFunctionMacroLine {

  public McFunctionMacroLineImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull McFunctionVisitor visitor) {
    visitor.visitMacroLine(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof McFunctionVisitor) accept((McFunctionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<McFunctionCommand> getCommandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionCommand.class);
  }

  @Override
  @NotNull
  public List<McFunctionKeyword> getKeywordList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionKeyword.class);
  }

  @Override
  @NotNull
  public List<McFunctionMacroVariable> getMacroVariableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionMacroVariable.class);
  }

  @Override
  @NotNull
  public List<McFunctionSelector> getSelectorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionSelector.class);
  }

  @Override
  @NotNull
  public PsiElement getMacroLineStart() {
    return findNotNullChildByType(MACRO_LINE_START);
  }

}
