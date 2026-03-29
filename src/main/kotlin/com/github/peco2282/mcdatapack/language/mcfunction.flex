package com.github.peco2282.mcdatapack.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

%%

%unicode
%class McFunctionLexer
%implements FlexLexer
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \t\f]
CONTINUATION=\\(\r\n|\r|\n)
COMMENT=#.*
STRING=\"([^\"\\]|\\.)*\"|'([^'\\]|\\.)*'
SELECTOR=@[saperSAPER]
MACRO=\$[a-zA-Z_][a-zA-Z0-9_]*
COMMAND=[a-z_][a-z0-9_]*
ARGUMENT=[^ \n\r\t\f#\"'\[\]{},]+

%%

{CONTINUATION}     { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.CONTINUATION_TOKEN; }
{WHITE_SPACE}+     { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SPACE_TOKEN; }
{CRLF}             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.CRLF_TOKEN; }
{COMMENT}          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COMMENT_TOKEN; }
{STRING}           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STRING_TOKEN; }

// Layer 3: Target selectors (must come before COMMAND to avoid @s being split)
"@s"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SELECTOR_S; }
"@a"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SELECTOR_A; }
"@p"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SELECTOR_P; }
"@e"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SELECTOR_E; }
"@r"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SELECTOR_R; }

// Layer 4: Comparison operators (multi-char first)
">="               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.GTE_TOKEN; }
"<="               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LTE_TOKEN; }
".."               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DOTDOT_TOKEN; }
">"                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.GT_TOKEN; }
"<"                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LT_TOKEN; }
"."                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DOT_TOKEN; }

// Layer 4: Macro
{MACRO}            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.MACRO_TOKEN; }

// Major Commands: 1.21.1
"advancement"      { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ADVANCEMENT_TOKEN; }
"attribute"        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ATTRIBUTE_TOKEN; }
"execute"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.EXECUTE_TOKEN; }
"bossbar"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.BOSSBAR_TOKEN; }
"clear"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.CLEAR_TOKEN; }
"clone"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.CLONE_TOKEN; }
"damage"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DAMAGE_TOKEN; }
"data"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DATA_TOKEN; }
"datapack"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DATAPACK_TOKEN; }
"debug"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DEBUG_TOKEN; }
"defaultgamemode"  { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DEFAULTGAMEMODE_TOKEN; }
"difficulty"       { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DIFFICULTY_TOKEN; }
"effect"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.EFFECT_TOKEN; }
"enchant"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ENCHANT_TOKEN; }
"experience"       { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.EXPERIENCE_TOKEN; }
"fill"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.FILL_TOKEN; }
"fillbiome"        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.FILLBIOME_TOKEN; }
"forceload"        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.FORCELOAD_TOKEN; }
"function"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.FUNCTION_TOKEN; }
"gamemode"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.GAMEMODE_TOKEN; }
"gamerule"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.GAMERULE_TOKEN; }
"give"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.GIVE_TOKEN; }
"help"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.HELP_TOKEN; }
"item"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ITEM_TOKEN; }
"jfr"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.JFR_TOKEN; }
"kick"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.KICK_TOKEN; }
"kill"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.KILL_TOKEN; }
"list"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LIST_TOKEN; }
"locate"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LOCATE_TOKEN; }
"loot"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LOOT_TOKEN; }
"me"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ME_TOKEN; }
"msg"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.MSG_TOKEN; }
"particle"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.PARTICLE_TOKEN; }
"perf"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.PERF_TOKEN; }
"place"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.PLACE_TOKEN; }
"playsound"        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.PLAYSOUND_TOKEN; }
"recipe"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RECIPE_TOKEN; }
"return"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RETURN_TOKEN; }
"ride"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RIDE_TOKEN; }
"say"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SAY_TOKEN; }
"schedule"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SCHEDULE_TOKEN; }
"scoreboard"       { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SCOREBOARD_TOKEN; }
"setblock"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SETBLOCK_TOKEN; }
"setidletimeout"   { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SETIDLETIMEOUT_TOKEN; }
"setworldspawn"    { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SETWORLDSPAWN_TOKEN; }
"spawnpoint"       { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SPAWNPOINT_TOKEN; }
"spectate"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SPECTATE_TOKEN; }
"spreadplayers"    { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SPREADPLAYERS_TOKEN; }
"stopsound"        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STOPSOUND_TOKEN; }
"summon"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SUMMON_TOKEN; }
"tag"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TAG_TOKEN; }
"team"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TEAM_TOKEN; }
"teammsg"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TEAMMSG_TOKEN; }
"teleport"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TELEPORT_TOKEN; }
"tell"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TELL_TOKEN; }
"tellraw"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TELLRAW_TOKEN; }
"tick"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TICK_TOKEN; }
"time"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TIME_TOKEN; }
"title"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TITLE_TOKEN; }
"tm"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TM_TOKEN; }
"tp"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TP_TOKEN; }
"trigger"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TRIGGER_TOKEN; }
"weather"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.WEATHER_TOKEN; }
"whitelist"        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.WHITELIST_TOKEN; }
"worldborder"      { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.WORLDBORDER_TOKEN; }
"xp"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.XP_TOKEN; }

// Flow Keywords & Subcommands
"if"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.IF_TOKEN; }
"unless"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.UNLESS_TOKEN; }
"run"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RUN_TOKEN; }
"only"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ONLY_TOKEN; }
"entity"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ENTITY_TOKEN; }
"modify"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.MODIFY_TOKEN; }
"storage"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STORAGE_TOKEN; }
"set"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SET_TOKEN; }
"from"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.FROM_TOKEN; }
"add"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ADD_TOKEN; }
"players"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.PLAYERS_TOKEN; }
"actionbar"        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ACTIONBAR_TOKEN; }
"matches"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.MATCHES_TOKEN; }
"as"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.AS_TOKEN; }
"at"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.AT_TOKEN; }
"store"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STORE_TOKEN; }
"result"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RESULT_TOKEN; }
"block"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.BLOCK_TOKEN; }
"items"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ITEMS_TOKEN; }
"score"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SCORE_TOKEN; }
"revoke"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.REVOKE_TOKEN; }
"grant"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.GRANT_TOKEN; }
"get"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.GET_TOKEN; }
"merge"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.MERGE_TOKEN; }
"remove"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.REMOVE_TOKEN; }
"enable"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ENABLE_TOKEN; }
"disable"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DISABLE_TOKEN; }
"base"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.BASE_TOKEN; }
"modifier"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.MODIFIER_TOKEN; }
"query"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.QUERY_TOKEN; }
"take"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TAKE_TOKEN; }
"objectives"       { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.OBJECTIVES_TOKEN; }
"setdisplay"       { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SETDISPLAY_TOKEN; }
"empty"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.EMPTY_TOKEN; }
"join"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.JOIN_TOKEN; }
"leave"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LEAVE_TOKEN; }
"rate"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RATE_TOKEN; }
"freeze"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.FREEZE_TOKEN; }
"step"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STEP_TOKEN; }
"stop"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STOP_TOKEN; }
"unfreeze"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.UNFREEZE_TOKEN; }
"subtitle"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SUBTITLE_TOKEN; }
"times"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TIMES_TOKEN; }
"center"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.CENTER_TOKEN; }
"warning"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.WARNING_TOKEN; }

// Structural symbols
"["                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LBRACK; }
"]"                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RBRACK; }
"{"                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LBRACE; }
"}"                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RBRACE; }
":"                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COLON; }
","                { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COMMA; }

// Generic tokens (lowest priority)
{COMMAND}          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COMMAND_TOKEN; }
{ARGUMENT}         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ARGUMENT_TOKEN; }

[^]                { return com.intellij.psi.TokenType.BAD_CHARACTER; }
