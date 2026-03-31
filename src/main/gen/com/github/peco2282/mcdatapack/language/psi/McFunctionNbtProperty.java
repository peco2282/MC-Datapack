// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionNbtProperty extends PsiElement {

  @Nullable
  McFunctionCommand getCommand();

  @Nullable
  McFunctionKeyword getKeyword();

  @NotNull
  McFunctionNbtValue getNbtValue();

  @Nullable
  PsiElement getArgumentToken();

  @Nullable
  PsiElement getCommandToken();

  @Nullable
  PsiElement getStringToken();

}
