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

public class McFunctionExecuteModifierClauseImpl extends ASTWrapperPsiElement implements McFunctionExecuteModifierClause {

  public McFunctionExecuteModifierClauseImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull McFunctionVisitor visitor) {
    visitor.visitExecuteModifierClause(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof McFunctionVisitor) accept((McFunctionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public McFunctionExecuteAlignClause getExecuteAlignClause() {
    return findChildByClass(McFunctionExecuteAlignClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteAnchoredClause getExecuteAnchoredClause() {
    return findChildByClass(McFunctionExecuteAnchoredClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteAsClause getExecuteAsClause() {
    return findChildByClass(McFunctionExecuteAsClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteAtClause getExecuteAtClause() {
    return findChildByClass(McFunctionExecuteAtClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteFacingClause getExecuteFacingClause() {
    return findChildByClass(McFunctionExecuteFacingClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteGenericModifier getExecuteGenericModifier() {
    return findChildByClass(McFunctionExecuteGenericModifier.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteIfClause getExecuteIfClause() {
    return findChildByClass(McFunctionExecuteIfClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteInClause getExecuteInClause() {
    return findChildByClass(McFunctionExecuteInClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteOnClause getExecuteOnClause() {
    return findChildByClass(McFunctionExecuteOnClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecutePositionClause getExecutePositionClause() {
    return findChildByClass(McFunctionExecutePositionClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteRideClause getExecuteRideClause() {
    return findChildByClass(McFunctionExecuteRideClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteRotatedClause getExecuteRotatedClause() {
    return findChildByClass(McFunctionExecuteRotatedClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteStoreClause getExecuteStoreClause() {
    return findChildByClass(McFunctionExecuteStoreClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteSummonClause getExecuteSummonClause() {
    return findChildByClass(McFunctionExecuteSummonClause.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteUnlessClause getExecuteUnlessClause() {
    return findChildByClass(McFunctionExecuteUnlessClause.class);
  }

}
