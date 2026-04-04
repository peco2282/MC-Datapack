// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionNbtPath extends PsiElement {

  @NotNull
  List<McFunctionKeyword> getKeywordList();

  @NotNull
  List<McFunctionNamespacedId> getNamespacedIdList();

  @NotNull
  List<McFunctionNbtCompound> getNbtCompoundList();

}
