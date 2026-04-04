// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionExecuteCommand extends PsiElement {

  @NotNull
  List<McFunctionCommandLine> getCommandLineList();

  @NotNull
  List<McFunctionComponentList> getComponentListList();

  @NotNull
  List<McFunctionJson> getJsonList();

  @NotNull
  List<McFunctionKeyword> getKeywordList();

  @NotNull
  List<McFunctionNbtCompound> getNbtCompoundList();

  @NotNull
  List<McFunctionSelector> getSelectorList();

}
