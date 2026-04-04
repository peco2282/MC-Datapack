// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionDataCommand extends PsiElement {

  @NotNull
  List<McFunctionCoordinate> getCoordinateList();

  @Nullable
  McFunctionKeyword getKeyword();

  @NotNull
  List<McFunctionNamespacedId> getNamespacedIdList();

  @Nullable
  McFunctionNbtCompound getNbtCompound();

  @Nullable
  McFunctionNbtValue getNbtValue();

  @NotNull
  List<McFunctionSelector> getSelectorList();

  @Nullable
  PsiElement getCommandToken();

}
