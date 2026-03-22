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

public class McFunctionCommandLineImpl extends ASTWrapperPsiElement implements McFunctionCommandLine {

  public McFunctionCommandLineImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull McFunctionVisitor visitor) {
    visitor.visitCommandLine(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof McFunctionVisitor) accept((McFunctionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<McFunctionArgument> getArgumentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionArgument.class);
  }

  @Override
  @NotNull
  public List<McFunctionJson> getJsonList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, McFunctionJson.class);
  }

  @Override
  @Nullable
  public PsiElement getCommandToken() {
    return findChildByType(COMMAND_TOKEN);
  }

}
