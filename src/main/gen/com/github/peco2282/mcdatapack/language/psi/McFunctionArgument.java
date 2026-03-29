// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionArgument extends PsiElement {

  @Nullable
  McFunctionCommand getCommand();

  @Nullable
  McFunctionKeyword getKeyword();

  @Nullable
  PsiElement getArgumentToken();

  @Nullable
  PsiElement getCommandToken();

  @Nullable
  PsiElement getContinuationToken();

  @Nullable
  PsiElement getMacroToken();

  @Nullable
  PsiElement getStringToken();

}
