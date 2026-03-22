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
CONTINUATION="\\"(\r\n|\r|\n)
COMMENT=#.*
STRING=\"([^\"\\\\]|\\.)*\"|'([^'\\\\]|\\.)*'
COMMAND=[a-z_][a-z0-9_]*
ARGUMENT=[^ \n\r\t\f#\"'\[\]{},\\\\]+

%state WAITING_ARGUMENT

%%
<YYINITIAL> {
  {CONTINUATION}   { /* Skip line continuation */ }
  {WHITE_SPACE}+   { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SPACE_TOKEN; }
  {CRLF}           { yybegin(YYINITIAL); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.CRLF_TOKEN; }
  {COMMENT}        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COMMENT_TOKEN; }
  {STRING}         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STRING_TOKEN; }
  "execute"        { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.EXECUTE_TOKEN; }
  "run"            { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RUN_TOKEN; }
  "as"             { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.AS_TOKEN; }
  "at"             { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.AT_TOKEN; }
  "store"          { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STORE_TOKEN; }
  "result"         { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RESULT_TOKEN; }
  "score"          { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SCORE_TOKEN; }
  "if"             { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.IF_TOKEN; }
  "entity"         { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ENTITY_TOKEN; }
  "["              { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LBRACK; }
  "]"              { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RBRACK; }
  "{"              { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LBRACE; }
  "}"              { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RBRACE; }
  ":"              { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COLON; }
  ","              { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COMMA; }
  {COMMAND}        { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COMMAND_TOKEN; }
  {ARGUMENT}       { yybegin(WAITING_ARGUMENT); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ARGUMENT_TOKEN; }
}

<WAITING_ARGUMENT> {
  {CONTINUATION}   { /* Skip line continuation */ }
  {WHITE_SPACE}+   { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SPACE_TOKEN; }
  {CRLF}           { yybegin(YYINITIAL); return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.CRLF_TOKEN; }
  {STRING}         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STRING_TOKEN; }
  "execute"        { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.EXECUTE_TOKEN; }
  "run"            { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RUN_TOKEN; }
  "as"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.AS_TOKEN; }
  "at"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.AT_TOKEN; }
  "store"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.STORE_TOKEN; }
  "result"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RESULT_TOKEN; }
  "score"          { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.SCORE_TOKEN; }
  "if"             { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.IF_TOKEN; }
  "entity"         { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ENTITY_TOKEN; }
  "["              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LBRACK; }
  "]"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RBRACK; }
  "{"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.LBRACE; }
  "}"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.RBRACE; }
  ":"              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COLON; }
  ","              { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.COMMA; }
  {ARGUMENT}       { return com.github.peco2282.mcdatapack.language.psi.McFunctionTypes.ARGUMENT_TOKEN; }
}

[^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
