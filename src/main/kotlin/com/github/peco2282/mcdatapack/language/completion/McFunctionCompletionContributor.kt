package com.github.peco2282.mcdatapack.language.completion

import com.github.peco2282.mcdatapack.language.highlighting.McFunctionIcons
import com.github.peco2282.mcdatapack.language.psi.McFunctionLanguage
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import javax.swing.Icon

class McFunctionCompletionContributor : CompletionContributor() {
  init {
    // 文脈に応じた補完
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement(),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          result: CompletionResultSet
        ) {
          val position = parameters.position
          if (position.language !is McFunctionLanguage) return

          // 行の先頭（または execute ... run の直後）かどうかを判定
          if (isCommandPosition(position)) {
            COMMANDS.forEach {
              result.addElement(
                LookupElementBuilder.create(it)
                  .withBoldness(true)
                  .withIcon(getIconForCommand(it))
              )
            }
          } else {
            KEYWORDS.forEach {
              result.addElement(
                LookupElementBuilder.create(it)
                  .withIcon(getIconForCommand(it))
              )
            }
          }
        }
      }
    )
  }

  private fun getIconForCommand(command: String): Icon {
    val text = command.lowercase()
    return when {
      text == "execute" -> McFunctionIcons.EXECUTE
      text == "function" -> McFunctionIcons.FUNCTION
      text == "give" -> McFunctionIcons.GIVE
      text.contains("effect") -> McFunctionIcons.EFFECT
      text.contains("scoreboard") -> McFunctionIcons.SCOREBOARD
      text.startsWith("@") -> McFunctionIcons.SELECTOR
      else -> McFunctionIcons.COMMAND
    }
  }

  private fun isCommandPosition(position: com.intellij.psi.PsiElement): Boolean {
    // 1. 行の最初（前にあるのが改行やスペースのみ）
    var prev = position.prevSibling
    while (prev != null && (prev.node.elementType == McFunctionTypes.WHITE_SPACE || prev.node.elementType == McFunctionTypes.CONTINUATION_TOKEN)) {
      prev = prev.prevSibling
    }
    if (prev == null || prev.node.elementType == McFunctionTypes.CRLF_TOKEN) return true

    // 2. run の直後
    if (prev.node.elementType == McFunctionTypes.RUN_TOKEN) return true

    // 3. McFunctionCommand の直後であればコマンドではない（引数のはず）
    // ただし、execute の modifiers の途中などは KEYWORDS を出したい
    return false
  }

  override fun beforeCompletion(context: CompletionInitializationContext) {
    // DUMMY_IDENTIFIER_TRIMMED を使うことで、入力中のトークンが補完候補に干渉するのを防ぐ
    context.dummyIdentifier = CompletionInitializationContext.DUMMY_IDENTIFIER_TRIMMED
  }
}

private val COMMANDS = listOf(
  "advancement", "attribute", "execute", "bossbar", "clear", "clone", "damage",
  "data", "datapack", "debug", "defaultgamemode", "difficulty", "effect", "enchant",
  "experience", "fill", "fillbiome", "forceload", "function", "gamemode", "gamerule",
  "give", "help", "item", "jfr", "kick", "kill", "list", "locate", "loot",
  "me", "msg", "particle", "perf", "place", "playsound", "recipe", "return",
  "ride", "say", "schedule", "scoreboard", "setblock", "setidletimeout", "setworldspawn",
  "spawnpoint", "spectate", "spreadplayers", "stopsound", "summon", "tag", "team",
  "teammsg", "teleport", "tell", "tellraw", "tick", "time", "title", "tm",
  "tp", "trigger", "weather", "whitelist", "worldborder", "xp", "if"
)

private val KEYWORDS = listOf(
  "run", "only", "entity", "modify", "storage", "set", "from", "add", "players",
  "actionbar", "matches", "as", "at", "anchored", "facing", "block", "items",
  "store", "result", "score", "text", "value", "eyes", "revoke", "grant",
  "get", "merge", "remove", "enable", "disable", "base", "modifier", "query",
  "take", "objectives", "setdisplay", "empty", "join", "leave", "rate",
  "freeze", "step", "stop", "unfreeze", "subtitle", "times", "center",
  "warning", "master", "music", "if", "unless"
)