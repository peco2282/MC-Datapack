package com.github.peco2282.mcdatapack.language

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext

class McFunctionCompletionContributor : CompletionContributor() {
  init {
    // すべての McFunction 内の要素に対して補完候補を出す
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement(),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          result: CompletionResultSet
        ) {
          // McFunctionLanguage の要素でない場合はスキップ
          if (parameters.position.language !is McFunctionLanguage) return

          COMMANDS.forEach {
            result.addElement(LookupElementBuilder.create(it))
          }
          KEYWORDS.forEach {
            result.addElement(LookupElementBuilder.create(it))
          }
        }
      }
    )
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