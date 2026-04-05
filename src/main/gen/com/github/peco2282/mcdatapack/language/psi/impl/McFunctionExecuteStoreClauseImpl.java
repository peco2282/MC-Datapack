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

public class McFunctionExecuteStoreClauseImpl extends ASTWrapperPsiElement implements McFunctionExecuteStoreClause {

  public McFunctionExecuteStoreClauseImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull McFunctionVisitor visitor) {
    visitor.visitExecuteStoreClause(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof McFunctionVisitor) accept((McFunctionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public McFunctionCoordinate getCoordinate() {
    return findChildByClass(McFunctionCoordinate.class);
  }

  @Override
  @NotNull
  public List<McFunctionExecuteNamespacedId> getExecuteNamespacedIdList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionExecuteNamespacedId.class);
  }

  @Override
  @Nullable
  public McFunctionSelector getSelector() {
    return findChildByClass(McFunctionSelector.class);
  }

  @Override
  @Nullable
  public PsiElement getArgumentToken() {
    return findChildByType(ARGUMENT_TOKEN);
  }

  @Override
  @Nullable
  public PsiElement getCommandToken() {
    return findChildByType(COMMAND_TOKEN);
  }

}
