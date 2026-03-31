// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionSelectorArgument extends PsiElement {

  @NotNull
  List<McFunctionNamespacedId> getNamespacedIdList();

  @Nullable
  McFunctionNbtCompound getNbtCompound();

  @Nullable
  McFunctionNbtList getNbtList();

  @Nullable
  PsiElement getArgumentToken();

  @Nullable
  PsiElement getStringToken();

}
