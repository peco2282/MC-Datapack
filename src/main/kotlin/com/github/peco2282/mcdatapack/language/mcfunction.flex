package com.github.peco2282.mcdatapack.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.*;

%%

%{
  public _McFunctionLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _McFunctionLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

COMMENT_TOKEN=#.*
CRLF_TOKEN=\R
SPACE_TOKEN=[ \t\f]+
STRING_TOKEN=\"([^\"\\]|\\.)*\"|'([^'\\]|\\.)*'
CONTINUATION_TOKEN=\\([ \t\f]*\R)?
MACRO_TOKEN=\$[a-zA-Z_][a-zA-Z0-9_]*
MACRO_VAR_TOKEN=\$\([a-zA-Z_][a-zA-Z0-9_]*(\[[0-9]+\])?\)
MACRO_LINE_START=\$\$
COORD_TOKEN=[~\^]-?[0-9]*(\.[0-9]+)?
COMMAND_TOKEN=[a-zA-Z_][a-zA-Z0-9_]*
ARGUMENT_TOKEN=[^ \n\r\t\f#\"'\[\]{},=:\\]+

%%
<YYINITIAL> {
  {WHITE_SPACE}              { return WHITE_SPACE; }

  "advancement"              { return ADVANCEMENT_TOKEN; }
  "attribute"                { return ATTRIBUTE_TOKEN; }
  "execute"                  { return EXECUTE_TOKEN; }
  "bossbar"                  { return BOSSBAR_TOKEN; }
  "chance"                   { return CHANCE_TOKEN; }
  "clear"                    { return CLEAR_TOKEN; }
  "clone"                    { return CLONE_TOKEN; }
  "damage"                   { return DAMAGE_TOKEN; }
  "data"                     { return DATA_TOKEN; }
  "datapack"                 { return DATAPACK_TOKEN; }
  "debug"                    { return DEBUG_TOKEN; }
  "defaultgamemode"          { return DEFAULTGAMEMODE_TOKEN; }
  "difficulty"               { return DIFFICULTY_TOKEN; }
  "effect"                   { return EFFECT_TOKEN; }
  "enchant"                  { return ENCHANT_TOKEN; }
  "experience"               { return EXPERIENCE_TOKEN; }
  "fill"                     { return FILL_TOKEN; }
  "fillbiome"                { return FILLBIOME_TOKEN; }
  "forceload"                { return FORCELOAD_TOKEN; }
  "function"                 { return FUNCTION_TOKEN; }
  "gamemode"                 { return GAMEMODE_TOKEN; }
  "gamerule"                 { return GAMERULE_TOKEN; }
  "give"                     { return GIVE_TOKEN; }
  "help"                     { return HELP_TOKEN; }
  "item"                     { return ITEM_TOKEN; }
  "jfr"                      { return JFR_TOKEN; }
  "kick"                     { return KICK_TOKEN; }
  "kill"                     { return KILL_TOKEN; }
  "list"                     { return LIST_TOKEN; }
  "locate"                   { return LOCATE_TOKEN; }
  "loot"                     { return LOOT_TOKEN; }
  "me"                       { return ME_TOKEN; }
  "msg"                      { return MSG_TOKEN; }
  "op"                       { return OP_TOKEN; }
  "pardon"                   { return PARDON_TOKEN; }
  "particle"                 { return PARTICLE_TOKEN; }
  "perf"                     { return PERF_TOKEN; }
  "place"                    { return PLACE_TOKEN; }
  "playsound"                { return PLAYSOUND_TOKEN; }
  "recipe"                   { return RECIPE_TOKEN; }
  "reload"                   { return RELOAD_TOKEN; }
  "return"                   { return RETURN_TOKEN; }
  "ride"                     { return RIDE_TOKEN; }
  "say"                      { return SAY_TOKEN; }
  "schedule"                 { return SCHEDULE_TOKEN; }
  "scoreboard"               { return SCOREBOARD_TOKEN; }
  "seed"                     { return SEED_TOKEN; }
  "setblock"                 { return SETBLOCK_TOKEN; }
  "setidletimeout"           { return SETIDLETIMEOUT_TOKEN; }
  "setworldspawn"            { return SETWORLDSPAWN_TOKEN; }
  "spawnpoint"               { return SPAWNPOINT_TOKEN; }
  "spectate"                 { return SPECTATE_TOKEN; }
  "spreadplayers"            { return SPREADPLAYERS_TOKEN; }
  "stopsound"                { return STOPSOUND_TOKEN; }
  "summon"                   { return SUMMON_TOKEN; }
  "tag"                      { return TAG_TOKEN; }
  "team"                     { return TEAM_TOKEN; }
  "teammsg"                  { return TEAMMSG_TOKEN; }
  "teleport"                 { return TELEPORT_TOKEN; }
  "tell"                     { return TELL_TOKEN; }
  "tellraw"                  { return TELLRAW_TOKEN; }
  "tick"                     { return TICK_TOKEN; }
  "time"                     { return TIME_TOKEN; }
  "title"                    { return TITLE_TOKEN; }
  "tm"                       { return TM_TOKEN; }
  "tp"                       { return TP_TOKEN; }
  "trigger"                  { return TRIGGER_TOKEN; }
  "weather"                  { return WEATHER_TOKEN; }
  "whitelist"                { return WHITELIST_TOKEN; }
  "worldborder"              { return WORLDBORDER_TOKEN; }
  "xp"                       { return XP_TOKEN; }
  "if"                       { return IF_TOKEN; }
  "unless"                   { return UNLESS_TOKEN; }
  "while"                    { return WHILE_TOKEN; }
  "until"                    { return UNTIL_TOKEN; }
  "run"                      { return RUN_TOKEN; }
  "only"                     { return ONLY_TOKEN; }
  "entity"                   { return ENTITY_TOKEN; }
  "modify"                   { return MODIFY_TOKEN; }
  "storage"                  { return STORAGE_TOKEN; }
  "set"                      { return SET_TOKEN; }
  "from"                     { return FROM_TOKEN; }
  "add"                      { return ADD_TOKEN; }
  "players"                  { return PLAYERS_TOKEN; }
  "actionbar"                { return ACTIONBAR_TOKEN; }
  "matches"                  { return MATCHES_TOKEN; }
  "as"                       { return AS_TOKEN; }
  "at"                       { return AT_TOKEN; }
  "facing"                   { return FACING_TOKEN; }
  "rotated"                  { return ROTATED_TOKEN; }
  "position"                 { return POSITION_TOKEN; }
  "on"                       { return ON_TOKEN; }
  "in"                       { return IN_TOKEN; }
  "align"                    { return ALIGN_TOKEN; }
  "eyes"                     { return EYES_TOKEN; }
  "feet"                     { return FEET_TOKEN; }
  "block"                    { return BLOCK_TOKEN; }
  "items"                    { return ITEMS_TOKEN; }
  "store"                    { return STORE_TOKEN; }
  "result"                   { return RESULT_TOKEN; }
  "success"                  { return SUCCESS_TOKEN; }
  "score"                    { return SCORE_TOKEN; }
  "text"                     { return TEXT_TOKEN; }
  "value"                    { return VALUE_TOKEN; }
  "revoke"                   { return REVOKE_TOKEN; }
  "grant"                    { return GRANT_TOKEN; }
  "get"                      { return GET_TOKEN; }
  "merge"                    { return MERGE_TOKEN; }
  "remove"                   { return REMOVE_TOKEN; }
  "enable"                   { return ENABLE_TOKEN; }
  "disable"                  { return DISABLE_TOKEN; }
  "base"                     { return BASE_TOKEN; }
  "modifier"                 { return MODIFIER_TOKEN; }
  "query"                    { return QUERY_TOKEN; }
  "take"                     { return TAKE_TOKEN; }
  "objectives"               { return OBJECTIVES_TOKEN; }
  "setdisplay"               { return SETDISPLAY_TOKEN; }
  "empty"                    { return EMPTY_TOKEN; }
  "join"                     { return JOIN_TOKEN; }
  "leave"                    { return LEAVE_TOKEN; }
  "rate"                     { return RATE_TOKEN; }
  "freeze"                   { return FREEZE_TOKEN; }
  "step"                     { return STEP_TOKEN; }
  "stop"                     { return STOP_TOKEN; }
  "unfreeze"                 { return UNFREEZE_TOKEN; }
  "subtitle"                 { return SUBTITLE_TOKEN; }
  "times"                    { return TIMES_TOKEN; }
  "center"                   { return CENTER_TOKEN; }
  "warning"                  { return WARNING_TOKEN; }
  "master"                   { return MASTER_TOKEN; }
  "music"                    { return MUSIC_TOKEN; }
  "by"                       { return BY_TOKEN; }
  "mount"                    { return MOUNT_TOKEN; }
  "anchored"                 { return ANCHORED_TOKEN; }
  "weapon.mainhand"          { return WEAPON_MAINHAND_TOKEN; }
  "weapon.offhand"           { return WEAPON_OFFHAND_TOKEN; }
  "copy"                     { return COPY_TOKEN; }
  "replace"                  { return REPLACE_TOKEN; }
  "with"                     { return WITH_TOKEN; }
  "dismount"                 { return DISMOUNT_TOKEN; }
  "@s"                       { return SELECTOR_S; }
  "@a"                       { return SELECTOR_A; }
  "@p"                       { return SELECTOR_P; }
  "@e"                       { return SELECTOR_E; }
  "@r"                       { return SELECTOR_R; }
  "."                        { return DOT_TOKEN; }
  ">="                       { return GTE_TOKEN; }
  "<="                       { return LTE_TOKEN; }
  ">"                        { return GT_TOKEN; }
  "<"                        { return LT_TOKEN; }
  ".."                       { return DOTDOT_TOKEN; }
  "["                        { return LBRACK; }
  "]"                        { return RBRACK; }
  "{"                        { return LBRACE; }
  "}"                        { return RBRACE; }
  ":"                        { return COLON; }
  "="                        { return EQUALS; }
  ","                        { return COMMA; }

  {COMMENT_TOKEN}            { return COMMENT_TOKEN; }
  {CRLF_TOKEN}               { return CRLF_TOKEN; }
  {SPACE_TOKEN}              { return SPACE_TOKEN; }
  {STRING_TOKEN}             { return STRING_TOKEN; }
  {CONTINUATION_TOKEN}       { return CONTINUATION_TOKEN; }
  {MACRO_TOKEN}              { return MACRO_TOKEN; }
  {MACRO_VAR_TOKEN}          { return MACRO_VAR_TOKEN; }
  {MACRO_LINE_START}         { return MACRO_LINE_START; }
  {COORD_TOKEN}              { return COORD_TOKEN; }
  {COMMAND_TOKEN}            { return COMMAND_TOKEN; }
  {ARGUMENT_TOKEN}           { return ARGUMENT_TOKEN; }

}

[^] { return BAD_CHARACTER; }
