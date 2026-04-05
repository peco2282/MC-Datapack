package com.github.peco2282.mcdatapack.language.highlighting

import com.github.peco2282.mcdatapack.language.psi.McFunctionLexerAdapter
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet

class McFunctionSyntaxHighlighter : SyntaxHighlighterBase() {
  companion object {
    // コメント (Structural Symbols: グレー) -> 実はコメントは別扱いが良いか。
    // ユーザー指定: Structural Symbols(グレー) - 文法を補助する記号
    val STRUCTURE = TextAttributesKey.createTextAttributesKey(
      "MCFUNCTION_STRUCTURE", DefaultLanguageHighlighterColors.OPERATION_SIGN
    )

    // Major Commands (オレンジ / 太字)
    val MAJOR_COMMAND = TextAttributesKey.createTextAttributesKey(
      "MC_MAJOR_COMMAND", DefaultLanguageHighlighterColors.KEYWORD
    )

    // Subcommands (別カラー)
    val SUB_COMMAND = TextAttributesKey.createTextAttributesKey(
      "MC_SUB_COMMAND", DefaultLanguageHighlighterColors.STATIC_METHOD
    )

    // Flow Keywords (ピンク・紫)
    val FLOW_KEYWORD = TextAttributesKey.createTextAttributesKey(
      "MC_FLOW_KEYWORD", DefaultLanguageHighlighterColors.CONSTANT
    )

    // Selectors (黄緑)
    val SELECTOR = TextAttributesKey.createTextAttributesKey(
      "MC_SELECTOR", DefaultLanguageHighlighterColors.METADATA
    )

    // Namespace (リソース識別子のコロンの前の部分)
    val NAMESPACE = TextAttributesKey.createTextAttributesKey(
      "MC_NAMESPACE", DefaultLanguageHighlighterColors.CLASS_NAME
    )
    val NAMESPACE_COLON = TextAttributesKey.createTextAttributesKey(
      "MC_NAMESPACE_COLON", DefaultLanguageHighlighterColors.OPERATION_SIGN
    )

    // Values / Literals (水色 / 緑 / 白)
    val STRING = TextAttributesKey.createTextAttributesKey(
      "MC_STRING", DefaultLanguageHighlighterColors.STRING
    )
    val ARGUMENT = TextAttributesKey.createTextAttributesKey(
      "MC_ARGUMENT", DefaultLanguageHighlighterColors.IDENTIFIER
    )

    // Macros & Constants (赤・ピンク)
    val MACRO = TextAttributesKey.createTextAttributesKey(
      "MC_MACRO", DefaultLanguageHighlighterColors.INSTANCE_FIELD
    )

    // JSON (NBT/Components) 色分け
    val JSON_KEY = TextAttributesKey.createTextAttributesKey(
      "MC_JSON_KEY", DefaultLanguageHighlighterColors.INSTANCE_FIELD
    )
    val JSON_STRING = TextAttributesKey.createTextAttributesKey(
      "MC_JSON_STRING", DefaultLanguageHighlighterColors.STRING
    )
    val JSON_NUMBER = TextAttributesKey.createTextAttributesKey(
      "MC_JSON_NUMBER", DefaultLanguageHighlighterColors.NUMBER
    )
    val JSON_BOOLEAN = TextAttributesKey.createTextAttributesKey(
      "MC_JSON_BOOLEAN", DefaultLanguageHighlighterColors.KEYWORD
    )

    val COMMENT = TextAttributesKey.createTextAttributesKey(
      "MC_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT
    )

    // Coordinates (水色: ~ ^ で始まる座標)
    val COORDINATE = TextAttributesKey.createTextAttributesKey(
      "MC_COORDINATE", DefaultLanguageHighlighterColors.NUMBER
    )

    val MAJOR_COMMAND_TOKENS = TokenSet.create(
      McFunctionTypes.COMMAND_TOKEN,
      McFunctionTypes.ADVANCEMENT_TOKEN,
      McFunctionTypes.ATTRIBUTE_TOKEN,
      McFunctionTypes.BOSSBAR_TOKEN,
      McFunctionTypes.CLEAR_TOKEN,
      McFunctionTypes.CLONE_TOKEN,
      McFunctionTypes.DAMAGE_TOKEN,
      McFunctionTypes.DATA_TOKEN,
      McFunctionTypes.DATAPACK_TOKEN,
      McFunctionTypes.DEBUG_TOKEN,
      McFunctionTypes.DEFAULTGAMEMODE_TOKEN,
      McFunctionTypes.DIFFICULTY_TOKEN,
      McFunctionTypes.EFFECT_TOKEN,
      McFunctionTypes.ENCHANT_TOKEN,
      McFunctionTypes.EXPERIENCE_TOKEN,
      McFunctionTypes.FILL_TOKEN,
      McFunctionTypes.FILLBIOME_TOKEN,
      McFunctionTypes.FORCELOAD_TOKEN,
      McFunctionTypes.FUNCTION_TOKEN,
      McFunctionTypes.GAMEMODE_TOKEN,
      McFunctionTypes.GAMERULE_TOKEN,
      McFunctionTypes.GIVE_TOKEN,
      McFunctionTypes.HELP_TOKEN,
      McFunctionTypes.ITEM_TOKEN,
      McFunctionTypes.JFR_TOKEN,
      McFunctionTypes.KICK_TOKEN,
      McFunctionTypes.KILL_TOKEN,
      McFunctionTypes.LIST_TOKEN,
      McFunctionTypes.LOCATE_TOKEN,
      McFunctionTypes.LOOT_TOKEN,
      McFunctionTypes.ME_TOKEN,
      McFunctionTypes.MSG_TOKEN,
      McFunctionTypes.PARTICLE_TOKEN,
      McFunctionTypes.PERF_TOKEN,
      McFunctionTypes.PLACE_TOKEN,
      McFunctionTypes.PLAYSOUND_TOKEN,
      McFunctionTypes.RECIPE_TOKEN,
      McFunctionTypes.RIDE_TOKEN,
      McFunctionTypes.SAY_TOKEN,
      McFunctionTypes.SCHEDULE_TOKEN,
      McFunctionTypes.SCOREBOARD_TOKEN,
      McFunctionTypes.SETBLOCK_TOKEN,
      McFunctionTypes.SETIDLETIMEOUT_TOKEN,
      McFunctionTypes.SETWORLDSPAWN_TOKEN,
      McFunctionTypes.SPAWNPOINT_TOKEN,
      McFunctionTypes.SPECTATE_TOKEN,
      McFunctionTypes.SPREADPLAYERS_TOKEN,
      McFunctionTypes.STOPSOUND_TOKEN,
      McFunctionTypes.SUMMON_TOKEN,
      McFunctionTypes.TAG_TOKEN,
      McFunctionTypes.TEAM_TOKEN,
      McFunctionTypes.TEAMMSG_TOKEN,
      McFunctionTypes.TELEPORT_TOKEN,
      McFunctionTypes.TELL_TOKEN,
      McFunctionTypes.TELLRAW_TOKEN,
      McFunctionTypes.TICK_TOKEN,
      McFunctionTypes.TIME_TOKEN,
      McFunctionTypes.TITLE_TOKEN,
      McFunctionTypes.TM_TOKEN,
      McFunctionTypes.TP_TOKEN,
      McFunctionTypes.TRIGGER_TOKEN,
      McFunctionTypes.WEATHER_TOKEN,
      McFunctionTypes.WHITELIST_TOKEN,
      McFunctionTypes.WORLDBORDER_TOKEN,
      McFunctionTypes.XP_TOKEN
    )

    val FLOW_KEYWORD_TOKENS = TokenSet.create(
      McFunctionTypes.EXECUTE_TOKEN,
      McFunctionTypes.RUN_TOKEN,
      McFunctionTypes.IF_TOKEN,
      McFunctionTypes.UNLESS_TOKEN,
      McFunctionTypes.RETURN_TOKEN,
      McFunctionTypes.STORE_TOKEN,
      McFunctionTypes.MATCHES_TOKEN
    )

    val SELECTOR_TOKENS = TokenSet.create(
      McFunctionTypes.SELECTOR_S,
      McFunctionTypes.SELECTOR_A,
      McFunctionTypes.SELECTOR_P,
      McFunctionTypes.SELECTOR_E,
      McFunctionTypes.SELECTOR_R
    )

    val SUB_COMMAND_TOKENS = TokenSet.create(
      McFunctionTypes.ONLY_TOKEN,
      McFunctionTypes.ENTITY_TOKEN,
      McFunctionTypes.MODIFY_TOKEN,
      McFunctionTypes.SET_TOKEN,
      McFunctionTypes.ADD_TOKEN,
      McFunctionTypes.PLAYERS_TOKEN,
      McFunctionTypes.ACTIONBAR_TOKEN,
      McFunctionTypes.AS_TOKEN,
      McFunctionTypes.AT_TOKEN,
      McFunctionTypes.ON_TOKEN,
      McFunctionTypes.IN_TOKEN,
      McFunctionTypes.BY_TOKEN,
      McFunctionTypes.MOUNT_TOKEN,
      McFunctionTypes.DISMOUNT_TOKEN,
      McFunctionTypes.ALIGN_TOKEN,
      McFunctionTypes.ANCHORED_TOKEN,
      McFunctionTypes.FACING_TOKEN,
      McFunctionTypes.POSITION_TOKEN,
      McFunctionTypes.ROTATED_TOKEN,
      McFunctionTypes.EYES_TOKEN,
      McFunctionTypes.FEET_TOKEN,
      McFunctionTypes.BLOCK_TOKEN,
      McFunctionTypes.ITEMS_TOKEN,
      McFunctionTypes.RESULT_TOKEN,
      McFunctionTypes.SCORE_TOKEN,
      McFunctionTypes.TEXT_TOKEN,
      McFunctionTypes.VALUE_TOKEN,
      McFunctionTypes.GET_TOKEN,
      McFunctionTypes.MERGE_TOKEN,
      McFunctionTypes.REMOVE_TOKEN,
      McFunctionTypes.ENABLE_TOKEN,
      McFunctionTypes.DISABLE_TOKEN,
      McFunctionTypes.BASE_TOKEN,
      McFunctionTypes.MODIFIER_TOKEN,
      McFunctionTypes.QUERY_TOKEN,
      McFunctionTypes.TAKE_TOKEN,
      McFunctionTypes.OBJECTIVES_TOKEN,
      McFunctionTypes.SETDISPLAY_TOKEN,
      McFunctionTypes.EMPTY_TOKEN,
      McFunctionTypes.JOIN_TOKEN,
      McFunctionTypes.LEAVE_TOKEN,
      McFunctionTypes.RATE_TOKEN,
      McFunctionTypes.FREEZE_TOKEN,
      McFunctionTypes.STEP_TOKEN,
      McFunctionTypes.STOP_TOKEN,
      McFunctionTypes.UNFREEZE_TOKEN,
      McFunctionTypes.SUBTITLE_TOKEN,
      McFunctionTypes.TIMES_TOKEN,
      McFunctionTypes.CENTER_TOKEN,
      McFunctionTypes.WARNING_TOKEN,
      McFunctionTypes.MASTER_TOKEN,
      McFunctionTypes.MUSIC_TOKEN,
      McFunctionTypes.FROM_TOKEN,
      McFunctionTypes.STORAGE_TOKEN,
      McFunctionTypes.REVOKE_TOKEN,
      McFunctionTypes.GRANT_TOKEN
    )

    val STRUCTURE_TOKENS = TokenSet.create(
      McFunctionTypes.LBRACK,
      McFunctionTypes.RBRACK,
      McFunctionTypes.LBRACE,
      McFunctionTypes.RBRACE,
      McFunctionTypes.COLON,
      McFunctionTypes.EQUALS,
      McFunctionTypes.COMMA,
      McFunctionTypes.DOT_TOKEN,
      McFunctionTypes.DOTDOT_TOKEN,
      McFunctionTypes.GTE_TOKEN,
      McFunctionTypes.LTE_TOKEN,
      McFunctionTypes.GT_TOKEN,
      McFunctionTypes.LT_TOKEN,
      McFunctionTypes.CONTINUATION_TOKEN
    )

    val MACRO_TOKENS = TokenSet.create(
      McFunctionTypes.MACRO_LINE_START,
      McFunctionTypes.MACRO_VAR_TOKEN,
      McFunctionTypes.MACRO_TOKEN
    )
  }

  override fun getHighlightingLexer(): Lexer = McFunctionLexerAdapter()

  override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
    return when (tokenType) {
      McFunctionTypes.COMMENT_TOKEN -> arrayOf(COMMENT)
      in MAJOR_COMMAND_TOKENS -> arrayOf(MAJOR_COMMAND)
      in FLOW_KEYWORD_TOKENS -> arrayOf(FLOW_KEYWORD)
      in SUB_COMMAND_TOKENS -> arrayOf(SUB_COMMAND)
      in SELECTOR_TOKENS -> arrayOf(SELECTOR)
      in STRUCTURE_TOKENS -> arrayOf(STRUCTURE)
      in MACRO_TOKENS -> arrayOf(MACRO)
      McFunctionTypes.STRING_TOKEN -> arrayOf(STRING)
      McFunctionTypes.COORD_TOKEN -> arrayOf(COORDINATE)
      McFunctionTypes.ARGUMENT_TOKEN -> arrayOf(ARGUMENT)
      McFunctionTypes.COLON -> arrayOf(NAMESPACE_COLON)
      McFunctionTypes.LBRACK, McFunctionTypes.RBRACK,
      McFunctionTypes.LBRACE, McFunctionTypes.RBRACE,
      McFunctionTypes.COMMA, McFunctionTypes.EQUALS,
      McFunctionTypes.DOT_TOKEN, McFunctionTypes.DOTDOT_TOKEN,
      McFunctionTypes.CONTINUATION_TOKEN -> arrayOf(STRUCTURE)
      else -> emptyArray()
    }
  }
}

class McFunctionSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
  override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
    McFunctionSyntaxHighlighter()
}
