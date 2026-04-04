// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionGiveCommand extends PsiElement {

  @NotNull
  List<McFunctionArgument> getArgumentList();

  @Nullable
  McFunctionItemStack getItemStack();

  @Nullable
  McFunctionSelector getSelector();

}
