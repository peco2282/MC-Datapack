// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.peco2282.mcdatapack.language.psi.impl.*;

public interface McFunctionTypes {

  IElementType ARGUMENT = new McFunctionElementType("ARGUMENT");
  IElementType COMMAND = new McFunctionElementType("COMMAND");
  IElementType COMMAND_LINE = new McFunctionElementType("COMMAND_LINE");
  IElementType JSON = new McFunctionElementType("JSON");
  IElementType JSON_ARRAY = new McFunctionElementType("JSON_ARRAY");
  IElementType JSON_OBJECT = new McFunctionElementType("JSON_OBJECT");
  IElementType KEYWORD = new McFunctionElementType("KEYWORD");

  IElementType ARGUMENT_TOKEN = new McFunctionTokenType("ARGUMENT_TOKEN");
  IElementType AS_TOKEN = new McFunctionTokenType("as");
  IElementType AT_TOKEN = new McFunctionTokenType("at");
  IElementType BLOCK_TOKEN = new McFunctionTokenType("block");
  IElementType COLON = new McFunctionTokenType(":");
  IElementType COMMA = new McFunctionTokenType(",");
  IElementType COMMAND_TOKEN = new McFunctionTokenType("COMMAND_TOKEN");
  IElementType COMMENT_TOKEN = new McFunctionTokenType("COMMENT_TOKEN");
  IElementType CONTINUATION_TOKEN = new McFunctionTokenType("CONTINUATION_TOKEN");
  IElementType CRLF_TOKEN = new McFunctionTokenType("CRLF_TOKEN");
  IElementType DATA_TOKEN = new McFunctionTokenType("data");
  IElementType DOTDOT_TOKEN = new McFunctionTokenType("..");
  IElementType DOT_TOKEN = new McFunctionTokenType(".");
  IElementType ENTITY_TOKEN = new McFunctionTokenType("entity");
  IElementType EXECUTE_TOKEN = new McFunctionTokenType("execute");
  IElementType FUNCTION_TOKEN = new McFunctionTokenType("function");
  IElementType GTE_TOKEN = new McFunctionTokenType(">=");
  IElementType GT_TOKEN = new McFunctionTokenType(">");
  IElementType IF_TOKEN = new McFunctionTokenType("if");
  IElementType ITEMS_TOKEN = new McFunctionTokenType("items");
  IElementType LBRACE = new McFunctionTokenType("{");
  IElementType LBRACK = new McFunctionTokenType("[");
  IElementType LTE_TOKEN = new McFunctionTokenType("<=");
  IElementType LT_TOKEN = new McFunctionTokenType("<");
  IElementType MACRO_TOKEN = new McFunctionTokenType("MACRO_TOKEN");
  IElementType MATCHES_TOKEN = new McFunctionTokenType("matches");
  IElementType RBRACE = new McFunctionTokenType("}");
  IElementType RBRACK = new McFunctionTokenType("]");
  IElementType RESULT_TOKEN = new McFunctionTokenType("result");
  IElementType RETURN_TOKEN = new McFunctionTokenType("return");
  IElementType RUN_TOKEN = new McFunctionTokenType("run");
  IElementType SCHEDULE_TOKEN = new McFunctionTokenType("schedule");
  IElementType SCORE_TOKEN = new McFunctionTokenType("score");
  IElementType SELECTOR_A = new McFunctionTokenType("@a");
  IElementType SELECTOR_E = new McFunctionTokenType("@e");
  IElementType SELECTOR_P = new McFunctionTokenType("@p");
  IElementType SELECTOR_R = new McFunctionTokenType("@r");
  IElementType SELECTOR_S = new McFunctionTokenType("@s");
  IElementType SPACE_TOKEN = new McFunctionTokenType("SPACE_TOKEN");
  IElementType STORAGE_TOKEN = new McFunctionTokenType("storage");
  IElementType STORE_TOKEN = new McFunctionTokenType("store");
  IElementType STRING_TOKEN = new McFunctionTokenType("STRING_TOKEN");
  IElementType UNLESS_TOKEN = new McFunctionTokenType("unless");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new McFunctionArgumentImpl(node);
      }
      else if (type == COMMAND) {
        return new McFunctionCommandImpl(node);
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
      else if (type == KEYWORD) {
        return new McFunctionKeywordImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
