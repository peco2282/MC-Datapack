// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.peco2282.mcdatapack.language.psi.impl.*;

public interface McFunctionTypes {

  IElementType ARGUMENT = new McFunctionElementType("ARGUMENT");
  IElementType COMMAND_LINE = new McFunctionElementType("COMMAND_LINE");
  IElementType JSON = new McFunctionElementType("JSON");
  IElementType JSON_ARRAY = new McFunctionElementType("JSON_ARRAY");
  IElementType JSON_OBJECT = new McFunctionElementType("JSON_OBJECT");

  IElementType ARGUMENT_TOKEN = new McFunctionTokenType("ARGUMENT_TOKEN");
  IElementType AS_TOKEN = new McFunctionTokenType("as");
  IElementType AT_TOKEN = new McFunctionTokenType("at");
  IElementType COLON = new McFunctionTokenType(":");
  IElementType COMMA = new McFunctionTokenType(",");
  IElementType COMMAND_TOKEN = new McFunctionTokenType("COMMAND_TOKEN");
  IElementType COMMENT_TOKEN = new McFunctionTokenType("COMMENT_TOKEN");
  IElementType CRLF_TOKEN = new McFunctionTokenType("CRLF_TOKEN");
  IElementType ENTITY_TOKEN = new McFunctionTokenType("entity");
  IElementType EXECUTE_TOKEN = new McFunctionTokenType("execute");
  IElementType IF_TOKEN = new McFunctionTokenType("if");
  IElementType LBRACE = new McFunctionTokenType("{");
  IElementType LBRACK = new McFunctionTokenType("[");
  IElementType RBRACE = new McFunctionTokenType("}");
  IElementType RBRACK = new McFunctionTokenType("]");
  IElementType RESULT_TOKEN = new McFunctionTokenType("result");
  IElementType RUN_TOKEN = new McFunctionTokenType("run");
  IElementType SCORE_TOKEN = new McFunctionTokenType("score");
  IElementType SPACE_TOKEN = new McFunctionTokenType("SPACE_TOKEN");
  IElementType STORE_TOKEN = new McFunctionTokenType("store");
  IElementType STRING_TOKEN = new McFunctionTokenType("STRING_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new McFunctionArgumentImpl(node);
      }
      else if (type == COMMAND_LINE) {
        return new McFunctionCommandLineImpl(node);
      }
      else if (type == JSON) {
        return new McFunctionJsonImpl(node);
      }
      else if (type == JSON_ARRAY) {
        return new McFunctionJsonArrayImpl(node);
      }
      else if (type == JSON_OBJECT) {
        return new McFunctionJsonObjectImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
