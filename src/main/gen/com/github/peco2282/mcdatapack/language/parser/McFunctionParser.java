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
  // selector | coordinate | json | COORD_TOKEN | ARGUMENT_TOKEN | COMMAND_TOKEN | STRING_TOKEN | command | keyword
  //   | SELECTOR_S | SELECTOR_A | SELECTOR_P | SELECTOR_E | SELECTOR_R
  //   | MACRO_TOKEN | MACRO_VAR_TOKEN
  //   | GTE_TOKEN | LTE_TOKEN | GT_TOKEN | LT_TOKEN | DOTDOT_TOKEN | DOT_TOKEN
  //   | COLON | EQUALS | LBRACK | RBRACK | LBRACE | RBRACE | COMMA
  //   | CONTINUATION_TOKEN
  public static boolean argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT, "<argument>");
    result_ = selector(builder_, level_ + 1);
    if (!result_) result_ = coordinate(builder_, level_ + 1);
    if (!result_) result_ = json(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COORD_TOKEN);
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
    if (!result_) result_ = consumeToken(builder_, MACRO_VAR_TOKEN);
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
  // COMMAND_TOKEN | ARGUMENT_TOKEN | keyword | command
  static boolean attr_ns_identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attr_ns_identifier")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // GET_TOKEN ARGUMENT_TOKEN?
  //   | SET_TOKEN ARGUMENT_TOKEN
  //   | ADD_TOKEN ARGUMENT_TOKEN
  //   | REMOVE_TOKEN ARGUMENT_TOKEN
  static boolean attribute_base_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_base_op")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_base_op_0(builder_, level_ + 1);
    if (!result_) result_ = parseTokens(builder_, 0, SET_TOKEN, ARGUMENT_TOKEN);
    if (!result_) result_ = parseTokens(builder_, 0, ADD_TOKEN, ARGUMENT_TOKEN);
    if (!result_) result_ = parseTokens(builder_, 0, REMOVE_TOKEN, ARGUMENT_TOKEN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // GET_TOKEN ARGUMENT_TOKEN?
  private static boolean attribute_base_op_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_base_op_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, GET_TOKEN);
    result_ = result_ && attribute_base_op_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ARGUMENT_TOKEN?
  private static boolean attribute_base_op_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_base_op_0_1")) return false;
    consumeToken(builder_, ARGUMENT_TOKEN);
    return true;
  }

  /* ********************************************************** */
  // ATTRIBUTE_TOKEN selector attribute_namespaced_id attribute_subcommand?
  public static boolean attribute_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_command")) return false;
    if (!nextTokenIs(builder_, ATTRIBUTE_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ATTRIBUTE_COMMAND, null);
    result_ = consumeToken(builder_, ATTRIBUTE_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, selector(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, attribute_namespaced_id(builder_, level_ + 1)) && result_;
    result_ = pinned_ && attribute_command_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // attribute_subcommand?
  private static boolean attribute_command_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_command_3")) return false;
    attribute_subcommand(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ADD_TOKEN ARGUMENT_TOKEN (STRING_TOKEN | ARGUMENT_TOKEN) ARGUMENT_TOKEN (COMMAND_TOKEN | keyword)
  //   | REMOVE_TOKEN namespaced_id
  //   | GET_TOKEN namespaced_id ARGUMENT_TOKEN?
  static boolean attribute_modifier_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_modifier_op")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_modifier_op_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_modifier_op_1(builder_, level_ + 1);
    if (!result_) result_ = attribute_modifier_op_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ADD_TOKEN ARGUMENT_TOKEN (STRING_TOKEN | ARGUMENT_TOKEN) ARGUMENT_TOKEN (COMMAND_TOKEN | keyword)
  private static boolean attribute_modifier_op_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_modifier_op_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ADD_TOKEN, ARGUMENT_TOKEN);
    result_ = result_ && attribute_modifier_op_0_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ARGUMENT_TOKEN);
    result_ = result_ && attribute_modifier_op_0_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STRING_TOKEN | ARGUMENT_TOKEN
  private static boolean attribute_modifier_op_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_modifier_op_0_2")) return false;
    boolean result_;
    result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    return result_;
  }

  // COMMAND_TOKEN | keyword
  private static boolean attribute_modifier_op_0_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_modifier_op_0_4")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    return result_;
  }

  // REMOVE_TOKEN namespaced_id
  private static boolean attribute_modifier_op_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_modifier_op_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REMOVE_TOKEN);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // GET_TOKEN namespaced_id ARGUMENT_TOKEN?
  private static boolean attribute_modifier_op_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_modifier_op_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, GET_TOKEN);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    result_ = result_ && attribute_modifier_op_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ARGUMENT_TOKEN?
  private static boolean attribute_modifier_op_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_modifier_op_2_2")) return false;
    consumeToken(builder_, ARGUMENT_TOKEN);
    return true;
  }

  /* ********************************************************** */
  // attr_ns_identifier (COLON attribute_namespaced_path)?
  static boolean attribute_namespaced_id(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_namespaced_id")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = attr_ns_identifier(builder_, level_ + 1);
    result_ = result_ && attribute_namespaced_id_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON attribute_namespaced_path)?
  private static boolean attribute_namespaced_id_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_namespaced_id_1")) return false;
    attribute_namespaced_id_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON attribute_namespaced_path
  private static boolean attribute_namespaced_id_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_namespaced_id_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && attribute_namespaced_path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // attr_ns_identifier (DOT_TOKEN attr_ns_identifier)*
  static boolean attribute_namespaced_path(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_namespaced_path")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = attr_ns_identifier(builder_, level_ + 1);
    result_ = result_ && attribute_namespaced_path_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (DOT_TOKEN attr_ns_identifier)*
  private static boolean attribute_namespaced_path_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_namespaced_path_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute_namespaced_path_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "attribute_namespaced_path_1", pos_)) break;
    }
    return true;
  }

  // DOT_TOKEN attr_ns_identifier
  private static boolean attribute_namespaced_path_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_namespaced_path_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_TOKEN);
    result_ = result_ && attr_ns_identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BASE_TOKEN attribute_base_op?
  //   | MODIFIER_TOKEN attribute_modifier_op
  //   | QUERY_TOKEN
  static boolean attribute_subcommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_subcommand")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_subcommand_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_subcommand_1(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, QUERY_TOKEN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // BASE_TOKEN attribute_base_op?
  private static boolean attribute_subcommand_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_subcommand_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BASE_TOKEN);
    result_ = result_ && attribute_subcommand_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // attribute_base_op?
  private static boolean attribute_subcommand_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_subcommand_0_1")) return false;
    attribute_base_op(builder_, level_ + 1);
    return true;
  }

  // MODIFIER_TOKEN attribute_modifier_op
  private static boolean attribute_subcommand_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_subcommand_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MODIFIER_TOKEN);
    result_ = result_ && attribute_modifier_op(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // CLEAR_TOKEN selector? item_id? argument*
  public static boolean clear_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "clear_command")) return false;
    if (!nextTokenIs(builder_, CLEAR_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CLEAR_COMMAND, null);
    result_ = consumeToken(builder_, CLEAR_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, clear_command_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, clear_command_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && clear_command_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // selector?
  private static boolean clear_command_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "clear_command_1")) return false;
    selector(builder_, level_ + 1);
    return true;
  }

  // item_id?
  private static boolean clear_command_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "clear_command_2")) return false;
    item_id(builder_, level_ + 1);
    return true;
  }

  // argument*
  private static boolean clear_command_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "clear_command_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!argument(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "clear_command_3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DAMAGE_TOKEN | ADVANCEMENT_TOKEN | ATTRIBUTE_TOKEN | EXECUTE_TOKEN | BOSSBAR_TOKEN | CLEAR_TOKEN | CLONE_TOKEN
  //   | DATA_TOKEN | DATAPACK_TOKEN | DEBUG_TOKEN | DEFAULTGAMEMODE_TOKEN | DIFFICULTY_TOKEN | EFFECT_TOKEN | ENCHANT_TOKEN
  //   | EXPERIENCE_TOKEN | FILL_TOKEN | FILLBIOME_TOKEN | FORCELOAD_TOKEN | FUNCTION_TOKEN | GAMEMODE_TOKEN | GAMERULE_TOKEN
  //   | GIVE_TOKEN | HELP_TOKEN | ITEM_TOKEN | JFR_TOKEN | KICK_TOKEN | KILL_TOKEN | LIST_TOKEN | LOCATE_TOKEN | LOOT_TOKEN
  //   | ME_TOKEN | MSG_TOKEN | PARTICLE_TOKEN | PERF_TOKEN | PLACE_TOKEN | PLAYSOUND_TOKEN | RECIPE_TOKEN | RETURN_TOKEN
  //   | RIDE_TOKEN | SAY_TOKEN | SCHEDULE_TOKEN | SCOREBOARD_TOKEN | SETBLOCK_TOKEN | SETIDLETIMEOUT_TOKEN | SETWORLDSPAWN_TOKEN
  //   | SPAWNPOINT_TOKEN | SPECTATE_TOKEN | SPREADPLAYERS_TOKEN | STOPSOUND_TOKEN | SUMMON_TOKEN | TAG_TOKEN | TEAM_TOKEN
  //   | TEAMMSG_TOKEN | TELEPORT_TOKEN | TELL_TOKEN | TELLRAW_TOKEN | TICK_TOKEN | TIME_TOKEN | TITLE_TOKEN | TM_TOKEN
  //   | TP_TOKEN | TRIGGER_TOKEN | WEATHER_TOKEN | WHITELIST_TOKEN | WORLDBORDER_TOKEN | XP_TOKEN
  //   | IF_TOKEN | MACRO_TOKEN | MACRO_VAR_TOKEN | COMMAND_TOKEN
  public static boolean command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND, "<command>");
    result_ = consumeToken(builder_, DAMAGE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ADVANCEMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ATTRIBUTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EXECUTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, BOSSBAR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, CLEAR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, CLONE_TOKEN);
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
    if (!result_) result_ = consumeToken(builder_, MACRO_VAR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (macro_line | execute_command | attribute_command | give_command | clear_command | data_command | item_command | generic_command) (CRLF_TOKEN | COMMENT_TOKEN | <<eof>>)?
  public static boolean command_line(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND_LINE, "<command line>");
    result_ = command_line_0(builder_, level_ + 1);
    result_ = result_ && command_line_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // macro_line | execute_command | attribute_command | give_command | clear_command | data_command | item_command | generic_command
  private static boolean command_line_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_0")) return false;
    boolean result_;
    result_ = macro_line(builder_, level_ + 1);
    if (!result_) result_ = execute_command(builder_, level_ + 1);
    if (!result_) result_ = attribute_command(builder_, level_ + 1);
    if (!result_) result_ = give_command(builder_, level_ + 1);
    if (!result_) result_ = clear_command(builder_, level_ + 1);
    if (!result_) result_ = data_command(builder_, level_ + 1);
    if (!result_) result_ = item_command(builder_, level_ + 1);
    if (!result_) result_ = generic_command(builder_, level_ + 1);
    return result_;
  }

  // (CRLF_TOKEN | COMMENT_TOKEN | <<eof>>)?
  private static boolean command_line_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_1")) return false;
    command_line_1_0(builder_, level_ + 1);
    return true;
  }

  // CRLF_TOKEN | COMMENT_TOKEN | <<eof>>
  private static boolean command_line_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CRLF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMENT_TOKEN);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !(CRLF_TOKEN | COMMENT_TOKEN)
  static boolean command_line_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !command_line_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // CRLF_TOKEN | COMMENT_TOKEN
  private static boolean command_line_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_line_recover_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, CRLF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMENT_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // component_key (EQUALS | COLON) component_value
  //             | ARGUMENT_TOKEN component_key
  public static boolean component(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPONENT, "<component>");
    result_ = component_0(builder_, level_ + 1);
    if (!result_) result_ = component_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // component_key (EQUALS | COLON) component_value
  private static boolean component_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = component_key(builder_, level_ + 1);
    result_ = result_ && component_0_1(builder_, level_ + 1);
    result_ = result_ && component_value(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // EQUALS | COLON
  private static boolean component_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_0_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, EQUALS);
    if (!result_) result_ = consumeToken(builder_, COLON);
    return result_;
  }

  // ARGUMENT_TOKEN component_key
  private static boolean component_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    result_ = result_ && component_key(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // namespaced_id
  static boolean component_key(PsiBuilder builder_, int level_) {
    return namespaced_id(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // LBRACK (component (COMMA component)*)? RBRACK
  public static boolean component_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_list")) return false;
    if (!nextTokenIs(builder_, LBRACK)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPONENT_LIST, null);
    result_ = consumeToken(builder_, LBRACK);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, component_list_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RBRACK) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (component (COMMA component)*)?
  private static boolean component_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_list_1")) return false;
    component_list_1_0(builder_, level_ + 1);
    return true;
  }

  // component (COMMA component)*
  private static boolean component_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = component(builder_, level_ + 1);
    result_ = result_ && component_list_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA component)*
  private static boolean component_list_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_list_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!component_list_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "component_list_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA component
  private static boolean component_list_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_list_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && component(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // nbt_compound | nbt_list | STRING_TOKEN | nbt_primitive
  public static boolean component_value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_value")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPONENT_VALUE, "<component value>");
    result_ = nbt_compound(builder_, level_ + 1);
    if (!result_) result_ = nbt_list(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = nbt_primitive(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // BY_TOKEN | MOUNT_TOKEN | RESULT_TOKEN | SUCCESS_TOKEN | RUN_TOKEN | DISMOUNT_TOKEN
  static boolean control_keyword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "control_keyword")) return false;
    boolean result_;
    result_ = consumeToken(builder_, BY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MOUNT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RESULT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SUCCESS_TOKEN);
    if (!result_) result_ = consumeToken(builder_, RUN_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DISMOUNT_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // COORD_TOKEN | numeric_literal
  public static boolean coordinate(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "coordinate")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COORDINATE, "<coordinate>");
    result_ = consumeToken(builder_, COORD_TOKEN);
    if (!result_) result_ = numeric_literal(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // COORD_TOKEN | ARGUMENT_TOKEN | GTE_TOKEN | LTE_TOKEN | GT_TOKEN | LT_TOKEN
  static boolean coordinate_component(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "coordinate_component")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COORD_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LT_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // BY_TOKEN selector
  static boolean damage_by_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "damage_by_clause")) return false;
    if (!nextTokenIs(builder_, BY_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, BY_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // DAMAGE_TOKEN selector ARGUMENT_TOKEN (namespaced_id | nbt_primitive | argument)? (damage_by_clause | damage_from_clause)*
  public static boolean damage_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "damage_command")) return false;
    if (!nextTokenIs(builder_, DAMAGE_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, DAMAGE_COMMAND, null);
    result_ = consumeToken(builder_, DAMAGE_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, selector(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, ARGUMENT_TOKEN)) && result_;
    result_ = pinned_ && report_error_(builder_, damage_command_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && damage_command_4(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (namespaced_id | nbt_primitive | argument)?
  private static boolean damage_command_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "damage_command_3")) return false;
    damage_command_3_0(builder_, level_ + 1);
    return true;
  }

  // namespaced_id | nbt_primitive | argument
  private static boolean damage_command_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "damage_command_3_0")) return false;
    boolean result_;
    result_ = namespaced_id(builder_, level_ + 1);
    if (!result_) result_ = nbt_primitive(builder_, level_ + 1);
    if (!result_) result_ = argument(builder_, level_ + 1);
    return result_;
  }

  // (damage_by_clause | damage_from_clause)*
  private static boolean damage_command_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "damage_command_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!damage_command_4_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "damage_command_4", pos_)) break;
    }
    return true;
  }

  // damage_by_clause | damage_from_clause
  private static boolean damage_command_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "damage_command_4_0")) return false;
    boolean result_;
    result_ = damage_by_clause(builder_, level_ + 1);
    if (!result_) result_ = damage_from_clause(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // FROM_TOKEN selector
  static boolean damage_from_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "damage_from_clause")) return false;
    if (!nextTokenIs(builder_, FROM_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, FROM_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // DATA_TOKEN data_subcommand
  public static boolean data_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_command")) return false;
    if (!nextTokenIs(builder_, DATA_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, DATA_COMMAND, null);
    result_ = consumeToken(builder_, DATA_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && data_subcommand(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (SET_TOKEN | MERGE_TOKEN | keyword | COMMAND_TOKEN) data_source_value
  static boolean data_modify_source(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_modify_source")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = data_modify_source_0(builder_, level_ + 1);
    result_ = result_ && data_source_value(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SET_TOKEN | MERGE_TOKEN | keyword | COMMAND_TOKEN
  private static boolean data_modify_source_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_modify_source_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, SET_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MERGE_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // FROM_TOKEN data_target nbt_path? | VALUE_TOKEN nbt_value
  static boolean data_source_value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_source_value")) return false;
    if (!nextTokenIs(builder_, "", FROM_TOKEN, VALUE_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = data_source_value_0(builder_, level_ + 1);
    if (!result_) result_ = data_source_value_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // FROM_TOKEN data_target nbt_path?
  private static boolean data_source_value_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_source_value_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, FROM_TOKEN);
    result_ = result_ && data_target(builder_, level_ + 1);
    result_ = result_ && data_source_value_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // nbt_path?
  private static boolean data_source_value_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_source_value_0_2")) return false;
    nbt_path(builder_, level_ + 1);
    return true;
  }

  // VALUE_TOKEN nbt_value
  private static boolean data_source_value_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_source_value_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VALUE_TOKEN);
    result_ = result_ && nbt_value(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // GET_TOKEN data_target nbt_path?
  //   | MERGE_TOKEN data_target nbt_compound
  //   | MODIFY_TOKEN data_target nbt_path data_modify_source
  //   | REMOVE_TOKEN data_target nbt_path
  static boolean data_subcommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_subcommand")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = data_subcommand_0(builder_, level_ + 1);
    if (!result_) result_ = data_subcommand_1(builder_, level_ + 1);
    if (!result_) result_ = data_subcommand_2(builder_, level_ + 1);
    if (!result_) result_ = data_subcommand_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // GET_TOKEN data_target nbt_path?
  private static boolean data_subcommand_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_subcommand_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, GET_TOKEN);
    result_ = result_ && data_target(builder_, level_ + 1);
    result_ = result_ && data_subcommand_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // nbt_path?
  private static boolean data_subcommand_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_subcommand_0_2")) return false;
    nbt_path(builder_, level_ + 1);
    return true;
  }

  // MERGE_TOKEN data_target nbt_compound
  private static boolean data_subcommand_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_subcommand_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MERGE_TOKEN);
    result_ = result_ && data_target(builder_, level_ + 1);
    result_ = result_ && nbt_compound(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // MODIFY_TOKEN data_target nbt_path data_modify_source
  private static boolean data_subcommand_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_subcommand_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MODIFY_TOKEN);
    result_ = result_ && data_target(builder_, level_ + 1);
    result_ = result_ && nbt_path(builder_, level_ + 1);
    result_ = result_ && data_modify_source(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // REMOVE_TOKEN data_target nbt_path
  private static boolean data_subcommand_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_subcommand_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REMOVE_TOKEN);
    result_ = result_ && data_target(builder_, level_ + 1);
    result_ = result_ && nbt_path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BLOCK_TOKEN coordinate | ENTITY_TOKEN selector | STORAGE_TOKEN namespaced_id
  static boolean data_target(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_target")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = data_target_0(builder_, level_ + 1);
    if (!result_) result_ = data_target_1(builder_, level_ + 1);
    if (!result_) result_ = data_target_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // BLOCK_TOKEN coordinate
  private static boolean data_target_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_target_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BLOCK_TOKEN);
    result_ = result_ && coordinate(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ENTITY_TOKEN selector
  private static boolean data_target_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_target_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ENTITY_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STORAGE_TOKEN namespaced_id
  private static boolean data_target_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_target_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STORAGE_TOKEN);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ALIGN_TOKEN (ARGUMENT_TOKEN | COMMAND_TOKEN | keyword)
  public static boolean execute_align_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_align_clause")) return false;
    if (!nextTokenIs(builder_, ALIGN_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_ALIGN_CLAUSE, null);
    result_ = consumeToken(builder_, ALIGN_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_align_clause_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ARGUMENT_TOKEN | COMMAND_TOKEN | keyword
  private static boolean execute_align_clause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_align_clause_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // ANCHORED_TOKEN (EYES_TOKEN | FEET_TOKEN)
  public static boolean execute_anchored_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_anchored_clause")) return false;
    if (!nextTokenIs(builder_, ANCHORED_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_ANCHORED_CLAUSE, null);
    result_ = consumeToken(builder_, ANCHORED_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_anchored_clause_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // EYES_TOKEN | FEET_TOKEN
  private static boolean execute_anchored_clause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_anchored_clause_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, EYES_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FEET_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // namespaced_id | selector | coordinate | nbt_compound | item_stack | COORD_TOKEN | ARGUMENT_TOKEN | STRING_TOKEN
  //   | SELECTOR_S | SELECTOR_A | SELECTOR_P | SELECTOR_E | SELECTOR_R
  //   | MACRO_TOKEN | MACRO_VAR_TOKEN
  //   | GTE_TOKEN | LTE_TOKEN | GT_TOKEN | LT_TOKEN | DOTDOT_TOKEN | DOT_TOKEN
  //   | COLON | EQUALS | LBRACK | RBRACK | LBRACE | RBRACE | COMMA
  static boolean execute_argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_argument")) return false;
    boolean result_;
    result_ = namespaced_id(builder_, level_ + 1);
    if (!result_) result_ = selector(builder_, level_ + 1);
    if (!result_) result_ = coordinate(builder_, level_ + 1);
    if (!result_) result_ = nbt_compound(builder_, level_ + 1);
    if (!result_) result_ = item_stack(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COORD_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_S);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_A);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_P);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_E);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_R);
    if (!result_) result_ = consumeToken(builder_, MACRO_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MACRO_VAR_TOKEN);
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
    return result_;
  }

  /* ********************************************************** */
  // AS_TOKEN selector
  public static boolean execute_as_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_as_clause")) return false;
    if (!nextTokenIs(builder_, AS_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_AS_CLAUSE, null);
    result_ = consumeToken(builder_, AS_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // AT_TOKEN selector
  public static boolean execute_at_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_at_clause")) return false;
    if (!nextTokenIs(builder_, AT_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_AT_CLAUSE, null);
    result_ = consumeToken(builder_, AT_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // execute_modifier_clause* (RUN_TOKEN command_line)?
  static boolean execute_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_body_0(builder_, level_ + 1);
    result_ = result_ && execute_body_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // execute_modifier_clause*
  private static boolean execute_body_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!execute_modifier_clause(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "execute_body_0", pos_)) break;
    }
    return true;
  }

  // (RUN_TOKEN command_line)?
  private static boolean execute_body_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_1")) return false;
    execute_body_1_0(builder_, level_ + 1);
    return true;
  }

  // RUN_TOKEN command_line
  private static boolean execute_body_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_body_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RUN_TOKEN);
    result_ = result_ && command_line(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EXECUTE_TOKEN execute_body?
  public static boolean execute_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_command")) return false;
    if (!nextTokenIs(builder_, EXECUTE_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_COMMAND, null);
    result_ = consumeToken(builder_, EXECUTE_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_command_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // execute_body?
  private static boolean execute_command_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_command_1")) return false;
    execute_body(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // FACING_TOKEN (ENTITY_TOKEN selector (EYES_TOKEN | FEET_TOKEN) | coordinate coordinate coordinate)
  public static boolean execute_facing_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_facing_clause")) return false;
    if (!nextTokenIs(builder_, FACING_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_FACING_CLAUSE, null);
    result_ = consumeToken(builder_, FACING_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_facing_clause_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ENTITY_TOKEN selector (EYES_TOKEN | FEET_TOKEN) | coordinate coordinate coordinate
  private static boolean execute_facing_clause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_facing_clause_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_facing_clause_1_0(builder_, level_ + 1);
    if (!result_) result_ = execute_facing_clause_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ENTITY_TOKEN selector (EYES_TOKEN | FEET_TOKEN)
  private static boolean execute_facing_clause_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_facing_clause_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ENTITY_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    result_ = result_ && execute_facing_clause_1_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // EYES_TOKEN | FEET_TOKEN
  private static boolean execute_facing_clause_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_facing_clause_1_0_2")) return false;
    boolean result_;
    result_ = consumeToken(builder_, EYES_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FEET_TOKEN);
    return result_;
  }

  // coordinate coordinate coordinate
  private static boolean execute_facing_clause_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_facing_clause_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = coordinate(builder_, level_ + 1);
    result_ = result_ && coordinate(builder_, level_ + 1);
    result_ = result_ && coordinate(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // keyword_not_namespaced | json | execute_argument
  public static boolean execute_generic_modifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_generic_modifier")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_GENERIC_MODIFIER, "<execute generic modifier>");
    result_ = keyword_not_namespaced(builder_, level_ + 1);
    if (!result_) result_ = json(builder_, level_ + 1);
    if (!result_) result_ = execute_argument(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IF_TOKEN execute_if_condition
  public static boolean execute_if_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_clause")) return false;
    if (!nextTokenIs(builder_, IF_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_IF_CLAUSE, null);
    result_ = consumeToken(builder_, IF_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_if_condition(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // BLOCK_TOKEN coordinate namespaced_id | ENTITY_TOKEN selector | SCORE_TOKEN selector namespaced_id (MATCHES_TOKEN ARGUMENT_TOKEN | (GT_TOKEN | LT_TOKEN | GTE_TOKEN | LTE_TOKEN | EQUALS) selector namespaced_id) | STORAGE_TOKEN namespaced_id namespaced_id | DATA_TOKEN (BLOCK_TOKEN coordinate | ENTITY_TOKEN selector | STORAGE_TOKEN namespaced_id) namespaced_id?
  static boolean execute_if_condition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_if_condition_0(builder_, level_ + 1);
    if (!result_) result_ = execute_if_condition_1(builder_, level_ + 1);
    if (!result_) result_ = execute_if_condition_2(builder_, level_ + 1);
    if (!result_) result_ = execute_if_condition_3(builder_, level_ + 1);
    if (!result_) result_ = execute_if_condition_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // BLOCK_TOKEN coordinate namespaced_id
  private static boolean execute_if_condition_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BLOCK_TOKEN);
    result_ = result_ && coordinate(builder_, level_ + 1);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ENTITY_TOKEN selector
  private static boolean execute_if_condition_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ENTITY_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SCORE_TOKEN selector namespaced_id (MATCHES_TOKEN ARGUMENT_TOKEN | (GT_TOKEN | LT_TOKEN | GTE_TOKEN | LTE_TOKEN | EQUALS) selector namespaced_id)
  private static boolean execute_if_condition_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SCORE_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    result_ = result_ && execute_if_condition_2_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // MATCHES_TOKEN ARGUMENT_TOKEN | (GT_TOKEN | LT_TOKEN | GTE_TOKEN | LTE_TOKEN | EQUALS) selector namespaced_id
  private static boolean execute_if_condition_2_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_2_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = parseTokens(builder_, 0, MATCHES_TOKEN, ARGUMENT_TOKEN);
    if (!result_) result_ = execute_if_condition_2_3_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (GT_TOKEN | LT_TOKEN | GTE_TOKEN | LTE_TOKEN | EQUALS) selector namespaced_id
  private static boolean execute_if_condition_2_3_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_2_3_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_if_condition_2_3_1_0(builder_, level_ + 1);
    result_ = result_ && selector(builder_, level_ + 1);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // GT_TOKEN | LT_TOKEN | GTE_TOKEN | LTE_TOKEN | EQUALS
  private static boolean execute_if_condition_2_3_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_2_3_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, GT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EQUALS);
    return result_;
  }

  // STORAGE_TOKEN namespaced_id namespaced_id
  private static boolean execute_if_condition_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STORAGE_TOKEN);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // DATA_TOKEN (BLOCK_TOKEN coordinate | ENTITY_TOKEN selector | STORAGE_TOKEN namespaced_id) namespaced_id?
  private static boolean execute_if_condition_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DATA_TOKEN);
    result_ = result_ && execute_if_condition_4_1(builder_, level_ + 1);
    result_ = result_ && execute_if_condition_4_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // BLOCK_TOKEN coordinate | ENTITY_TOKEN selector | STORAGE_TOKEN namespaced_id
  private static boolean execute_if_condition_4_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_4_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_if_condition_4_1_0(builder_, level_ + 1);
    if (!result_) result_ = execute_if_condition_4_1_1(builder_, level_ + 1);
    if (!result_) result_ = execute_if_condition_4_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // BLOCK_TOKEN coordinate
  private static boolean execute_if_condition_4_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_4_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BLOCK_TOKEN);
    result_ = result_ && coordinate(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ENTITY_TOKEN selector
  private static boolean execute_if_condition_4_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_4_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ENTITY_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STORAGE_TOKEN namespaced_id
  private static boolean execute_if_condition_4_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_4_1_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STORAGE_TOKEN);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // namespaced_id?
  private static boolean execute_if_condition_4_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_if_condition_4_2")) return false;
    namespaced_id(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IN_TOKEN namespaced_id
  public static boolean execute_in_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_in_clause")) return false;
    if (!nextTokenIs(builder_, IN_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_IN_CLAUSE, null);
    result_ = consumeToken(builder_, IN_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // execute_namespaced_id component_list?
  static boolean execute_item_stack(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_item_stack")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_namespaced_id(builder_, level_ + 1);
    result_ = result_ && execute_item_stack_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // component_list?
  private static boolean execute_item_stack_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_item_stack_1")) return false;
    component_list(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // execute_as_clause
  //   | execute_at_clause
  //   | execute_store_clause
  //   | execute_if_clause
  //   | execute_unless_clause
  //   | execute_facing_clause
  //   | execute_rotated_clause
  //   | execute_position_clause
  //   | execute_on_clause
  //   | execute_summon_clause
  //   | execute_ride_clause
  //   | execute_anchored_clause
  //   | execute_in_clause
  //   | execute_align_clause
  //   | execute_generic_modifier
  public static boolean execute_modifier_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_modifier_clause")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_MODIFIER_CLAUSE, "<execute modifier clause>");
    result_ = execute_as_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_at_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_store_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_if_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_unless_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_facing_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_rotated_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_position_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_on_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_summon_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_ride_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_anchored_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_in_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_align_clause(builder_, level_ + 1);
    if (!result_) result_ = execute_generic_modifier(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // safe_identifier (COLON safe_namespaced_path)?
  static boolean execute_namespaced_id(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_namespaced_id")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = safe_identifier(builder_, level_ + 1);
    result_ = result_ && execute_namespaced_id_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON safe_namespaced_path)?
  private static boolean execute_namespaced_id_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_namespaced_id_1")) return false;
    execute_namespaced_id_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON safe_namespaced_path
  private static boolean execute_namespaced_id_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_namespaced_id_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && safe_namespaced_path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ON_TOKEN (ARGUMENT_TOKEN | COMMAND_TOKEN | keyword)
  public static boolean execute_on_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_on_clause")) return false;
    if (!nextTokenIs(builder_, ON_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_ON_CLAUSE, null);
    result_ = consumeToken(builder_, ON_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_on_clause_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ARGUMENT_TOKEN | COMMAND_TOKEN | keyword
  private static boolean execute_on_clause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_on_clause_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // POSITION_TOKEN (AS_TOKEN selector | coordinate coordinate coordinate)
  public static boolean execute_position_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_position_clause")) return false;
    if (!nextTokenIs(builder_, POSITION_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_POSITION_CLAUSE, null);
    result_ = consumeToken(builder_, POSITION_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_position_clause_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // AS_TOKEN selector | coordinate coordinate coordinate
  private static boolean execute_position_clause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_position_clause_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_position_clause_1_0(builder_, level_ + 1);
    if (!result_) result_ = execute_position_clause_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // AS_TOKEN selector
  private static boolean execute_position_clause_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_position_clause_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AS_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // coordinate coordinate coordinate
  private static boolean execute_position_clause_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_position_clause_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = coordinate(builder_, level_ + 1);
    result_ = result_ && coordinate(builder_, level_ + 1);
    result_ = result_ && coordinate(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // RIDE_TOKEN (MOUNT_TOKEN | DISMOUNT_TOKEN)
  public static boolean execute_ride_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_ride_clause")) return false;
    if (!nextTokenIs(builder_, RIDE_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_RIDE_CLAUSE, null);
    result_ = consumeToken(builder_, RIDE_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_ride_clause_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // MOUNT_TOKEN | DISMOUNT_TOKEN
  private static boolean execute_ride_clause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_ride_clause_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, MOUNT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DISMOUNT_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // ROTATED_TOKEN (AS_TOKEN selector | coordinate coordinate)
  public static boolean execute_rotated_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_rotated_clause")) return false;
    if (!nextTokenIs(builder_, ROTATED_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_ROTATED_CLAUSE, null);
    result_ = consumeToken(builder_, ROTATED_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_rotated_clause_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // AS_TOKEN selector | coordinate coordinate
  private static boolean execute_rotated_clause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_rotated_clause_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_rotated_clause_1_0(builder_, level_ + 1);
    if (!result_) result_ = execute_rotated_clause_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // AS_TOKEN selector
  private static boolean execute_rotated_clause_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_rotated_clause_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AS_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // coordinate coordinate
  private static boolean execute_rotated_clause_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_rotated_clause_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = coordinate(builder_, level_ + 1);
    result_ = result_ && coordinate(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BLOCK_TOKEN coordinate execute_namespaced_id (COMMAND_TOKEN | keyword_not_namespaced) ARGUMENT_TOKEN
  static boolean execute_store_block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_block")) return false;
    if (!nextTokenIs(builder_, BLOCK_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BLOCK_TOKEN);
    result_ = result_ && coordinate(builder_, level_ + 1);
    result_ = result_ && execute_namespaced_id(builder_, level_ + 1);
    result_ = result_ && execute_store_block_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ARGUMENT_TOKEN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMAND_TOKEN | keyword_not_namespaced
  private static boolean execute_store_block_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_block_3")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword_not_namespaced(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // STORE_TOKEN (RESULT_TOKEN | SUCCESS_TOKEN) (execute_store_storage | execute_store_entity | execute_store_block | execute_store_score | ARGUMENT_TOKEN*)
  public static boolean execute_store_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_clause")) return false;
    if (!nextTokenIs(builder_, STORE_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_STORE_CLAUSE, null);
    result_ = consumeToken(builder_, STORE_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, execute_store_clause_1(builder_, level_ + 1));
    result_ = pinned_ && execute_store_clause_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // RESULT_TOKEN | SUCCESS_TOKEN
  private static boolean execute_store_clause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_clause_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, RESULT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SUCCESS_TOKEN);
    return result_;
  }

  // execute_store_storage | execute_store_entity | execute_store_block | execute_store_score | ARGUMENT_TOKEN*
  private static boolean execute_store_clause_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_clause_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = execute_store_storage(builder_, level_ + 1);
    if (!result_) result_ = execute_store_entity(builder_, level_ + 1);
    if (!result_) result_ = execute_store_block(builder_, level_ + 1);
    if (!result_) result_ = execute_store_score(builder_, level_ + 1);
    if (!result_) result_ = execute_store_clause_2_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ARGUMENT_TOKEN*
  private static boolean execute_store_clause_2_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_clause_2_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, ARGUMENT_TOKEN)) break;
      if (!empty_element_parsed_guard_(builder_, "execute_store_clause_2_4", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ENTITY_TOKEN selector execute_namespaced_id (COMMAND_TOKEN | keyword_not_namespaced) ARGUMENT_TOKEN
  static boolean execute_store_entity(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_entity")) return false;
    if (!nextTokenIs(builder_, ENTITY_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ENTITY_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    result_ = result_ && execute_namespaced_id(builder_, level_ + 1);
    result_ = result_ && execute_store_entity_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ARGUMENT_TOKEN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMAND_TOKEN | keyword_not_namespaced
  private static boolean execute_store_entity_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_entity_3")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword_not_namespaced(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // SCORE_TOKEN selector execute_namespaced_id
  static boolean execute_store_score(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_score")) return false;
    if (!nextTokenIs(builder_, SCORE_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SCORE_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    result_ = result_ && execute_namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // STORAGE_TOKEN execute_namespaced_id execute_namespaced_id (COMMAND_TOKEN | keyword_not_namespaced) ARGUMENT_TOKEN
  static boolean execute_store_storage(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_storage")) return false;
    if (!nextTokenIs(builder_, STORAGE_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STORAGE_TOKEN);
    result_ = result_ && execute_namespaced_id(builder_, level_ + 1);
    result_ = result_ && execute_namespaced_id(builder_, level_ + 1);
    result_ = result_ && execute_store_storage_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ARGUMENT_TOKEN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMAND_TOKEN | keyword_not_namespaced
  private static boolean execute_store_storage_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_store_storage_3")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword_not_namespaced(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // SUMMON_TOKEN namespaced_id
  public static boolean execute_summon_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_summon_clause")) return false;
    if (!nextTokenIs(builder_, SUMMON_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_SUMMON_CLAUSE, null);
    result_ = consumeToken(builder_, SUMMON_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // UNLESS_TOKEN execute_if_condition
  public static boolean execute_unless_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "execute_unless_clause")) return false;
    if (!nextTokenIs(builder_, UNLESS_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXECUTE_UNLESS_CLAUSE, null);
    result_ = consumeToken(builder_, UNLESS_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && execute_if_condition(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (damage_command | ride_command | command | json) (CONTINUATION_TOKEN? (nbt_compound | json | namespaced_id | argument))*
  public static boolean generic_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, GENERIC_COMMAND, "<generic command>");
    result_ = generic_command_0(builder_, level_ + 1);
    result_ = result_ && generic_command_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // damage_command | ride_command | command | json
  private static boolean generic_command_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_0")) return false;
    boolean result_;
    result_ = damage_command(builder_, level_ + 1);
    if (!result_) result_ = ride_command(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    if (!result_) result_ = json(builder_, level_ + 1);
    return result_;
  }

  // (CONTINUATION_TOKEN? (nbt_compound | json | namespaced_id | argument))*
  private static boolean generic_command_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!generic_command_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_command_1", pos_)) break;
    }
    return true;
  }

  // CONTINUATION_TOKEN? (nbt_compound | json | namespaced_id | argument)
  private static boolean generic_command_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = generic_command_1_0_0(builder_, level_ + 1);
    result_ = result_ && generic_command_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CONTINUATION_TOKEN?
  private static boolean generic_command_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_1_0_0")) return false;
    consumeToken(builder_, CONTINUATION_TOKEN);
    return true;
  }

  // nbt_compound | json | namespaced_id | argument
  private static boolean generic_command_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_command_1_0_1")) return false;
    boolean result_;
    result_ = nbt_compound(builder_, level_ + 1);
    if (!result_) result_ = json(builder_, level_ + 1);
    if (!result_) result_ = namespaced_id(builder_, level_ + 1);
    if (!result_) result_ = argument(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // GIVE_TOKEN selector item_stack argument*
  public static boolean give_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "give_command")) return false;
    if (!nextTokenIs(builder_, GIVE_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, GIVE_COMMAND, null);
    result_ = consumeToken(builder_, GIVE_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, selector(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, item_stack(builder_, level_ + 1)) && result_;
    result_ = pinned_ && give_command_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // argument*
  private static boolean give_command_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "give_command_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!argument(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "give_command_3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // COMMAND_TOKEN | ARGUMENT_TOKEN | keyword | command
  static boolean identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // ITEM_TOKEN (item_modify_clause | item_replace_clause)
  public static boolean item_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_command")) return false;
    if (!nextTokenIs(builder_, ITEM_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_COMMAND, null);
    result_ = consumeToken(builder_, ITEM_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && item_command_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // item_modify_clause | item_replace_clause
  private static boolean item_command_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_command_1")) return false;
    boolean result_;
    result_ = item_modify_clause(builder_, level_ + 1);
    if (!result_) result_ = item_replace_clause(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // ns_identifier (COLON namespaced_path)?
  public static boolean item_id(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_id")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_ID, "<item id>");
    result_ = ns_identifier(builder_, level_ + 1);
    result_ = result_ && item_id_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COLON namespaced_path)?
  private static boolean item_id_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_id_1")) return false;
    item_id_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON namespaced_path
  private static boolean item_id_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_id_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && namespaced_path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // MODIFY_TOKEN item_target item_slot namespaced_id
  static boolean item_modify_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_modify_clause")) return false;
    if (!nextTokenIs(builder_, MODIFY_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MODIFY_TOKEN);
    result_ = result_ && item_target(builder_, level_ + 1);
    result_ = result_ && item_slot(builder_, level_ + 1);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // REPLACE_TOKEN item_target item_slot WITH_TOKEN item_stack ARGUMENT_TOKEN?
  static boolean item_replace_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_replace_clause")) return false;
    if (!nextTokenIs(builder_, REPLACE_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REPLACE_TOKEN);
    result_ = result_ && item_target(builder_, level_ + 1);
    result_ = result_ && item_slot(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, WITH_TOKEN);
    result_ = result_ && item_stack(builder_, level_ + 1);
    result_ = result_ && item_replace_clause_5(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ARGUMENT_TOKEN?
  private static boolean item_replace_clause_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_replace_clause_5")) return false;
    consumeToken(builder_, ARGUMENT_TOKEN);
    return true;
  }

  /* ********************************************************** */
  // slot_id | namespaced_id | ARGUMENT_TOKEN
  public static boolean item_slot(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_slot")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_SLOT, "<item slot>");
    result_ = slot_id(builder_, level_ + 1);
    if (!result_) result_ = namespaced_id(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // namespaced_id component_list?
  public static boolean item_stack(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_stack")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_STACK, "<item stack>");
    result_ = namespaced_id(builder_, level_ + 1);
    result_ = result_ && item_stack_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // component_list?
  private static boolean item_stack_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_stack_1")) return false;
    component_list(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // BLOCK_TOKEN coordinate | ENTITY_TOKEN selector | STORAGE_TOKEN namespaced_id
  public static boolean item_target(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_target")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_TARGET, "<item target>");
    result_ = item_target_0(builder_, level_ + 1);
    if (!result_) result_ = item_target_1(builder_, level_ + 1);
    if (!result_) result_ = item_target_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // BLOCK_TOKEN coordinate
  private static boolean item_target_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_target_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BLOCK_TOKEN);
    result_ = result_ && coordinate(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ENTITY_TOKEN selector
  private static boolean item_target_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_target_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ENTITY_TOKEN);
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STORAGE_TOKEN namespaced_id
  private static boolean item_target_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_target_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STORAGE_TOKEN);
    result_ = result_ && namespaced_id(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
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
  // json_value
  static boolean json_element(PsiBuilder builder_, int level_) {
    return json_value(builder_, level_ + 1);
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
  // json_key (COLON | EQUALS) json_value
  static boolean json_property(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = json_key(builder_, level_ + 1);
    result_ = result_ && json_property_1(builder_, level_ + 1);
    result_ = result_ && json_value(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COLON | EQUALS
  private static boolean json_property_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "json_property_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, EQUALS);
    return result_;
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
  // control_keyword | ONLY_TOKEN | ENTITY_TOKEN | MODIFY_TOKEN | STORAGE_TOKEN | SET_TOKEN
  //   | FROM_TOKEN | ADD_TOKEN | PLAYERS_TOKEN | ACTIONBAR_TOKEN | MATCHES_TOKEN | AS_TOKEN | AT_TOKEN | ANCHORED_TOKEN | FACING_TOKEN | BLOCK_TOKEN
  //   | ITEMS_TOKEN | STORE_TOKEN | SCORE_TOKEN | TEXT_TOKEN | VALUE_TOKEN | EYES_TOKEN | REVOKE_TOKEN | GRANT_TOKEN | DATA_TOKEN
  //   | GET_TOKEN | MERGE_TOKEN | REMOVE_TOKEN | ENABLE_TOKEN | DISABLE_TOKEN | BASE_TOKEN | MODIFIER_TOKEN
  //   | QUERY_TOKEN | TAKE_TOKEN | OBJECTIVES_TOKEN | SETDISPLAY_TOKEN | EMPTY_TOKEN | JOIN_TOKEN | LEAVE_TOKEN
  //   | RATE_TOKEN | FREEZE_TOKEN | STEP_TOKEN | STOP_TOKEN | UNFREEZE_TOKEN | SUBTITLE_TOKEN | TIMES_TOKEN
  //   | CENTER_TOKEN | WARNING_TOKEN | MASTER_TOKEN | MUSIC_TOKEN
  //   | IF_TOKEN | UNLESS_TOKEN
  public static boolean keyword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keyword")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, KEYWORD, "<keyword>");
    result_ = control_keyword(builder_, level_ + 1);
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
  // ONLY_TOKEN | ENTITY_TOKEN | MODIFY_TOKEN | SET_TOKEN
  //   | FROM_TOKEN | ADD_TOKEN | PLAYERS_TOKEN | ACTIONBAR_TOKEN | MATCHES_TOKEN | ANCHORED_TOKEN | FACING_TOKEN
  //   | ITEMS_TOKEN | TEXT_TOKEN | VALUE_TOKEN | EYES_TOKEN | REVOKE_TOKEN | GRANT_TOKEN
  //   | MERGE_TOKEN | REMOVE_TOKEN | ENABLE_TOKEN | DISABLE_TOKEN | BASE_TOKEN | MODIFIER_TOKEN
  //   | QUERY_TOKEN | TAKE_TOKEN | OBJECTIVES_TOKEN | SETDISPLAY_TOKEN | EMPTY_TOKEN | JOIN_TOKEN | LEAVE_TOKEN
  //   | RATE_TOKEN | FREEZE_TOKEN | STEP_TOKEN | STOP_TOKEN | UNFREEZE_TOKEN | SUBTITLE_TOKEN | TIMES_TOKEN
  //   | CENTER_TOKEN | WARNING_TOKEN | MASTER_TOKEN | MUSIC_TOKEN
  //   | FEET_TOKEN
  static boolean keyword_not_namespaced(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keyword_not_namespaced")) return false;
    boolean result_;
    result_ = consumeToken(builder_, ONLY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ENTITY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MODIFY_TOKEN);
    if (!result_) result_ = consumeToken(builder_, SET_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FROM_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ADD_TOKEN);
    if (!result_) result_ = consumeToken(builder_, PLAYERS_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ACTIONBAR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MATCHES_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ANCHORED_TOKEN);
    if (!result_) result_ = consumeToken(builder_, FACING_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ITEMS_TOKEN);
    if (!result_) result_ = consumeToken(builder_, TEXT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, VALUE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EYES_TOKEN);
    if (!result_) result_ = consumeToken(builder_, REVOKE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GRANT_TOKEN);
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
    if (!result_) result_ = consumeToken(builder_, FEET_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // MACRO_LINE_START macro_line_content*
  public static boolean macro_line(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "macro_line")) return false;
    if (!nextTokenIs(builder_, MACRO_LINE_START)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MACRO_LINE, null);
    result_ = consumeToken(builder_, MACRO_LINE_START);
    pinned_ = result_; // pin = 1
    result_ = result_ && macro_line_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // macro_line_content*
  private static boolean macro_line_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "macro_line_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!macro_line_content(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "macro_line_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // macro_variable | ARGUMENT_TOKEN | COMMAND_TOKEN | STRING_TOKEN
  //   | selector | SELECTOR_S | SELECTOR_A | SELECTOR_P | SELECTOR_E | SELECTOR_R
  //   | keyword | command | MACRO_TOKEN | COLON | DOT_TOKEN | DOTDOT_TOKEN
  //   | GTE_TOKEN | LTE_TOKEN | GT_TOKEN | LT_TOKEN | EQUALS | COMMA
  //   | LBRACK | RBRACK | LBRACE | RBRACE
  static boolean macro_line_content(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "macro_line_content")) return false;
    boolean result_;
    result_ = macro_variable(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = selector(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_S);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_A);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_P);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_E);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_R);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, MACRO_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, DOT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DOTDOT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, EQUALS);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, LBRACK);
    if (!result_) result_ = consumeToken(builder_, RBRACK);
    if (!result_) result_ = consumeToken(builder_, LBRACE);
    if (!result_) result_ = consumeToken(builder_, RBRACE);
    return result_;
  }

  /* ********************************************************** */
  // MACRO_VAR_TOKEN | MACRO_TOKEN
  public static boolean macro_variable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "macro_variable")) return false;
    if (!nextTokenIs(builder_, "<macro variable>", MACRO_TOKEN, MACRO_VAR_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MACRO_VARIABLE, "<macro variable>");
    result_ = consumeToken(builder_, MACRO_VAR_TOKEN);
    if (!result_) result_ = consumeToken(builder_, MACRO_TOKEN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (command_line | CRLF_TOKEN | COMMENT_TOKEN | CONTINUATION_TOKEN)*
  static boolean mcFunctionFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mcFunctionFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!mcFunctionFile_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "mcFunctionFile", pos_)) break;
    }
    return true;
  }

  // command_line | CRLF_TOKEN | COMMENT_TOKEN | CONTINUATION_TOKEN
  private static boolean mcFunctionFile_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mcFunctionFile_0")) return false;
    boolean result_;
    result_ = command_line(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, CRLF_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, CONTINUATION_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // ns_identifier (COLON namespaced_path)?
  public static boolean namespaced_id(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "namespaced_id")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NAMESPACED_ID, "<namespaced id>");
    result_ = ns_identifier(builder_, level_ + 1);
    result_ = result_ && namespaced_id_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COLON namespaced_path)?
  private static boolean namespaced_id_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "namespaced_id_1")) return false;
    namespaced_id_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON namespaced_path
  private static boolean namespaced_id_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "namespaced_id_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && namespaced_path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ns_identifier (DOT_TOKEN ns_identifier)*
  static boolean namespaced_path(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "namespaced_path")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ns_identifier(builder_, level_ + 1);
    result_ = result_ && namespaced_path_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (DOT_TOKEN ns_identifier)*
  private static boolean namespaced_path_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "namespaced_path_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!namespaced_path_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "namespaced_path_1", pos_)) break;
    }
    return true;
  }

  // DOT_TOKEN ns_identifier
  private static boolean namespaced_path_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "namespaced_path_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_TOKEN);
    result_ = result_ && ns_identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LBRACE (nbt_property (COMMA nbt_property)*)? RBRACE
  public static boolean nbt_compound(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_compound")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NBT_COMPOUND, null);
    result_ = consumeToken(builder_, LBRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, nbt_compound_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RBRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (nbt_property (COMMA nbt_property)*)?
  private static boolean nbt_compound_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_compound_1")) return false;
    nbt_compound_1_0(builder_, level_ + 1);
    return true;
  }

  // nbt_property (COMMA nbt_property)*
  private static boolean nbt_compound_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_compound_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = nbt_property(builder_, level_ + 1);
    result_ = result_ && nbt_compound_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA nbt_property)*
  private static boolean nbt_compound_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_compound_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!nbt_compound_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "nbt_compound_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA nbt_property
  private static boolean nbt_compound_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_compound_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && nbt_property(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // STRING_TOKEN | COMMAND_TOKEN | ARGUMENT_TOKEN | keyword | command
  static boolean nbt_key(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_key")) return false;
    boolean result_;
    result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // LBRACK (nbt_value (COMMA nbt_value)*)? RBRACK
  public static boolean nbt_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_list")) return false;
    if (!nextTokenIs(builder_, LBRACK)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NBT_LIST, null);
    result_ = consumeToken(builder_, LBRACK);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, nbt_list_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RBRACK) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (nbt_value (COMMA nbt_value)*)?
  private static boolean nbt_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_list_1")) return false;
    nbt_list_1_0(builder_, level_ + 1);
    return true;
  }

  // nbt_value (COMMA nbt_value)*
  private static boolean nbt_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = nbt_value(builder_, level_ + 1);
    result_ = result_ && nbt_list_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA nbt_value)*
  private static boolean nbt_list_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_list_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!nbt_list_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "nbt_list_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA nbt_value
  private static boolean nbt_list_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_list_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && nbt_value(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // nbt_path_step (DOT_TOKEN nbt_path_step | nbt_compound)*
  public static boolean nbt_path(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_path")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NBT_PATH, "<nbt path>");
    result_ = nbt_path_step(builder_, level_ + 1);
    result_ = result_ && nbt_path_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (DOT_TOKEN nbt_path_step | nbt_compound)*
  private static boolean nbt_path_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_path_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!nbt_path_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "nbt_path_1", pos_)) break;
    }
    return true;
  }

  // DOT_TOKEN nbt_path_step | nbt_compound
  private static boolean nbt_path_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_path_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = nbt_path_1_0_0(builder_, level_ + 1);
    if (!result_) result_ = nbt_compound(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // DOT_TOKEN nbt_path_step
  private static boolean nbt_path_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_path_1_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_TOKEN);
    result_ = result_ && nbt_path_step(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // namespaced_id | ARGUMENT_TOKEN | COMMAND_TOKEN | keyword
  static boolean nbt_path_step(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_path_step")) return false;
    boolean result_;
    result_ = namespaced_id(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // ARGUMENT_TOKEN | COMMAND_TOKEN | keyword | command
  //                 | GTE_TOKEN | LTE_TOKEN | GT_TOKEN | LT_TOKEN | DOTDOT_TOKEN | DOT_TOKEN
  public static boolean nbt_primitive(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_primitive")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NBT_PRIMITIVE, "<nbt primitive>");
    result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, GTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LTE_TOKEN);
    if (!result_) result_ = consumeToken(builder_, GT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, LT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DOTDOT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, DOT_TOKEN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // nbt_key (COLON | EQUALS) nbt_value
  public static boolean nbt_property(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_property")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NBT_PROPERTY, "<nbt property>");
    result_ = nbt_key(builder_, level_ + 1);
    result_ = result_ && nbt_property_1(builder_, level_ + 1);
    result_ = result_ && nbt_value(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // COLON | EQUALS
  private static boolean nbt_property_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_property_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, EQUALS);
    return result_;
  }

  /* ********************************************************** */
  // nbt_compound | nbt_list | STRING_TOKEN | nbt_primitive
  public static boolean nbt_value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "nbt_value")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NBT_VALUE, "<nbt value>");
    result_ = nbt_compound(builder_, level_ + 1);
    if (!result_) result_ = nbt_list(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = nbt_primitive(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // !control_keyword (COMMAND_TOKEN | keyword | command)
  static boolean ns_identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ns_identifier")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ns_identifier_0(builder_, level_ + 1);
    result_ = result_ && ns_identifier_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !control_keyword
  private static boolean ns_identifier_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ns_identifier_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !control_keyword(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // COMMAND_TOKEN | keyword | command
  private static boolean ns_identifier_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ns_identifier_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // ARGUMENT_TOKEN | COMMAND_TOKEN
  static boolean numeric_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "numeric_literal")) return false;
    if (!nextTokenIs(builder_, "", ARGUMENT_TOKEN, COMMAND_TOKEN)) return false;
    boolean result_;
    result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // RIDE_TOKEN selector (ride_mount_clause | DISMOUNT_TOKEN)
  public static boolean ride_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ride_command")) return false;
    if (!nextTokenIs(builder_, RIDE_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RIDE_COMMAND, null);
    result_ = consumeToken(builder_, RIDE_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, selector(builder_, level_ + 1));
    result_ = pinned_ && ride_command_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ride_mount_clause | DISMOUNT_TOKEN
  private static boolean ride_command_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ride_command_2")) return false;
    boolean result_;
    result_ = ride_mount_clause(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, DISMOUNT_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // MOUNT_TOKEN selector
  static boolean ride_mount_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ride_mount_clause")) return false;
    if (!nextTokenIs(builder_, MOUNT_TOKEN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, MOUNT_TOKEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && selector(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !control_keyword (COMMAND_TOKEN | keyword_not_namespaced | command | ARGUMENT_TOKEN)
  static boolean safe_identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "safe_identifier")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = safe_identifier_0(builder_, level_ + 1);
    result_ = result_ && safe_identifier_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !control_keyword
  private static boolean safe_identifier_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "safe_identifier_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !control_keyword(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // COMMAND_TOKEN | keyword_not_namespaced | command | ARGUMENT_TOKEN
  private static boolean safe_identifier_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "safe_identifier_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = keyword_not_namespaced(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // safe_identifier (DOT_TOKEN safe_identifier)*
  static boolean safe_namespaced_path(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "safe_namespaced_path")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = safe_identifier(builder_, level_ + 1);
    result_ = result_ && safe_namespaced_path_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (DOT_TOKEN safe_identifier)*
  private static boolean safe_namespaced_path_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "safe_namespaced_path_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!safe_namespaced_path_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "safe_namespaced_path_1", pos_)) break;
    }
    return true;
  }

  // DOT_TOKEN safe_identifier
  private static boolean safe_namespaced_path_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "safe_namespaced_path_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_TOKEN);
    result_ = result_ && safe_identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // selector_base selector_arguments?
  public static boolean selector(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SELECTOR, "<selector>");
    result_ = selector_base(builder_, level_ + 1);
    result_ = result_ && selector_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // selector_arguments?
  private static boolean selector_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_1")) return false;
    selector_arguments(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ns_identifier (COLON namespaced_path)?
  public static boolean selector_arg_key(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arg_key")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SELECTOR_ARG_KEY, "<selector arg key>");
    result_ = ns_identifier(builder_, level_ + 1);
    result_ = result_ && selector_arg_key_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COLON namespaced_path)?
  private static boolean selector_arg_key_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arg_key_1")) return false;
    selector_arg_key_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON namespaced_path
  private static boolean selector_arg_key_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arg_key_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && namespaced_path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // nbt_compound | nbt_list | STRING_TOKEN | namespaced_id | ARGUMENT_TOKEN
  static boolean selector_arg_value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arg_value")) return false;
    boolean result_;
    result_ = nbt_compound(builder_, level_ + 1);
    if (!result_) result_ = nbt_list(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRING_TOKEN);
    if (!result_) result_ = namespaced_id(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    return result_;
  }

  /* ********************************************************** */
  // selector_arg_key EQUALS selector_arg_value
  public static boolean selector_argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_argument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SELECTOR_ARGUMENT, "<selector argument>");
    result_ = selector_arg_key(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    result_ = result_ && selector_arg_value(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // LBRACK (selector_argument (COMMA selector_argument)*)? RBRACK
  public static boolean selector_arguments(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arguments")) return false;
    if (!nextTokenIs(builder_, LBRACK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACK);
    result_ = result_ && selector_arguments_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACK);
    exit_section_(builder_, marker_, SELECTOR_ARGUMENTS, result_);
    return result_;
  }

  // (selector_argument (COMMA selector_argument)*)?
  private static boolean selector_arguments_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arguments_1")) return false;
    selector_arguments_1_0(builder_, level_ + 1);
    return true;
  }

  // selector_argument (COMMA selector_argument)*
  private static boolean selector_arguments_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arguments_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = selector_argument(builder_, level_ + 1);
    result_ = result_ && selector_arguments_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA selector_argument)*
  private static boolean selector_arguments_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arguments_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!selector_arguments_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "selector_arguments_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA selector_argument
  private static boolean selector_arguments_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_arguments_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && selector_argument(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SELECTOR_S | SELECTOR_A | SELECTOR_P | SELECTOR_E | SELECTOR_R
  static boolean selector_base(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "selector_base")) return false;
    boolean result_;
    result_ = consumeToken(builder_, SELECTOR_S);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_A);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_P);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_E);
    if (!result_) result_ = consumeToken(builder_, SELECTOR_R);
    return result_;
  }

  /* ********************************************************** */
  // WEAPON_MAINHAND_TOKEN | WEAPON_OFFHAND_TOKEN | COMMAND_TOKEN | ARGUMENT_TOKEN
  public static boolean slot_id(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "slot_id")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SLOT_ID, "<slot id>");
    result_ = consumeToken(builder_, WEAPON_MAINHAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, WEAPON_OFFHAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, COMMAND_TOKEN);
    if (!result_) result_ = consumeToken(builder_, ARGUMENT_TOKEN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

}
