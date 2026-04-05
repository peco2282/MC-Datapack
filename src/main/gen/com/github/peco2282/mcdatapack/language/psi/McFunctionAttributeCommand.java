// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionAttributeCommand extends PsiElement {

  @Nullable
  McFunctionKeyword getKeyword();

  @NotNull
  List<McFunctionNamespacedId> getNamespacedIdList();

  @Nullable
  McFunctionSelector getSelector();

  @Nullable
  PsiElement getArgumentToken();

  @Nullable
  PsiElement getCommandToken();

  @Nullable
  PsiElement getStringToken();

}
