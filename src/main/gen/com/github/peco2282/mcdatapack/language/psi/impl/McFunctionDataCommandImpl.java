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

public class McFunctionDataCommandImpl extends ASTWrapperPsiElement implements McFunctionDataCommand {

  public McFunctionDataCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull McFunctionVisitor visitor) {
    visitor.visitDataCommand(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof McFunctionVisitor) accept((McFunctionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<McFunctionCoordinate> getCoordinateList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionCoordinate.class);
  }

  @Override
  @Nullable
  public McFunctionKeyword getKeyword() {
    return findChildByClass(McFunctionKeyword.class);
  }

  @Override
  @NotNull
  public List<McFunctionNamespacedId> getNamespacedIdList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionNamespacedId.class);
  }

  @Override
  @Nullable
  public McFunctionNbtCompound getNbtCompound() {
    return findChildByClass(McFunctionNbtCompound.class);
  }

  @Override
  @Nullable
  public McFunctionNbtValue getNbtValue() {
    return findChildByClass(McFunctionNbtValue.class);
  }

  @Override
  @NotNull
  public List<McFunctionSelector> getSelectorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionSelector.class);
  }

  @Override
  @Nullable
  public PsiElement getCommandToken() {
    return findChildByType(COMMAND_TOKEN);
  }

}
