// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface McFunctionGenericCommand extends PsiElement {

  @NotNull
  List<McFunctionArgument> getArgumentList();

  @Nullable
  McFunctionCommand getCommand();

  @Nullable
  McFunctionDamageCommand getDamageCommand();

  @NotNull
  List<McFunctionJson> getJsonList();

  @NotNull
  List<McFunctionNamespacedId> getNamespacedIdList();

  @NotNull
  List<McFunctionNbtCompound> getNbtCompoundList();

  @Nullable
  McFunctionParticleCommand getParticleCommand();

  @Nullable
  McFunctionReturnCommand getReturnCommand();

  @Nullable
  McFunctionRideCommand getRideCommand();

}
