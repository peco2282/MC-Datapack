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
  @Nullable
  public McFunctionAttributeCommand getAttributeCommand() {
    return findChildByClass(McFunctionAttributeCommand.class);
  }

  @Override
  @Nullable
  public McFunctionClearCommand getClearCommand() {
    return findChildByClass(McFunctionClearCommand.class);
  }

  @Override
  @Nullable
  public McFunctionDamageCommand getDamageCommand() {
    return findChildByClass(McFunctionDamageCommand.class);
  }

  @Override
  @Nullable
  public McFunctionDataCommand getDataCommand() {
    return findChildByClass(McFunctionDataCommand.class);
  }

  @Override
  @Nullable
  public McFunctionExecuteCommand getExecuteCommand() {
    return findChildByClass(McFunctionExecuteCommand.class);
  }

  @Override
  @Nullable
  public McFunctionGenericCommand getGenericCommand() {
    return findChildByClass(McFunctionGenericCommand.class);
  }

  @Override
  @Nullable
  public McFunctionGiveCommand getGiveCommand() {
    return findChildByClass(McFunctionGiveCommand.class);
  }

  @Override
  @Nullable
  public McFunctionItemCommand getItemCommand() {
    return findChildByClass(McFunctionItemCommand.class);
  }

  @Override
  @Nullable
  public McFunctionMacroLine getMacroLine() {
    return findChildByClass(McFunctionMacroLine.class);
  }

  @Override
  @Nullable
  public McFunctionParticleCommand getParticleCommand() {
    return findChildByClass(McFunctionParticleCommand.class);
  }

  @Override
  @Nullable
  public McFunctionReturnCommand getReturnCommand() {
    return findChildByClass(McFunctionReturnCommand.class);
  }

  @Override
  @Nullable
  public McFunctionRideCommand getRideCommand() {
    return findChildByClass(McFunctionRideCommand.class);
  }

  @Override
  @Nullable
  public PsiElement getCommentToken() {
    return findChildByType(COMMENT_TOKEN);
  }

  @Override
  @Nullable
  public PsiElement getCrlfToken() {
    return findChildByType(CRLF_TOKEN);
  }

}
