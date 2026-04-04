// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionCommandLine extends PsiElement {

  @Nullable
  McFunctionAttributeCommand getAttributeCommand();

  @Nullable
  McFunctionClearCommand getClearCommand();

  @Nullable
  McFunctionDataCommand getDataCommand();

  @Nullable
  McFunctionExecuteCommand getExecuteCommand();

  @Nullable
  McFunctionGenericCommand getGenericCommand();

  @Nullable
  McFunctionGiveCommand getGiveCommand();

  @Nullable
  McFunctionItemCommand getItemCommand();

  @Nullable
  McFunctionMacroLine getMacroLine();

  @Nullable
  PsiElement getCommentToken();

  @Nullable
  PsiElement getCrlfToken();

}
