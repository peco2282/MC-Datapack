// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class McFunctionParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return mcFunctionFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // ARGUMENT_TOKEN | COMMAND_TOKEN | STRING_TOKEN | EXECUTE_TOKEN | RUN_TOKEN | AS_TOKEN | AT_TOKEN | STORE_TOKEN | RESULT_TOKEN | SCORE_TOKEN | IF_TOKEN | ENTITY_TOKEN | COLON | LBRACK | RBRACK | LBRACE | RBRACE | COMMA
  public static boolean argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT, "<argument>");
    result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EXECUTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RUN_TOKEN);
    if (!result_) result_ = consumeToken(builder_, AS_TOKEN);
    if (!result_) result_ = consumeToken(builder_, AT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STORE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RESULT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SCORE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, IF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ENTITY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, LBRACK);
    if (!result_) result_ = consumeToken(builder_, RBRACK);
    if (!result_) result_ = consumeToken(builder_, LBRACE);
    if (!result_) result_ = consumeToken(builder_, RBRACE);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (EXECUTE_TOKEN | COMMAND_TOKEN) (SPACE_TOKEN | (json | argument))*
  public static boolean command_line(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line")) return false;
    if (!nextTokenIs(builder_, "<command line>", COMMAND_TOKEN, EXECUTE_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND_LINE, "<command line>");
    result_ = command_line_0(builder_, level_ + 1);
    result_ = result_ && command_line_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // EXECUTE_TOKEN | COMMAND_TOKEN
  private static boolean command_line_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, EXECUTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    return result_;
  }

  // (SPACE_TOKEN | (json | argument))*
  private static boolean command_line_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!command_line_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "command_line_1", pos_)) break;
    }
    return true;
  }

  // SPACE_TOKEN | (json | argument)
  private static boolean command_line_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SPACE_TOKEN);
    if (!result_) result_ = command_line_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // json | argument
  private static boolean command_line_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_1_0_1")) return false;
    boolean result_;
    result_ = json(builder_, level_ + 1);
    if (!result_) result_ = argument(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // command_line | COMMENT_TOKEN | CRLF_TOKEN | SPACE_TOKEN
  static boolean item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item")) return false;
    boolean result_;
    result_ = command_line(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, CRLF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SPACE_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // json_array | json_object
  public static boolean json(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json")) return false;
    if (!nextTokenIs(builder_, "<json>", LBRACE, LBRACK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, JSON, "<json>");
    result_ = json_array(builder_, level_ + 1);
    if (!result_) result_ = json_object(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // LBRACK (json_element (COMMA json_element)*)? RBRACK
  public static boolean json_array(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_array")) return false;
    if (!nextTokenIs(builder_, LBRACK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACK);
    result_ = result_ && json_array_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACK);
    exit_section_(builder_, marker_, JSON_ARRAY, result_);
    return result_;
  }

  // (json_element (COMMA json_element)*)?
  private static boolean json_array_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_array_1")) return false;
    json_array_1_0(builder_, level_ + 1);
    return true;
  }

  // json_element (COMMA json_element)*
  private static boolean json_array_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_array_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_element(builder_, level_ + 1);
    result_ = result_ && json_array_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA json_element)*
  private static boolean json_array_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_array_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!json_array_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "json_array_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA json_element
  private static boolean json_array_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_array_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && json_element(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SPACE_TOKEN? (json | argument) SPACE_TOKEN?
  static boolean json_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_element")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_element_0(builder_, level_ + 1);
    result_ = result_ && json_element_1(builder_, level_ + 1);
    result_ = result_ && json_element_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SPACE_TOKEN?
  private static boolean json_element_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_element_0")) return false;
    consumeToken(builder_, SPACE_TOKEN);
    return true;
  }

  // json | argument
  private static boolean json_element_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_element_1")) return false;
    boolean result_;
    result_ = json(builder_, level_ + 1);
    if (!result_) result_ = argument(builder_, level_ + 1);
    return result_;
  }

  // SPACE_TOKEN?
  private static boolean json_element_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_element_2")) return false;
    consumeToken(builder_, SPACE_TOKEN);
    return true;
  }

  /* ********************************************************** */
  // LBRACE (json_property (COMMA json_property)*)? RBRACE
  public static boolean json_object(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_object")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACE);
    result_ = result_ && json_object_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, JSON_OBJECT, result_);
    return result_;
  }

  // (json_property (COMMA json_property)*)?
  private static boolean json_object_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_object_1")) return false;
    json_object_1_0(builder_, level_ + 1);
    return true;
  }

  // json_property (COMMA json_property)*
  private static boolean json_object_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_object_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_property(builder_, level_ + 1);
    result_ = result_ && json_object_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA json_property)*
  private static boolean json_object_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_object_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!json_object_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "json_object_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA json_property
  private static boolean json_object_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_object_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && json_property(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SPACE_TOKEN? (argument | STRING_TOKEN) SPACE_TOKEN? COLON SPACE_TOKEN? (json | argument) SPACE_TOKEN?
  static boolean json_property(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_property_0(builder_, level_ + 1);
    result_ = result_ && json_property_1(builder_, level_ + 1);
    result_ = result_ && json_property_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && json_property_4(builder_, level_ + 1);
    result_ = result_ && json_property_5(builder_, level_ + 1);
    result_ = result_ && json_property_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SPACE_TOKEN?
  private static boolean json_property_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_0")) return false;
    consumeToken(builder_, SPACE_TOKEN);
    return true;
  }

  // argument | STRING_TOKEN
  private static boolean json_property_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_1")) return false;
    boolean result_;
    result_ = argument(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    return result_;
  }

  // SPACE_TOKEN?
  private static boolean json_property_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_2")) return false;
    consumeToken(builder_, SPACE_TOKEN);
    return true;
  }

  // SPACE_TOKEN?
  private static boolean json_property_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_4")) return false;
    consumeToken(builder_, SPACE_TOKEN);
    return true;
  }

  // json | argument
  private static boolean json_property_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_5")) return false;
    boolean result_;
    result_ = json(builder_, level_ + 1);
    if (!result_) result_ = argument(builder_, level_ + 1);
    return result_;
  }

  // SPACE_TOKEN?
  private static boolean json_property_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_6")) return false;
    consumeToken(builder_, SPACE_TOKEN);
    return true;
  }

  /* ********************************************************** */
  // item*
  static boolean mcFunctionFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mcFunctionFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!item(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "mcFunctionFile", pos_)) break;
    }
    return true;
  }

}
