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

public class McFunctionExecuteGenericModifierImpl extends ASTWrapperPsiElement implements McFunctionExecuteGenericModifier {

  public McFunctionExecuteGenericModifierImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull McFunctionVisitor visitor) {
    visitor.visitExecuteGenericModifier(this);
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
  @Nullable
  public McFunctionComponentList getComponentList() {
    return findChildByClass(McFunctionComponentList.class);
  }

  @Override
  @Nullable
  public McFunctionCoordinate getCoordinate() {
    return findChildByClass(McFunctionCoordinate.class);
  }

  @Override
  @Nullable
  public McFunctionJson getJson() {
    return findChildByClass(McFunctionJson.class);
  }

  @Override
  @Nullable
  public McFunctionNbtCompound getNbtCompound() {
    return findChildByClass(McFunctionNbtCompound.class);
  }

  @Override
  @Nullable
  public McFunctionSelector getSelector() {
    return findChildByClass(McFunctionSelector.class);
  }

  @Override
  @Nullable
  public PsiElement getCoordToken() {
    return findChildByType(COORD_TOKEN);
  }

  @Override
  @Nullable
  public PsiElement getMacroToken() {
    return findChildByType(MACRO_TOKEN);
  }

  @Override
  @Nullable
  public PsiElement getMacroVarToken() {
    return findChildByType(MACRO_VAR_TOKEN);
  }

  @Override
  @Nullable
  public PsiElement getStringToken() {
    return findChildByType(STRING_TOKEN);
  }

}
