// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionExecuteGenericModifier extends PsiElement {

  @NotNull
  List<McFunctionCommand> getCommandList();

  @Nullable
  McFunctionComponentList getComponentList();

  @Nullable
  McFunctionCoordinate getCoordinate();

  @Nullable
  McFunctionJson getJson();

  @Nullable
  McFunctionNbtCompound getNbtCompound();

  @Nullable
  McFunctionSelector getSelector();

  @Nullable
  PsiElement getCoordToken();

  @Nullable
  PsiElement getMacroToken();

  @Nullable
  PsiElement getMacroVarToken();

  @Nullable
  PsiElement getStringToken();

}
