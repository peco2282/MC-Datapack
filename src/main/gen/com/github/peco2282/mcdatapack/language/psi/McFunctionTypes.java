// This is a generated file. Not intended for manual editing.
package com.github.peco2282.mcdatapack.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.peco2282.mcdatapack.language.psi.impl.*;

public interface McFunctionTypes {

  IElementType ARGUMENT = new McFunctionElementType("ARGUMENT");
  IElementType ATTRIBUTE_COMMAND = new McFunctionElementType("ATTRIBUTE_COMMAND");
  IElementType CLEAR_COMMAND = new McFunctionElementType("CLEAR_COMMAND");
  IElementType COMMAND = new McFunctionElementType("COMMAND");
  IElementType COMMAND_LINE = new McFunctionElementType("COMMAND_LINE");
  IElementType COMPONENT = new McFunctionElementType("COMPONENT");
  IElementType COMPONENT_LIST = new McFunctionElementType("COMPONENT_LIST");
  IElementType COMPONENT_VALUE = new McFunctionElementType("COMPONENT_VALUE");
  IElementType COORDINATE = new McFunctionElementType("COORDINATE");
  IElementType COORDINATE_OR_NUMERIC = new McFunctionElementType("COORDINATE_OR_NUMERIC");
  IElementType DAMAGE_COMMAND = new McFunctionElementType("DAMAGE_COMMAND");
  IElementType DATA_COMMAND = new McFunctionElementType("DATA_COMMAND");
  IElementType EXECUTE_ALIGN_CLAUSE = new McFunctionElementType("EXECUTE_ALIGN_CLAUSE");
  IElementType EXECUTE_ANCHORED_CLAUSE = new McFunctionElementType("EXECUTE_ANCHORED_CLAUSE");
  IElementType EXECUTE_AS_CLAUSE = new McFunctionElementType("EXECUTE_AS_CLAUSE");
  IElementType EXECUTE_AT_CLAUSE = new McFunctionElementType("EXECUTE_AT_CLAUSE");
  IElementType EXECUTE_COMMAND = new McFunctionElementType("EXECUTE_COMMAND");
  IElementType EXECUTE_FACING_CLAUSE = new McFunctionElementType("EXECUTE_FACING_CLAUSE");
  IElementType EXECUTE_GENERIC_MODIFIER = new McFunctionElementType("EXECUTE_GENERIC_MODIFIER");
  IElementType EXECUTE_IF_CLAUSE = new McFunctionElementType("EXECUTE_IF_CLAUSE");
  IElementType EXECUTE_IN_CLAUSE = new McFunctionElementType("EXECUTE_IN_CLAUSE");
  IElementType EXECUTE_MODIFIER_CLAUSE = new McFunctionElementType("EXECUTE_MODIFIER_CLAUSE");
  IElementType EXECUTE_ON_CLAUSE = new McFunctionElementType("EXECUTE_ON_CLAUSE");
  IElementType EXECUTE_POSITION_CLAUSE = new McFunctionElementType("EXECUTE_POSITION_CLAUSE");
  IElementType EXECUTE_RIDE_CLAUSE = new McFunctionElementType("EXECUTE_RIDE_CLAUSE");
  IElementType EXECUTE_ROTATED_CLAUSE = new McFunctionElementType("EXECUTE_ROTATED_CLAUSE");
  IElementType EXECUTE_STORE_CLAUSE = new McFunctionElementType("EXECUTE_STORE_CLAUSE");
  IElementType EXECUTE_SUMMON_CLAUSE = new McFunctionElementType("EXECUTE_SUMMON_CLAUSE");
  IElementType EXECUTE_UNLESS_CLAUSE = new McFunctionElementType("EXECUTE_UNLESS_CLAUSE");
  IElementType GENERIC_COMMAND = new McFunctionElementType("GENERIC_COMMAND");
  IElementType GIVE_COMMAND = new McFunctionElementType("GIVE_COMMAND");
  IElementType ITEM_COMMAND = new McFunctionElementType("ITEM_COMMAND");
  IElementType ITEM_ID = new McFunctionElementType("ITEM_ID");
  IElementType ITEM_SLOT = new McFunctionElementType("ITEM_SLOT");
  IElementType ITEM_STACK = new McFunctionElementType("ITEM_STACK");
  IElementType ITEM_TARGET = new McFunctionElementType("ITEM_TARGET");
  IElementType JSON = new McFunctionElementType("JSON");
  IElementType JSON_ARRAY = new McFunctionElementType("JSON_ARRAY");
  IElementType JSON_OBJECT = new McFunctionElementType("JSON_OBJECT");
  IElementType KEYWORD = new McFunctionElementType("KEYWORD");
  IElementType MACRO_LINE = new McFunctionElementType("MACRO_LINE");
  IElementType MACRO_VARIABLE = new McFunctionElementType("MACRO_VARIABLE");
  IElementType NAMESPACED_ID = new McFunctionElementType("NAMESPACED_ID");
  IElementType NBT_COMPOUND = new McFunctionElementType("NBT_COMPOUND");
  IElementType NBT_LIST = new McFunctionElementType("NBT_LIST");
  IElementType NBT_PATH = new McFunctionElementType("NBT_PATH");
  IElementType NBT_PRIMITIVE = new McFunctionElementType("NBT_PRIMITIVE");
  IElementType NBT_PROPERTY = new McFunctionElementType("NBT_PROPERTY");
  IElementType NBT_VALUE = new McFunctionElementType("NBT_VALUE");
  IElementType PARTICLE_COMMAND = new McFunctionElementType("PARTICLE_COMMAND");
  IElementType RETURN_COMMAND = new McFunctionElementType("RETURN_COMMAND");
  IElementType RIDE_COMMAND = new McFunctionElementType("RIDE_COMMAND");
  IElementType SELECTOR = new McFunctionElementType("SELECTOR");
  IElementType SELECTOR_ARGUMENT = new McFunctionElementType("SELECTOR_ARGUMENT");
  IElementType SELECTOR_ARGUMENTS = new McFunctionElementType("SELECTOR_ARGUMENTS");
  IElementType SELECTOR_ARG_KEY = new McFunctionElementType("SELECTOR_ARG_KEY");
  IElementType SLOT_ID = new McFunctionElementType("SLOT_ID");

  IElementType ACTIONBAR_TOKEN = new McFunctionTokenType("actionbar");
  IElementType ADD_TOKEN = new McFunctionTokenType("add");
  IElementType ADVANCEMENT_TOKEN = new McFunctionTokenType("advancement");
  IElementType ALIGN_TOKEN = new McFunctionTokenType("align");
  IElementType ANCHORED_TOKEN = new McFunctionTokenType("anchored");
  IElementType ARGUMENT_TOKEN = new McFunctionTokenType("ARGUMENT_TOKEN");
  IElementType AS_TOKEN = new McFunctionTokenType("as");
  IElementType ATTRIBUTE_TOKEN = new McFunctionTokenType("attribute");
  IElementType AT_TOKEN = new McFunctionTokenType("at");
  IElementType BASE_TOKEN = new McFunctionTokenType("base");
  IElementType BLOCK_TOKEN = new McFunctionTokenType("block");
  IElementType BOSSBAR_TOKEN = new McFunctionTokenType("bossbar");
  IElementType BY_TOKEN = new McFunctionTokenType("by");
  IElementType CENTER_TOKEN = new McFunctionTokenType("center");
  IElementType CLEAR_TOKEN = new McFunctionTokenType("clear");
  IElementType CLONE_TOKEN = new McFunctionTokenType("clone");
  IElementType COLON = new McFunctionTokenType(":");
  IElementType COMMA = new McFunctionTokenType(",");
  IElementType COMMAND_TOKEN = new McFunctionTokenType("COMMAND_TOKEN");
  IElementType COMMENT_TOKEN = new McFunctionTokenType("COMMENT_TOKEN");
  IElementType CONTINUATION_TOKEN = new McFunctionTokenType("CONTINUATION_TOKEN");
  IElementType COORD_TOKEN = new McFunctionTokenType("COORD_TOKEN");
  IElementType COPY_TOKEN = new McFunctionTokenType("copy");
  IElementType CRLF_TOKEN = new McFunctionTokenType("CRLF_TOKEN");
  IElementType DAMAGE_TOKEN = new McFunctionTokenType("damage");
  IElementType DATAPACK_TOKEN = new McFunctionTokenType("datapack");
  IElementType DATA_TOKEN = new McFunctionTokenType("data");
  IElementType DEBUG_TOKEN = new McFunctionTokenType("debug");
  IElementType DEFAULTGAMEMODE_TOKEN = new McFunctionTokenType("defaultgamemode");
  IElementType DIFFICULTY_TOKEN = new McFunctionTokenType("difficulty");
  IElementType DISABLE_TOKEN = new McFunctionTokenType("disable");
  IElementType DISMOUNT_TOKEN = new McFunctionTokenType("dismount");
  IElementType DOTDOT_TOKEN = new McFunctionTokenType("..");
  IElementType DOT_TOKEN = new McFunctionTokenType(".");
  IElementType EFFECT_TOKEN = new McFunctionTokenType("effect");
  IElementType EMPTY_TOKEN = new McFunctionTokenType("empty");
  IElementType ENABLE_TOKEN = new McFunctionTokenType("enable");
  IElementType ENCHANT_TOKEN = new McFunctionTokenType("enchant");
  IElementType ENTITY_TOKEN = new McFunctionTokenType("entity");
  IElementType EQUALS = new McFunctionTokenType("=");
  IElementType EXECUTE_TOKEN = new McFunctionTokenType("execute");
  IElementType EXPERIENCE_TOKEN = new McFunctionTokenType("experience");
  IElementType EYES_TOKEN = new McFunctionTokenType("eyes");
  IElementType FACING_TOKEN = new McFunctionTokenType("facing");
  IElementType FEET_TOKEN = new McFunctionTokenType("feet");
  IElementType FILLBIOME_TOKEN = new McFunctionTokenType("fillbiome");
  IElementType FILL_TOKEN = new McFunctionTokenType("fill");
  IElementType FORCELOAD_TOKEN = new McFunctionTokenType("forceload");
  IElementType FREEZE_TOKEN = new McFunctionTokenType("freeze");
  IElementType FROM_TOKEN = new McFunctionTokenType("from");
  IElementType FUNCTION_TOKEN = new McFunctionTokenType("function");
  IElementType GAMEMODE_TOKEN = new McFunctionTokenType("gamemode");
  IElementType GAMERULE_TOKEN = new McFunctionTokenType("gamerule");
  IElementType GET_TOKEN = new McFunctionTokenType("get");
  IElementType GIVE_TOKEN = new McFunctionTokenType("give");
  IElementType GRANT_TOKEN = new McFunctionTokenType("grant");
  IElementType GTE_TOKEN = new McFunctionTokenType(">=");
  IElementType GT_TOKEN = new McFunctionTokenType(">");
  IElementType HELP_TOKEN = new McFunctionTokenType("help");
  IElementType IF_TOKEN = new McFunctionTokenType("if");
  IElementType IN_TOKEN = new McFunctionTokenType("in");
  IElementType ITEMS_TOKEN = new McFunctionTokenType("items");
  IElementType ITEM_TOKEN = new McFunctionTokenType("item");
  IElementType JFR_TOKEN = new McFunctionTokenType("jfr");
  IElementType JOIN_TOKEN = new McFunctionTokenType("join");
  IElementType KICK_TOKEN = new McFunctionTokenType("kick");
  IElementType KILL_TOKEN = new McFunctionTokenType("kill");
  IElementType LBRACE = new McFunctionTokenType("{");
  IElementType LBRACK = new McFunctionTokenType("[");
  IElementType LEAVE_TOKEN = new McFunctionTokenType("leave");
  IElementType LIST_TOKEN = new McFunctionTokenType("list");
  IElementType LOCATE_TOKEN = new McFunctionTokenType("locate");
  IElementType LOOT_TOKEN = new McFunctionTokenType("loot");
  IElementType LTE_TOKEN = new McFunctionTokenType("<=");
  IElementType LT_TOKEN = new McFunctionTokenType("<");
  IElementType MACRO_LINE_START = new McFunctionTokenType("MACRO_LINE_START");
  IElementType MACRO_TOKEN = new McFunctionTokenType("MACRO_TOKEN");
  IElementType MACRO_VAR_TOKEN = new McFunctionTokenType("MACRO_VAR_TOKEN");
  IElementType MASTER_TOKEN = new McFunctionTokenType("master");
  IElementType MATCHES_TOKEN = new McFunctionTokenType("matches");
  IElementType MERGE_TOKEN = new McFunctionTokenType("merge");
  IElementType ME_TOKEN = new McFunctionTokenType("me");
  IElementType MODIFIER_TOKEN = new McFunctionTokenType("modifier");
  IElementType MODIFY_TOKEN = new McFunctionTokenType("modify");
  IElementType MOUNT_TOKEN = new McFunctionTokenType("mount");
  IElementType MSG_TOKEN = new McFunctionTokenType("msg");
  IElementType MUSIC_TOKEN = new McFunctionTokenType("music");
  IElementType OBJECTIVES_TOKEN = new McFunctionTokenType("objectives");
  IElementType ONLY_TOKEN = new McFunctionTokenType("only");
  IElementType ON_TOKEN = new McFunctionTokenType("on");
  IElementType OP_TOKEN = new McFunctionTokenType("op");
  IElementType PARDON_TOKEN = new McFunctionTokenType("pardon");
  IElementType PARTICLE_TOKEN = new McFunctionTokenType("particle");
  IElementType PERF_TOKEN = new McFunctionTokenType("perf");
  IElementType PLACE_TOKEN = new McFunctionTokenType("place");
  IElementType PLAYERS_TOKEN = new McFunctionTokenType("players");
  IElementType PLAYSOUND_TOKEN = new McFunctionTokenType("playsound");
  IElementType POSITION_TOKEN = new McFunctionTokenType("position");
  IElementType QUERY_TOKEN = new McFunctionTokenType("query");
  IElementType RATE_TOKEN = new McFunctionTokenType("rate");
  IElementType RBRACE = new McFunctionTokenType("}");
  IElementType RBRACK = new McFunctionTokenType("]");
  IElementType RECIPE_TOKEN = new McFunctionTokenType("recipe");
  IElementType RELOAD_TOKEN = new McFunctionTokenType("reload");
  IElementType REMOVE_TOKEN = new McFunctionTokenType("remove");
  IElementType REPLACE_TOKEN = new McFunctionTokenType("replace");
  IElementType RESULT_TOKEN = new McFunctionTokenType("result");
  IElementType RETURN_TOKEN = new McFunctionTokenType("return");
  IElementType REVOKE_TOKEN = new McFunctionTokenType("revoke");
  IElementType RIDE_TOKEN = new McFunctionTokenType("ride");
  IElementType ROTATED_TOKEN = new McFunctionTokenType("rotated");
  IElementType RUN_TOKEN = new McFunctionTokenType("run");
  IElementType SAY_TOKEN = new McFunctionTokenType("say");
  IElementType SCHEDULE_TOKEN = new McFunctionTokenType("schedule");
  IElementType SCOREBOARD_TOKEN = new McFunctionTokenType("scoreboard");
  IElementType SCORE_TOKEN = new McFunctionTokenType("score");
  IElementType SEED_TOKEN = new McFunctionTokenType("seed");
  IElementType SELECTOR_A = new McFunctionTokenType("@a");
  IElementType SELECTOR_E = new McFunctionTokenType("@e");
  IElementType SELECTOR_P = new McFunctionTokenType("@p");
  IElementType SELECTOR_R = new McFunctionTokenType("@r");
  IElementType SELECTOR_S = new McFunctionTokenType("@s");
  IElementType SETBLOCK_TOKEN = new McFunctionTokenType("setblock");
  IElementType SETDISPLAY_TOKEN = new McFunctionTokenType("setdisplay");
  IElementType SETIDLETIMEOUT_TOKEN = new McFunctionTokenType("setidletimeout");
  IElementType SETWORLDSPAWN_TOKEN = new McFunctionTokenType("setworldspawn");
  IElementType SET_TOKEN = new McFunctionTokenType("set");
  IElementType SPAWNPOINT_TOKEN = new McFunctionTokenType("spawnpoint");
  IElementType SPECTATE_TOKEN = new McFunctionTokenType("spectate");
  IElementType SPREADPLAYERS_TOKEN = new McFunctionTokenType("spreadplayers");
  IElementType STEP_TOKEN = new McFunctionTokenType("step");
  IElementType STOPSOUND_TOKEN = new McFunctionTokenType("stopsound");
  IElementType STOP_TOKEN = new McFunctionTokenType("stop");
  IElementType STORAGE_TOKEN = new McFunctionTokenType("storage");
  IElementType STORE_TOKEN = new McFunctionTokenType("store");
  IElementType STRING_TOKEN = new McFunctionTokenType("STRING_TOKEN");
  IElementType SUBTITLE_TOKEN = new McFunctionTokenType("subtitle");
  IElementType SUCCESS_TOKEN = new McFunctionTokenType("success");
  IElementType SUMMON_TOKEN = new McFunctionTokenType("summon");
  IElementType TAG_TOKEN = new McFunctionTokenType("tag");
  IElementType TAKE_TOKEN = new McFunctionTokenType("take");
  IElementType TEAMMSG_TOKEN = new McFunctionTokenType("teammsg");
  IElementType TEAM_TOKEN = new McFunctionTokenType("team");
  IElementType TELEPORT_TOKEN = new McFunctionTokenType("teleport");
  IElementType TELLRAW_TOKEN = new McFunctionTokenType("tellraw");
  IElementType TELL_TOKEN = new McFunctionTokenType("tell");
  IElementType TEXT_TOKEN = new McFunctionTokenType("text");
  IElementType TICK_TOKEN = new McFunctionTokenType("tick");
  IElementType TIMES_TOKEN = new McFunctionTokenType("times");
  IElementType TIME_TOKEN = new McFunctionTokenType("time");
  IElementType TITLE_TOKEN = new McFunctionTokenType("title");
  IElementType TM_TOKEN = new McFunctionTokenType("tm");
  IElementType TP_TOKEN = new McFunctionTokenType("tp");
  IElementType TRIGGER_TOKEN = new McFunctionTokenType("trigger");
  IElementType UNFREEZE_TOKEN = new McFunctionTokenType("unfreeze");
  IElementType UNLESS_TOKEN = new McFunctionTokenType("unless");
  IElementType VALUE_TOKEN = new McFunctionTokenType("value");
  IElementType WARNING_TOKEN = new McFunctionTokenType("warning");
  IElementType WEAPON_MAINHAND_TOKEN = new McFunctionTokenType("weapon.mainhand");
  IElementType WEAPON_OFFHAND_TOKEN = new McFunctionTokenType("weapon.offhand");
  IElementType WEATHER_TOKEN = new McFunctionTokenType("weather");
  IElementType WHITELIST_TOKEN = new McFunctionTokenType("whitelist");
  IElementType WITH_TOKEN = new McFunctionTokenType("with");
  IElementType WORLDBORDER_TOKEN = new McFunctionTokenType("worldborder");
  IElementType XP_TOKEN = new McFunctionTokenType("xp");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new McFunctionArgumentImpl(node);
      }
      else if (type == ATTRIBUTE_COMMAND) {
        return new McFunctionAttributeCommandImpl(node);
      }
      else if (type == CLEAR_COMMAND) {
        return new McFunctionClearCommandImpl(node);
      }
      else if (type == COMMAND) {
        return new McFunctionCommandImpl(node);
      }
      else if (type == COMMAND_LINE) {
        return new McFunctionCommandLineImpl(node);
      }
      else if (type == COMPONENT) {
        return new McFunctionComponentImpl(node);
      }
      else if (type == COMPONENT_LIST) {
        return new McFunctionComponentListImpl(node);
      }
      else if (type == COMPONENT_VALUE) {
        return new McFunctionComponentValueImpl(node);
      }
      else if (type == COORDINATE) {
        return new McFunctionCoordinateImpl(node);
      }
      else if (type == COORDINATE_OR_NUMERIC) {
        return new McFunctionCoordinateOrNumericImpl(node);
      }
      else if (type == DAMAGE_COMMAND) {
        return new McFunctionDamageCommandImpl(node);
      }
      else if (type == DATA_COMMAND) {
        return new McFunctionDataCommandImpl(node);
      }
      else if (type == EXECUTE_ALIGN_CLAUSE) {
        return new McFunctionExecuteAlignClauseImpl(node);
      }
      else if (type == EXECUTE_ANCHORED_CLAUSE) {
        return new McFunctionExecuteAnchoredClauseImpl(node);
      }
      else if (type == EXECUTE_AS_CLAUSE) {
        return new McFunctionExecuteAsClauseImpl(node);
      }
      else if (type == EXECUTE_AT_CLAUSE) {
        return new McFunctionExecuteAtClauseImpl(node);
      }
      else if (type == EXECUTE_COMMAND) {
        return new McFunctionExecuteCommandImpl(node);
      }
      else if (type == EXECUTE_FACING_CLAUSE) {
        return new McFunctionExecuteFacingClauseImpl(node);
      }
      else if (type == EXECUTE_GENERIC_MODIFIER) {
        return new McFunctionExecuteGenericModifierImpl(node);
      }
      else if (type == EXECUTE_IF_CLAUSE) {
        return new McFunctionExecuteIfClauseImpl(node);
      }
      else if (type == EXECUTE_IN_CLAUSE) {
        return new McFunctionExecuteInClauseImpl(node);
      }
      else if (type == EXECUTE_MODIFIER_CLAUSE) {
        return new McFunctionExecuteModifierClauseImpl(node);
      }
      else if (type == EXECUTE_ON_CLAUSE) {
        return new McFunctionExecuteOnClauseImpl(node);
      }
      else if (type == EXECUTE_POSITION_CLAUSE) {
        return new McFunctionExecutePositionClauseImpl(node);
      }
      else if (type == EXECUTE_RIDE_CLAUSE) {
        return new McFunctionExecuteRideClauseImpl(node);
      }
      else if (type == EXECUTE_ROTATED_CLAUSE) {
        return new McFunctionExecuteRotatedClauseImpl(node);
      }
      else if (type == EXECUTE_STORE_CLAUSE) {
        return new McFunctionExecuteStoreClauseImpl(node);
      }
      else if (type == EXECUTE_SUMMON_CLAUSE) {
        return new McFunctionExecuteSummonClauseImpl(node);
      }
      else if (type == EXECUTE_UNLESS_CLAUSE) {
        return new McFunctionExecuteUnlessClauseImpl(node);
      }
      else if (type == GENERIC_COMMAND) {
        return new McFunctionGenericCommandImpl(node);
      }
      else if (type == GIVE_COMMAND) {
        return new McFunctionGiveCommandImpl(node);
      }
      else if (type == ITEM_COMMAND) {
        return new McFunctionItemCommandImpl(node);
      }
      else if (type == ITEM_ID) {
        return new McFunctionItemIdImpl(node);
      }
      else if (type == ITEM_SLOT) {
        return new McFunctionItemSlotImpl(node);
      }
      else if (type == ITEM_STACK) {
        return new McFunctionItemStackImpl(node);
      }
      else if (type == ITEM_TARGET) {
        return new McFunctionItemTargetImpl(node);
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
      else if (type == MACRO_LINE) {
        return new McFunctionMacroLineImpl(node);
      }
      else if (type == MACRO_VARIABLE) {
        return new McFunctionMacroVariableImpl(node);
      }
      else if (type == NAMESPACED_ID) {
        return new McFunctionNamespacedIdImpl(node);
      }
      else if (type == NBT_COMPOUND) {
        return new McFunctionNbtCompoundImpl(node);
      }
      else if (type == NBT_LIST) {
        return new McFunctionNbtListImpl(node);
      }
      else if (type == NBT_PATH) {
        return new McFunctionNbtPathImpl(node);
      }
      else if (type == NBT_PRIMITIVE) {
        return new McFunctionNbtPrimitiveImpl(node);
      }
      else if (type == NBT_PROPERTY) {
        return new McFunctionNbtPropertyImpl(node);
      }
      else if (type == NBT_VALUE) {
        return new McFunctionNbtValueImpl(node);
      }
      else if (type == PARTICLE_COMMAND) {
        return new McFunctionParticleCommandImpl(node);
      }
      else if (type == RETURN_COMMAND) {
        return new McFunctionReturnCommandImpl(node);
      }
      else if (type == RIDE_COMMAND) {
        return new McFunctionRideCommandImpl(node);
      }
      else if (type == SELECTOR) {
        return new McFunctionSelectorImpl(node);
      }
      else if (type == SELECTOR_ARGUMENT) {
        return new McFunctionSelectorArgumentImpl(node);
      }
      else if (type == SELECTOR_ARGUMENTS) {
        return new McFunctionSelectorArgumentsImpl(node);
      }
      else if (type == SELECTOR_ARG_KEY) {
        return new McFunctionSelectorArgKeyImpl(node);
      }
      else if (type == SLOT_ID) {
        return new McFunctionSlotIdImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
