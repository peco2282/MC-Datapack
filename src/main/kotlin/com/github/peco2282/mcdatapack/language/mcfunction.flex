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

// Major Commands: execute, advancement, data, tag, scoreboard, title, function, return, tp
"execute"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.EXECUTE_TOKEN; }
"advancement"      { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ADVANCEMENT_TOKEN; }
"data"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.DATA_TOKEN; }
"tag"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TAG_TOKEN; }
"scoreboard"       { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SCOREBOARD_TOKEN; }
"title"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TITLE_TOKEN; }
"function"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.FUNCTION_TOKEN; }
"return"           { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RETURN_TOKEN; }
"tp"               { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.TP_TOKEN; }
"schedule"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SCHEDULE_TOKEN; }

// Flow Keywords: if, unless, run, only, entity, modify, storage, set, from, add, players, actionbar, matches, as, at, store, result, block, items
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
