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

public class McFunctionSelectorArgumentImpl extends ASTWrapperPsiElement implements McFunctionSelectorArgument {

  public McFunctionSelectorArgumentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull McFunctionVisitor visitor) {
    visitor.visitSelectorArgument(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof McFunctionVisitor) accept((McFunctionVisitor)visitor);
    else super.accept(visitor);
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
  public McFunctionNbtList getNbtList() {
    return findChildByClass(McFunctionNbtList.class);
  }

  @Override
  @Nullable
  public PsiElement getArgumentToken() {
    return findChildByType(ARGUMENT_TOKEN);
  }

  @Override
  @Nullable
  public PsiElement getStringToken() {
    return findChildByType(STRING_TOKEN);
  }

}
