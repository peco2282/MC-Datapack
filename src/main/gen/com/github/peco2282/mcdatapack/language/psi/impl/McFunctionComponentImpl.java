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

public class McFunctionComponentImpl extends ASTWrapperPsiElement implements McFunctionComponent {

  public McFunctionComponentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull McFunctionVisitor visitor) {
    visitor.visitComponent(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof McFunctionVisitor) accept((McFunctionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public McFunctionComponentValue getComponentValue() {
    return findChildByClass(McFunctionComponentValue.class);
  }

  @Override
  @NotNull
  public McFunctionNamespacedId getNamespacedId() {
    return findNotNullChildByClass(McFunctionNamespacedId.class);
  }

  @Override
  @Nullable
  public PsiElement getArgumentToken() {
    return findChildByType(ARGUMENT_TOKEN);
  }

}
