// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionDamageCommand extends PsiElement {

  @Nullable
  McFunctionArgument getArgument();

  @Nullable
  McFunctionNamespacedId getNamespacedId();

  @Nullable
  McFunctionNbtPrimitive getNbtPrimitive();

  @NotNull
  List<McFunctionSelector> getSelectorList();

  @Nullable
  PsiElement getArgumentToken();

}
