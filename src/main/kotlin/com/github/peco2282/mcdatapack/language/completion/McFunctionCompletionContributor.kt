package com.github.peco2282.mcdatapack.language.completion

import com.github.peco2282.mcdatapack.language.highlighting.McFunctionIcons
import com.github.peco2282.mcdatapack.language.psi.McFunctionLanguage
import com.github.peco2282.mcdatapack.language.psi.McFunctionTypes
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.completion.PrioritizedLookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import javax.swing.Icon

class McFunctionCompletionContributor : CompletionContributor() {
  init {
    // 1. コマンド名およびサブコマンドの補完
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement().withLanguage(McFunctionLanguage.INSTANCE),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          result: CompletionResultSet
        ) {
          val position = parameters.position
          if (position.language !is McFunctionLanguage) return
          
          // 行頭
          if (isCommandPosition(position)) {
            COMMANDS.forEach {
              result.addElement(
                LookupElementBuilder.create(it)
                  .withBoldness(true)
                  .withIcon(getIconForCommand(it))
              )
            }
            return
          }

          // execute ... run の後の全コマンド補完
          if (isAfterRun(position)) {
            COMMANDS.forEach {
              result.addElement(
                LookupElementBuilder.create(it)
                  .withBoldness(true)
                  .withIcon(getIconForCommand(it))
              )
            }
            return
          }

          // 文脈に応じた補完
          val prevVisible = getPreviousVisibleElement(position) ?: return
          val prevText = prevVisible.text.lowercase()
          
          when (prevText) {
            "execute" -> {
              EXECUTE_SUBCOMMANDS.forEach {
                var priority = 0.0
                if (it == "as" || it == "at") priority = 100.0
                else if (it == "run") priority = -100.0
                
                result.addElement(
                  PrioritizedLookupElement.withPriority(
                    LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND),
                    priority
                  )
                )
              }
            }
            "if", "unless", "while", "until" -> {
              IF_UNLESS_SUBCOMMANDS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "as", "at", "facing", "rotated", "position" -> {
              if (prevText == "facing" || prevText == "rotated" || prevText == "position") {
                result.addElement(LookupElementBuilder.create("as").withIcon(McFunctionIcons.COMMAND))
                if (prevText == "facing") {
                  result.addElement(LookupElementBuilder.create("entity").withIcon(McFunctionIcons.COMMAND))
                }
              }
              // セレクター候補
              SELECTORS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
              }
            }
            "on" -> {
              ON_SUBCOMMANDS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "summon" -> {
              // 本来はエンティティIDのリストを出すべきだが、一旦主要なものか空
            }
            "give", "tp", "teleport", "kill", "damage", "effect", "enchant", "gamemode", "clear" -> {
              // ターゲットセレクター
              SELECTORS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
              }
            }
            "data" -> {
              listOf("get", "merge", "modify", "remove").forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "weather" -> {
              listOf("clear", "rain", "thunder").forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "difficulty" -> {
              listOf("peaceful", "easy", "normal", "hard").forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "gamerule" -> {
              GAME_RULES.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "block", "entity", "storage" -> {
              // 文脈により座標、セレクター、または名前空間ID
              if (prevText == "entity") {
                SELECTORS.forEach {
                  result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
                }
              }
            }
            "function" -> {
              addFunctionReferences(parameters, result)
            }
          }
        }
      }
    )

    // 2. ターゲットセレクターの引数 '[' または ',' の後の補完
    extend(
      CompletionType.BASIC,
      PlatformPatterns.or(
        PlatformPatterns.psiElement().afterLeaf(PlatformPatterns.psiElement(McFunctionTypes.LBRACK)),
        PlatformPatterns.psiElement().afterLeaf(PlatformPatterns.psiElement(McFunctionTypes.COMMA))
          .withParent(PlatformPatterns.psiElement(McFunctionTypes.SELECTOR_ARGUMENTS))
      ),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          result: CompletionResultSet
        ) {
          SELECTOR_ARGUMENTS.forEach {
            result.addElement(LookupElementBuilder.create(it).withTailText("="))
          }
        }
      }
    )

    // 3. セレクター引数の値の補完 (gamemode=, sort=, type=)
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement().afterLeaf(PlatformPatterns.psiElement(McFunctionTypes.EQUALS))
        .withParent(PlatformPatterns.psiElement(McFunctionTypes.SELECTOR_ARGUMENT)),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          result: CompletionResultSet
        ) {
          val position = parameters.position
          val prev = getPreviousVisibleElement(position) ?: return
          val key = prev.prevSibling?.text ?: return // key= の key 部分

          when (key) {
            "gamemode" -> listOf("creative", "survival", "adventure", "spectator").forEach {
              result.addElement(LookupElementBuilder.create(it))
            }
            "sort" -> listOf("nearest", "furthest", "random", "arbitrary").forEach {
              result.addElement(LookupElementBuilder.create(it))
            }
            "type" -> ENTITY_IDS.forEach {
              result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
            }
            "limit" -> result.addElement(LookupElementBuilder.create("1"))
          }
        }
      }
    )

    // 4. scoreboard objectives add <name> <criteria> の criteria 補完
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement().withLanguage(McFunctionLanguage.INSTANCE),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          result: CompletionResultSet
        ) {
          val position = parameters.position
          val prev = getPreviousVisibleElement(position) ?: return
          
          // スコアボードの criteria
          // scoreboard objectives add <name> <criteria>
          val prev2 = getPreviousVisibleElement(prev) ?: return
          val prev3 = getPreviousVisibleElement(prev2) ?: return
          
          if (prevText(prev2) == "add" && prevText(prev3) == "objectives") {
              CRITERIA.forEach {
                  result.addElement(LookupElementBuilder.create(it))
              }
          }
        }
      }
    )

    // 5. 動的リソース (scoreboard objectives, tags, storage) の補完
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement().withLanguage(McFunctionLanguage.INSTANCE),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          result: CompletionResultSet
        ) {
          val position = parameters.position
          val prev = getPreviousVisibleElement(position) ?: return
          val text = prev.text.lowercase()

          when (text) {
            "score", "objectives" -> {
              // プロジェクト内の全 .mcfunction からスコアボード名を抽出（簡易版）
              addScoreboardObjectives(parameters, result)
            }
            "tag" -> {
              addEntityTags(parameters, result)
            }
            "storage" -> {
              addStorageNames(parameters, result)
            }
          }
        }
      }
    )
  }

  private fun prevText(element: PsiElement): String = element.text.lowercase()

  private fun addScoreboardObjectives(parameters: CompletionParameters, result: CompletionResultSet) {
    val project = parameters.originalFile.project
    val files = FilenameIndex.getAllFilesByExt(project, "mcfunction", GlobalSearchScope.allScope(project))
    val objectives = mutableSetOf<String>()
    
    for (file in files) {
      val psiFile = PsiManager.getInstance(project).findFile(file) ?: continue
      val commands = PsiTreeUtil.findChildrenOfType(psiFile, com.github.peco2282.mcdatapack.language.psi.McFunctionCommandLine::class.java)
      for (cmdLine in commands) {
          val text = cmdLine.text
          // 簡易パース: scoreboard objectives add <name>
          if (text.contains("scoreboard objectives add")) {
              val parts = text.split(Regex("\\s+"))
              val addIndex = parts.indexOf("add")
              if (addIndex != -1 && parts.size > addIndex + 1) {
                  objectives.add(parts[addIndex + 1])
              }
          }
      }
    }
    objectives.forEach {
      result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SCOREBOARD))
    }
  }

  private fun addEntityTags(parameters: CompletionParameters, result: CompletionResultSet) {
    val project = parameters.originalFile.project
    val files = FilenameIndex.getAllFilesByExt(project, "mcfunction", GlobalSearchScope.allScope(project))
    val tags = mutableSetOf<String>()

    for (file in files) {
      val psiFile = PsiManager.getInstance(project).findFile(file) ?: continue
      val text = psiFile.text
      // tag <selector> add <name>
      val regex = Regex("tag\\s+@\\w+(\\[.*])?\\s+add\\s+(\\w+)")
      regex.findAll(text).forEach {
        tags.add(it.groupValues[2])
      }
    }
    tags.forEach {
      result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.TAG))
    }
  }

  private fun addStorageNames(parameters: CompletionParameters, result: CompletionResultSet) {
      // storage 名は namespaced_id なので、addFunctionReferences と同様のロジックでプロジェクト内の resource 等から取得するのが望ましいが、
      // ここでは mcfunction 内で使われているものを収集する
      val project = parameters.originalFile.project
      val files = FilenameIndex.getAllFilesByExt(project, "mcfunction", GlobalSearchScope.allScope(project))
      val storages = mutableSetOf<String>()

      for (file in files) {
          val psiFile = PsiManager.getInstance(project).findFile(file) ?: continue
          val text = psiFile.text
          // data ... storage <name>
          val regex = Regex("storage\\s+([a-z0-9_.-]+:[a-z0-9_./-]+|[a-z0-9_./-]+)")
          regex.findAll(text).forEach {
              storages.add(it.groupValues[1])
          }
      }
      storages.forEach {
          result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.NBT))
      }
  }

  private fun getPreviousVisibleElement(position: PsiElement): PsiElement? {
    var prev = position.prevSibling
    if (prev == null) {
      var parent = position.parent
      while (parent != null && prev == null) {
        prev = parent.prevSibling
        parent = parent.parent
      }
    }
    
    while (prev != null && (prev.node.elementType == com.intellij.psi.TokenType.WHITE_SPACE || prev.node.elementType == McFunctionTypes.CONTINUATION_TOKEN)) {
      prev = prev.prevSibling
    }
    return prev
  }

  private fun isAfterRun(element: PsiElement): Boolean {
    // 簡易的な判定: 前のトークンが "run" か、または execute コマンドの文脈で run の後にいるか
    val prev = getPreviousVisibleElement(element) ?: return false
    if (prev.text.lowercase() == "run") return true
    
    // PSI構造による厳密な判定
    var parent = element.parent
    while (parent != null) {
      if (parent is com.github.peco2282.mcdatapack.language.psi.McFunctionExecuteCommand) {
        val runToken = parent.node.findChildByType(McFunctionTypes.RUN_TOKEN)
        if (runToken != null && element.textOffset > runToken.startOffset) {
          return true
        }
      }
      parent = parent.parent
    }
    return false
  }

  private fun addFunctionReferences(parameters: CompletionParameters, result: CompletionResultSet) {
    val project = parameters.originalFile.project
    val files = FilenameIndex.getAllFilesByExt(project, "mcfunction", GlobalSearchScope.allScope(project))
    
    for (virtualFile in files) {
      val path = virtualFile.path
      // data/<namespace>/functions/<path>.mcfunction から namespace:path を作成
      val dataIndex = path.replace("\\", "/").indexOf("/data/")
      if (dataIndex != -1) {
        val relativePath = path.substring(dataIndex + 6).replace("\\", "/")
        val parts = relativePath.split("/")
        if (parts.size >= 3 && parts[1] == "functions") {
          val namespace = parts[0]
          val functionPath = parts.drop(2).joinToString("/").removeSuffix(".mcfunction")
          result.addElement(
            LookupElementBuilder.create("$namespace:$functionPath")
              .withIcon(McFunctionIcons.FUNCTION)
          )
          if (namespace == "minecraft") {
            result.addElement(
              LookupElementBuilder.create(functionPath)
                .withIcon(McFunctionIcons.FUNCTION)
            )
          }
        }
      }
    }
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
    // position が McFunctionCommand の中にあるか、または親が McFunctionCommandLine の最初の子（の空白以外）であるか
    var element = position
    while (element != null && element !is com.intellij.psi.PsiFile) {
        val prev = element.prevSibling
        if (prev != null) {
            // 前に何か（空白以外）があれば、それは行頭ではない
            if (prev.node.elementType != com.intellij.psi.TokenType.WHITE_SPACE && prev.node.elementType != McFunctionTypes.CONTINUATION_TOKEN) {
                // ただし run の直後なら OK
                if (prev.node.elementType == McFunctionTypes.RUN_TOKEN) return true
                
                // さらに遡る（run の後に空白がある場合など）
                var p = prev
                while (p != null && (p.node.elementType == com.intellij.psi.TokenType.WHITE_SPACE || p.node.elementType == McFunctionTypes.CONTINUATION_TOKEN)) {
                    p = p.prevSibling
                }
                if (p != null && p.node.elementType == McFunctionTypes.RUN_TOKEN) return true
                
                return false
            }
        } else {
            // 前に兄弟がいない場合、親へ
            element = element.parent
            continue
        }
        // 前が空白ならさらに前を見る
        var p = prev
        while (p != null && (p.node.elementType == com.intellij.psi.TokenType.WHITE_SPACE || p.node.elementType == McFunctionTypes.CONTINUATION_TOKEN)) {
            p = p.prevSibling
        }
        if (p == null) return true // 行頭
        if (p.node.elementType == McFunctionTypes.CRLF_TOKEN) return true // 行頭
        if (p.node.elementType == McFunctionTypes.RUN_TOKEN) return true
        return false
    }
    return true
  }

  override fun beforeCompletion(context: CompletionInitializationContext) {
    // DUMMY_IDENTIFIER_TRIMMED を使うことで、入力中のトークンが補完候補に干渉するのを防ぐ
    context.dummyIdentifier = CompletionInitializationContext.DUMMY_IDENTIFIER_TRIMMED
  }
}

private val EXECUTE_SUBCOMMANDS = listOf(
  "as", "at", "anchored", "facing", "in", "on", "positioned", "rotated", "store", "summon", "if", "unless", "while", "until", "run"
)

private val IF_UNLESS_SUBCOMMANDS = listOf(
  "block", "blocks", "data", "dimension", "entity", "function", "items", "loaded", "predicate", "score", "storage"
)

private val ON_SUBCOMMANDS = listOf(
  "attacker", "controller", "leasher", "origin", "owner", "passengers", "target", "vehicle"
)

private val SELECTORS = listOf("@a", "@e", "@p", "@r", "@s")

private val SELECTOR_ARGUMENTS = listOf(
  "advancements", "distance", "dx", "dy", "dz", "gamemode", "level", "limit", "name", "nbt", "predicate", "scores", "sort", "tag", "team", "type", "x", "x_rotation", "y", "y_rotation", "z"
)

private val CRITERIA = listOf(
    "dummy", "trigger", "deathCount", "playerKillCount", "totalKillCount", "health", "food", "air", "armor", "level", "xp"
)

private val ENTITY_IDS = listOf(
    "minecraft:player", "minecraft:zombie", "minecraft:skeleton", "minecraft:creeper", "minecraft:spider", "minecraft:enderman"
)

private val GAME_RULES = listOf(
  "announceAdvancements", "commandBlockOutput", "disableElytraMovementCheck", "disableRaids", "doDaylightCycle",
  "doEntityDrops", "doFireTick", "doImmediateRespawn", "doInsomnia", "doLimitedCrafting", "doMobLoot",
  "doMobSpawning", "doPatrolSpawning", "doTileDrops", "doTraderSpawning", "doWeatherCycle", "drowningDamage",
  "fallDamage", "fireDamage", "forgiveDeadPlayers", "freezeDamage", "keepInventory", "logAdminCommands",
  "maxCommandChainLength", "maxEntityCramming", "mobGriefing", "naturalRegeneration", "playersSleepingPercentage",
  "randomTickSpeed", "reducedDebugInfo", "sendCommandFeedback", "showDeathMessages", "spawnRadius",
  "spectatorsGenerateChunks", "universalAnger"
)

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