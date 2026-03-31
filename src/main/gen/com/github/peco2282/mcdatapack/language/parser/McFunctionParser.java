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
  // json | ARGUMENT_TOKEN | COMMAND_TOKEN | STRING_TOKEN | command | keyword
  //   | SELECTOR_S | SELECTOR_A | SELECTOR_P | SELECTOR_E | SELECTOR_R
  //   | MACRO_TOKEN
  //   | GTE_TOKEN | LTE_TOKEN | GT_TOKEN | LT_TOKEN | DOTDOT_TOKEN | DOT_TOKEN
  //   | COLON | EQUALS | LBRACK | RBRACK | LBRACE | RBRACE | COMMA
  //   | CONTINUATION_TOKEN
  public static boolean argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT, "<argument>");
    result_ = json(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = command(builder_, level_ + 1);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_S);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_A);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_P);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_E);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_R);
    if (!result_) result_ = consumeToken(builder_, MACRO_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DOTDOT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DOT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, EQUALS);
    if (!result_) result_ = consumeToken(builder_, LBRACK);
    if (!result_) result_ = consumeToken(builder_, RBRACK);
    if (!result_) result_ = consumeToken(builder_, LBRACE);
    if (!result_) result_ = consumeToken(builder_, RBRACE);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, CONTINUATION_TOKEN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ADVANCEMENT_TOKEN | ATTRIBUTE_TOKEN | EXECUTE_TOKEN | BOSSBAR_TOKEN | CLEAR_TOKEN | CLONE_TOKEN | DAMAGE_TOKEN
  //   | DATA_TOKEN | DATAPACK_TOKEN | DEBUG_TOKEN | DEFAULTGAMEMODE_TOKEN | DIFFICULTY_TOKEN | EFFECT_TOKEN | ENCHANT_TOKEN
  //   | EXPERIENCE_TOKEN | FILL_TOKEN | FILLBIOME_TOKEN | FORCELOAD_TOKEN | FUNCTION_TOKEN | GAMEMODE_TOKEN | GAMERULE_TOKEN
  //   | GIVE_TOKEN | HELP_TOKEN | ITEM_TOKEN | JFR_TOKEN | KICK_TOKEN | KILL_TOKEN | LIST_TOKEN | LOCATE_TOKEN | LOOT_TOKEN
  //   | ME_TOKEN | MSG_TOKEN | PARTICLE_TOKEN | PERF_TOKEN | PLACE_TOKEN | PLAYSOUND_TOKEN | RECIPE_TOKEN | RETURN_TOKEN
  //   | RIDE_TOKEN | SAY_TOKEN | SCHEDULE_TOKEN | SCOREBOARD_TOKEN | SETBLOCK_TOKEN | SETIDLETIMEOUT_TOKEN | SETWORLDSPAWN_TOKEN
  //   | SPAWNPOINT_TOKEN | SPECTATE_TOKEN | SPREADPLAYERS_TOKEN | STOPSOUND_TOKEN | SUMMON_TOKEN | TAG_TOKEN | TEAM_TOKEN
  //   | TEAMMSG_TOKEN | TELEPORT_TOKEN | TELL_TOKEN | TELLRAW_TOKEN | TICK_TOKEN | TIME_TOKEN | TITLE_TOKEN | TM_TOKEN
  //   | TP_TOKEN | TRIGGER_TOKEN | WEATHER_TOKEN | WHITELIST_TOKEN | WORLDBORDER_TOKEN | XP_TOKEN
  //   | IF_TOKEN | MACRO_TOKEN | COMMAND_TOKEN
  public static boolean command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND, "<command>");
    result_ = consumeToken(builder_, ADVANCEMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ATTRIBUTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EXECUTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, BOSSBAR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, CLEAR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, CLONE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DAMAGE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DATA_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DATAPACK_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DEBUG_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DEFAULTGAMEMODE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DIFFICULTY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EFFECT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ENCHANT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EXPERIENCE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FILL_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FILLBIOME_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FORCELOAD_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FUNCTION_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GAMEMODE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GAMERULE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GIVE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, HELP_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ITEM_TOKEN);
    if (!result_) result_ = consumeToken(builder_, JFR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, KICK_TOKEN);
    if (!result_) result_ = consumeToken(builder_, KILL_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LIST_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LOCATE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LOOT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ME_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MSG_TOKEN);
    if (!result_) result_ = consumeToken(builder_, PARTICLE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, PERF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, PLACE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, PLAYSOUND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RECIPE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RETURN_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RIDE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SAY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SCHEDULE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SCOREBOARD_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SETBLOCK_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SETIDLETIMEOUT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SETWORLDSPAWN_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SPAWNPOINT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SPECTATE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SPREADPLAYERS_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STOPSOUND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SUMMON_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TAG_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TEAM_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TEAMMSG_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TELEPORT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TELL_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TELLRAW_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TICK_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TIME_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TITLE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TM_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TP_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TRIGGER_TOKEN);
    if (!result_) result_ = consumeToken(builder_, WEATHER_TOKEN);
    if (!result_) result_ = consumeToken(builder_, WHITELIST_TOKEN);
    if (!result_) result_ = consumeToken(builder_, WORLDBORDER_TOKEN);
    if (!result_) result_ = consumeToken(builder_, XP_TOKEN);
    if (!result_) result_ = consumeToken(builder_, IF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MACRO_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // execute_command | generic_command
  public static boolean command_line(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND_LINE, "<command line>");
    result_ = execute_command(builder_, level_ + 1);
    if (!result_) result_ = generic_command(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (WHITE_SPACE | execute_modifier_with_continuation)+ (RUN_TOKEN (WHITE_SPACE command_line | execute_body))?
  static boolean execute_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_body_0(builder_, level_ + 1);
    result_ = result_ && execute_body_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (WHITE_SPACE | execute_modifier_with_continuation)+
  private static boolean execute_body_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_body_0_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!execute_body_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "execute_body_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // WHITE_SPACE | execute_modifier_with_continuation
  private static boolean execute_body_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, WHITE_SPACE);
    if (!result_) result_ = execute_modifier_with_continuation(builder_, level_ + 1);
    return result_;
  }

  // (RUN_TOKEN (WHITE_SPACE command_line | execute_body))?
  private static boolean execute_body_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_1")) return false;
    execute_body_1_0(builder_, level_ + 1);
    return true;
  }

  // RUN_TOKEN (WHITE_SPACE command_line | execute_body)
  private static boolean execute_body_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RUN_TOKEN);
    result_ = result_ && execute_body_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // WHITE_SPACE command_line | execute_body
  private static boolean execute_body_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_1_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_body_1_0_1_0(builder_, level_ + 1);
    if (!result_) result_ = execute_body(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // WHITE_SPACE command_line
  private static boolean execute_body_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, WHITE_SPACE);
    result_ = result_ && command_line(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EXECUTE_TOKEN execute_body?
  public static boolean execute_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_command")) return false;
    if (!nextTokenIs(builder_, EXECUTE_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EXECUTE_TOKEN);
    result_ = result_ && execute_command_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, EXECUTE_COMMAND, result_);
    return result_;
  }

  // execute_body?
  private static boolean execute_command_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_command_1")) return false;
    execute_body(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // keyword | json | argument
  static boolean execute_modifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_modifier")) return false;
    boolean result_;
    result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = json(builder_, level_ + 1);
    if (!result_) result_ = argument(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // (CONTINUATION_TOKEN WHITE_SPACE?)? execute_modifier
  static boolean execute_modifier_with_continuation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_modifier_with_continuation")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_modifier_with_continuation_0(builder_, level_ + 1);
    result_ = result_ && execute_modifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (CONTINUATION_TOKEN WHITE_SPACE?)?
  private static boolean execute_modifier_with_continuation_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_modifier_with_continuation_0")) return false;
    execute_modifier_with_continuation_0_0(builder_, level_ + 1);
    return true;
  }

  // CONTINUATION_TOKEN WHITE_SPACE?
  private static boolean execute_modifier_with_continuation_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_modifier_with_continuation_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONTINUATION_TOKEN);
    result_ = result_ && execute_modifier_with_continuation_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // WHITE_SPACE?
  private static boolean execute_modifier_with_continuation_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_modifier_with_continuation_0_0_1")) return false;
    consumeToken(builder_, WHITE_SPACE);
    return true;
  }

  /* ********************************************************** */
  // (command | json) (WHITE_SPACE | (CONTINUATION_TOKEN WHITE_SPACE?) | json | argument)*
  public static boolean generic_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, GENERIC_COMMAND, "<generic command>");
    result_ = generic_command_0(builder_, level_ + 1);
    result_ = result_ && generic_command_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // command | json
  private static boolean generic_command_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_0")) return false;
    boolean result_;
    result_ = command(builder_, level_ + 1);
    if (!result_) result_ = json(builder_, level_ + 1);
    return result_;
  }

  // (WHITE_SPACE | (CONTINUATION_TOKEN WHITE_SPACE?) | json | argument)*
  private static boolean generic_command_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!generic_command_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_command_1", pos_)) break;
    }
    return true;
  }

  // WHITE_SPACE | (CONTINUATION_TOKEN WHITE_SPACE?) | json | argument
  private static boolean generic_command_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, WHITE_SPACE);
    if (!result_) result_ = generic_command_1_0_1(builder_, level_ + 1);
    if (!result_) result_ = json(builder_, level_ + 1);
    if (!result_) result_ = argument(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CONTINUATION_TOKEN WHITE_SPACE?
  private static boolean generic_command_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_1_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONTINUATION_TOKEN);
    result_ = result_ && generic_command_1_0_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // WHITE_SPACE?
  private static boolean generic_command_1_0_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_1_0_1_1")) return false;
    consumeToken(builder_, WHITE_SPACE);
    return true;
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
  // WHITE_SPACE? json_value WHITE_SPACE?
  static boolean json_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_element")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_element_0(builder_, level_ + 1);
    result_ = result_ && json_value(builder_, level_ + 1);
    result_ = result_ && json_element_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // WHITE_SPACE?
  private static boolean json_element_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_element_0")) return false;
    consumeToken(builder_, WHITE_SPACE);
    return true;
  }

  // WHITE_SPACE?
  private static boolean json_element_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_element_2")) return false;
    consumeToken(builder_, WHITE_SPACE);
    return true;
  }

  /* ********************************************************** */
  // (argument | STRING_TOKEN | COLON)+
  static boolean json_key(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_key")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_key_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!json_key_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "json_key", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // argument | STRING_TOKEN | COLON
  private static boolean json_key_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_key_0")) return false;
    boolean result_;
    result_ = argument(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COLON);
    return result_;
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
  // WHITE_SPACE? json_key WHITE_SPACE? (COLON | EQUALS) WHITE_SPACE? json_value WHITE_SPACE?
  static boolean json_property(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_property_0(builder_, level_ + 1);
    result_ = result_ && json_key(builder_, level_ + 1);
    result_ = result_ && json_property_2(builder_, level_ + 1);
    result_ = result_ && json_property_3(builder_, level_ + 1);
    result_ = result_ && json_property_4(builder_, level_ + 1);
    result_ = result_ && json_value(builder_, level_ + 1);
    result_ = result_ && json_property_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // WHITE_SPACE?
  private static boolean json_property_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_0")) return false;
    consumeToken(builder_, WHITE_SPACE);
    return true;
  }

  // WHITE_SPACE?
  private static boolean json_property_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_2")) return false;
    consumeToken(builder_, WHITE_SPACE);
    return true;
  }

  // COLON | EQUALS
  private static boolean json_property_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_3")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, EQUALS);
    return result_;
  }

  // WHITE_SPACE?
  private static boolean json_property_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_4")) return false;
    consumeToken(builder_, WHITE_SPACE);
    return true;
  }

  // WHITE_SPACE?
  private static boolean json_property_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_6")) return false;
    consumeToken(builder_, WHITE_SPACE);
    return true;
  }

  /* ********************************************************** */
  // json | (argument | STRING_TOKEN)+ json?
  static boolean json_value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_value")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json(builder_, level_ + 1);
    if (!result_) result_ = json_value_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (argument | STRING_TOKEN)+ json?
  private static boolean json_value_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_value_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_value_1_0(builder_, level_ + 1);
    result_ = result_ && json_value_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (argument | STRING_TOKEN)+
  private static boolean json_value_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_value_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_value_1_0_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!json_value_1_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "json_value_1_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // argument | STRING_TOKEN
  private static boolean json_value_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_value_1_0_0")) return false;
    boolean result_;
    result_ = argument(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    return result_;
  }

  // json?
  private static boolean json_value_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_value_1_1")) return false;
    json(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // RUN_TOKEN | ONLY_TOKEN | ENTITY_TOKEN | MODIFY_TOKEN | STORAGE_TOKEN | SET_TOKEN
  //   | FROM_TOKEN | ADD_TOKEN | PLAYERS_TOKEN | ACTIONBAR_TOKEN | MATCHES_TOKEN | AS_TOKEN | AT_TOKEN | ANCHORED_TOKEN | FACING_TOKEN | BLOCK_TOKEN
  //   | ITEMS_TOKEN | STORE_TOKEN | RESULT_TOKEN | SCORE_TOKEN | TEXT_TOKEN | VALUE_TOKEN | EYES_TOKEN | REVOKE_TOKEN | GRANT_TOKEN | DATA_TOKEN
  //   | GET_TOKEN | MERGE_TOKEN | REMOVE_TOKEN | ENABLE_TOKEN | DISABLE_TOKEN | BASE_TOKEN | MODIFIER_TOKEN
  //   | QUERY_TOKEN | TAKE_TOKEN | OBJECTIVES_TOKEN | SETDISPLAY_TOKEN | EMPTY_TOKEN | JOIN_TOKEN | LEAVE_TOKEN
  //   | RATE_TOKEN | FREEZE_TOKEN | STEP_TOKEN | STOP_TOKEN | UNFREEZE_TOKEN | SUBTITLE_TOKEN | TIMES_TOKEN
  //   | CENTER_TOKEN | WARNING_TOKEN | MASTER_TOKEN | MUSIC_TOKEN
  //   | IF_TOKEN | UNLESS_TOKEN
  public static boolean keyword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keyword")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, KEYWORD, "<keyword>");
    result_ = consumeToken(builder_, RUN_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ONLY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ENTITY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MODIFY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STORAGE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SET_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FROM_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ADD_TOKEN);
    if (!result_) result_ = consumeToken(builder_, PLAYERS_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ACTIONBAR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MATCHES_TOKEN);
    if (!result_) result_ = consumeToken(builder_, AS_TOKEN);
    if (!result_) result_ = consumeToken(builder_, AT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ANCHORED_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FACING_TOKEN);
    if (!result_) result_ = consumeToken(builder_, BLOCK_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ITEMS_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STORE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RESULT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SCORE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TEXT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, VALUE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EYES_TOKEN);
    if (!result_) result_ = consumeToken(builder_, REVOKE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GRANT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DATA_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GET_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MERGE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, REMOVE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ENABLE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DISABLE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, BASE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MODIFIER_TOKEN);
    if (!result_) result_ = consumeToken(builder_, QUERY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TAKE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, OBJECTIVES_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SETDISPLAY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EMPTY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, JOIN_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LEAVE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RATE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FREEZE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STEP_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STOP_TOKEN);
    if (!result_) result_ = consumeToken(builder_, UNFREEZE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SUBTITLE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TIMES_TOKEN);
    if (!result_) result_ = consumeToken(builder_, CENTER_TOKEN);
    if (!result_) result_ = consumeToken(builder_, WARNING_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MASTER_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MUSIC_TOKEN);
    if (!result_) result_ = consumeToken(builder_, IF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, UNLESS_TOKEN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (command_line | COMMENT_TOKEN | CRLF_TOKEN | WHITE_SPACE | CONTINUATION_TOKEN)*
  static boolean mcFunctionFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mcFunctionFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!mcFunctionFile_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "mcFunctionFile", pos_)) break;
    }
    return true;
  }

  // command_line | COMMENT_TOKEN | CRLF_TOKEN | WHITE_SPACE | CONTINUATION_TOKEN
  private static boolean mcFunctionFile_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mcFunctionFile_0")) return false;
    boolean result_;
    result_ = command_line(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, CRLF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, WHITE_SPACE);
    if (!result_) result_ = consumeToken(builder_, CONTINUATION_TOKEN);
    return result_;
  }

}
