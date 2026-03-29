// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionGenericCommand extends PsiElement {

  @NotNull
  List<McFunctionArgument> getArgumentList();

  @NotNull
  McFunctionCommand getCommand();

  @NotNull
  List<McFunctionJson> getJsonList();

}
