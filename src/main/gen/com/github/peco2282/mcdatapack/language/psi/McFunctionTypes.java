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

  IElementType ACTIONBAR_TOKEN = new McFunctionTokenType("actionbar");
  IElementType ADD_TOKEN = new McFunctionTokenType("add");
  IElementType ADVANCEMENT_TOKEN = new McFunctionTokenType("advancement");
  IElementType ANCHORED_TOKEN = new McFunctionTokenType("anchored");
  IElementType ARGUMENT_TOKEN = new McFunctionTokenType("ARGUMENT_TOKEN");
  IElementType AS_TOKEN = new McFunctionTokenType("as");
  IElementType ATTRIBUTE_TOKEN = new McFunctionTokenType("attribute");
  IElementType AT_TOKEN = new McFunctionTokenType("at");
  IElementType BASE_TOKEN = new McFunctionTokenType("base");
  IElementType BLOCK_TOKEN = new McFunctionTokenType("block");
  IElementType BOSSBAR_TOKEN = new McFunctionTokenType("bossbar");
  IElementType CENTER_TOKEN = new McFunctionTokenType("center");
  IElementType CLEAR_TOKEN = new McFunctionTokenType("clear");
  IElementType CLONE_TOKEN = new McFunctionTokenType("clone");
  IElementType COLON = new McFunctionTokenType(":");
  IElementType COMMA = new McFunctionTokenType(",");
  IElementType COMMAND_TOKEN = new McFunctionTokenType("COMMAND_TOKEN");
  IElementType COMMENT_TOKEN = new McFunctionTokenType("COMMENT_TOKEN");
  IElementType CONTINUATION_TOKEN = new McFunctionTokenType("CONTINUATION_TOKEN");
  IElementType CRLF_TOKEN = new McFunctionTokenType("CRLF_TOKEN");
  IElementType DAMAGE_TOKEN = new McFunctionTokenType("damage");
  IElementType DATAPACK_TOKEN = new McFunctionTokenType("datapack");
  IElementType DATA_TOKEN = new McFunctionTokenType("data");
  IElementType DEBUG_TOKEN = new McFunctionTokenType("debug");
  IElementType DEFAULTGAMEMODE_TOKEN = new McFunctionTokenType("defaultgamemode");
  IElementType DIFFICULTY_TOKEN = new McFunctionTokenType("difficulty");
  IElementType DISABLE_TOKEN = new McFunctionTokenType("disable");
  IElementType DOTDOT_TOKEN = new McFunctionTokenType("..");
  IElementType DOT_TOKEN = new McFunctionTokenType(".");
  IElementType EFFECT_TOKEN = new McFunctionTokenType("effect");
  IElementType EMPTY_TOKEN = new McFunctionTokenType("empty");
  IElementType ENABLE_TOKEN = new McFunctionTokenType("enable");
  IElementType ENCHANT_TOKEN = new McFunctionTokenType("enchant");
  IElementType ENTITY_TOKEN = new McFunctionTokenType("entity");
  IElementType EXECUTE_TOKEN = new McFunctionTokenType("execute");
  IElementType EXPERIENCE_TOKEN = new McFunctionTokenType("experience");
  IElementType EYES_TOKEN = new McFunctionTokenType("eyes");
  IElementType FACING_TOKEN = new McFunctionTokenType("facing");
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
  IElementType MACRO_TOKEN = new McFunctionTokenType("MACRO_TOKEN");
  IElementType MASTER_TOKEN = new McFunctionTokenType("master");
  IElementType MATCHES_TOKEN = new McFunctionTokenType("matches");
  IElementType MERGE_TOKEN = new McFunctionTokenType("merge");
  IElementType ME_TOKEN = new McFunctionTokenType("me");
  IElementType MODIFIER_TOKEN = new McFunctionTokenType("modifier");
  IElementType MODIFY_TOKEN = new McFunctionTokenType("modify");
  IElementType MSG_TOKEN = new McFunctionTokenType("msg");
  IElementType MUSIC_TOKEN = new McFunctionTokenType("music");
  IElementType OBJECTIVES_TOKEN = new McFunctionTokenType("objectives");
  IElementType ONLY_TOKEN = new McFunctionTokenType("only");
  IElementType OP_TOKEN = new McFunctionTokenType("op");
  IElementType PARDON_TOKEN = new McFunctionTokenType("pardon");
  IElementType PARTICLE_TOKEN = new McFunctionTokenType("particle");
  IElementType PERF_TOKEN = new McFunctionTokenType("perf");
  IElementType PLACE_TOKEN = new McFunctionTokenType("place");
  IElementType PLAYERS_TOKEN = new McFunctionTokenType("players");
  IElementType PLAYSOUND_TOKEN = new McFunctionTokenType("playsound");
  IElementType QUERY_TOKEN = new McFunctionTokenType("query");
  IElementType RATE_TOKEN = new McFunctionTokenType("rate");
  IElementType RBRACE = new McFunctionTokenType("}");
  IElementType RBRACK = new McFunctionTokenType("]");
  IElementType RECIPE_TOKEN = new McFunctionTokenType("recipe");
  IElementType RELOAD_TOKEN = new McFunctionTokenType("reload");
  IElementType REMOVE_TOKEN = new McFunctionTokenType("remove");
  IElementType RESULT_TOKEN = new McFunctionTokenType("result");
  IElementType RETURN_TOKEN = new McFunctionTokenType("return");
  IElementType REVOKE_TOKEN = new McFunctionTokenType("revoke");
  IElementType RIDE_TOKEN = new McFunctionTokenType("ride");
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
  IElementType SPACE_TOKEN = new McFunctionTokenType("SPACE_TOKEN");
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
  IElementType WEATHER_TOKEN = new McFunctionTokenType("weather");
  IElementType WHITELIST_TOKEN = new McFunctionTokenType("whitelist");
  IElementType WHITE_SPACE = new McFunctionTokenType("WHITE_SPACE");
  IElementType WORLDBORDER_TOKEN = new McFunctionTokenType("worldborder");
  IElementType XP_TOKEN = new McFunctionTokenType("xp");

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
