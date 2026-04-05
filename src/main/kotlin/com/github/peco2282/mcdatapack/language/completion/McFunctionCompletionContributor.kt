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
            McFunctionConstants.COMMANDS.forEach {
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
            McFunctionConstants.COMMANDS.forEach {
              result.addElement(
                LookupElementBuilder.create(it)
                  .withBoldness(true)
                  .withIcon(getIconForCommand(it))
              )
            }
            return
          }

          if (isInsideSelectorType(position)) {
              addIds(McFunctionConstants.ENTITY_IDS, result)
              // アイテムやブロックのタグも type= 内で使える場合があるが、ここでは主にエンティティ
              addEntityTags(parameters, result)
              return
          }

          // 文脈に応じた補完
          val prevVisible = getPreviousVisibleElement(position) ?: return
          val prevText = prevVisible.text.lowercase()
          
          when (prevText) {
            "run" -> {
                // execute ... run の直後は全コマンド
                McFunctionConstants.COMMANDS.forEach {
                    result.addElement(
                        LookupElementBuilder.create(it)
                            .withBoldness(true)
                            .withIcon(getIconForCommand(it))
                    )
                }
            }
            "execute" -> {
              McFunctionConstants.EXECUTE_SUBCOMMANDS.forEach {
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
              McFunctionConstants.IF_UNLESS_SUBCOMMANDS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
              if (prevText == "if" || prevText == "unless") {
                  result.addElement(LookupElementBuilder.create("function").withIcon(McFunctionIcons.COMMAND))
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
              McFunctionConstants.SELECTORS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
              }
            }
            "on" -> {
              McFunctionConstants.ON_SUBCOMMANDS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "rotate" -> {
                result.addElement(LookupElementBuilder.create("as").withIcon(McFunctionIcons.COMMAND))
                McFunctionConstants.SELECTORS.forEach {
                    result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
                }
            }
            "summon" -> {
                addIds(McFunctionConstants.ENTITY_IDS, result)
            }
            "setblock" -> {
                addIds(McFunctionConstants.BLOCK_IDS, result)
            }
            "fill" -> {
                addIds(McFunctionConstants.BLOCK_IDS, result)
            }
            "give", "tp", "teleport", "kill", "damage", "enchant", "clear" -> {
              // ターゲットセレクター
              McFunctionConstants.SELECTORS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
              }
            }
            "effect" -> {
              // effect give/clear <selector> ...
              listOf("give", "clear").forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "@a", "@e", "@p", "@r", "@s" -> {
                // セレクターの次に来る引数を、前のコマンドに基づいて判断する
                val p2 = getPreviousVisibleElement(prevVisible)
                val cmdText = p2?.text?.lowercase()
                when (cmdText) {
                    "give", "clear" -> {
                        addIds(McFunctionConstants.ITEM_IDS, result)
                    }
                    "effect" -> {
                        // effect give @p <effect_id> または effect clear @p <effect_id>
                        val p3 = getPreviousVisibleElement(p2!!)
                        if (p3?.text?.lowercase() == "give" || p3?.text?.lowercase() == "clear") {
                            addIds(McFunctionConstants.EFFECT_IDS, result)
                        }
                    }
                    "tp", "teleport" -> {
                        // 座標または別のセレクター
                        McFunctionConstants.SELECTORS.forEach {
                            result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
                        }
                    }
                }
            }
            "in" -> {
                // execute in <dimension>
                // 現状 dimension ID のリストはないが、namespaced_id として補完されるべき
                // とりあえず既知のディメンションがあれば追加する
                listOf("minecraft:overworld", "minecraft:the_nether", "minecraft:the_end").forEach {
                    result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
                    result.addElement(LookupElementBuilder.create(it.removePrefix("minecraft:")).withIcon(McFunctionIcons.COMMAND))
                }
            }
            "item" -> {
                // item replace entity <selector> ...
                // item replace block <pos> ...
                listOf("replace", "modify").forEach {
                    result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
                }
            }
            "replace" -> {
                val p2 = getPreviousVisibleElement(prevVisible)
                if (p2?.text?.lowercase() == "item") {
                    listOf("block", "entity").forEach {
                        result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
                    }
                }
            }
            "data" -> {
              listOf("get", "merge", "modify", "remove").forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "modify" -> {
                val p2 = getPreviousVisibleElement(prevVisible)
                if (p2?.text?.lowercase() == "data") {
                    listOf("block", "entity", "storage").forEach {
                        result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
                    }
                }
            }
            "place" -> {
                // place feature/template/structure ...
                listOf("feature", "template", "structure").forEach {
                    result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
                }
            }
            "locate" -> {
                // locate biome/poi/structure ...
                listOf("biome", "poi", "structure").forEach {
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
            "gamemode" -> {
              // gamemode コマンド直後はセレクター、サブコマンドとしての gamemode= は別ブランチで処理
              McFunctionConstants.SELECTORS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
              }
              listOf("survival", "creative", "adventure", "spectator").forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "gamerule" -> {
              McFunctionConstants.GAME_RULES.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "block", "entity", "storage" -> {
              // 文脈により座標、セレクター、または名前空間ID
              when (prevText) {
                "entity" -> {
                  McFunctionConstants.SELECTORS.forEach {
                    result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.SELECTOR))
                  }
                  // もしくは直接エンティティID
                  addIds(McFunctionConstants.ENTITY_IDS, result)
                }
                "storage" -> {
                  addStorageNames(parameters, result)
                }
                else -> {
                  // data get block <pos>
                  // 本来は座標だが、ここではIDも出せるようにするか？
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
          McFunctionConstants.SELECTOR_ARGUMENTS.forEach {
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
              result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
            }
            "sort" -> listOf("nearest", "furthest", "random", "arbitrary").forEach {
              result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
            }
            "type" -> {
              McFunctionConstants.ENTITY_IDS.forEach {
                result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
                result.addElement(LookupElementBuilder.create("!" + it).withIcon(McFunctionIcons.COMMAND))
              }
            }
            "limit" -> listOf("1", "2", "5", "10").forEach {
              result.addElement(LookupElementBuilder.create(it))
            }
            "team" -> {
              addTeamNames(parameters, result)
              result.addElement(LookupElementBuilder.create("!").withTypeText("チームなし"))
            }
            "tag" -> {
              addEntityTags(parameters, result)
              result.addElement(LookupElementBuilder.create("!").withTypeText("タグなし"))
            }
            "name" -> {
              // プレイヤー名は動的なので、否定形のヒントのみ
              result.addElement(LookupElementBuilder.create("!").withTypeText("名前の否定"))
            }
            "predicate" -> {
              addPredicateNames(parameters, result)
            }
            "scores" -> {
              result.addElement(LookupElementBuilder.create("{}").withTypeText("スコア条件"))
              addScoreboardObjectivesForSelector(parameters, result)
            }
            "advancements" -> {
              result.addElement(LookupElementBuilder.create("{}").withTypeText("進捗条件"))
            }
            "nbt" -> {
              result.addElement(LookupElementBuilder.create("{}").withTypeText("NBT条件"))
              result.addElement(LookupElementBuilder.create("!{}").withTypeText("NBT否定条件"))
            }
            "distance" -> listOf("..10", "5..10", "5..").forEach {
              result.addElement(LookupElementBuilder.create(it).withTypeText("距離範囲"))
            }
            "level" -> listOf("0..30", "..30", "30..").forEach {
              result.addElement(LookupElementBuilder.create(it).withTypeText("レベル範囲"))
            }
            "x_rotation" -> listOf("-90..90", "-90..0", "0..90").forEach {
              result.addElement(LookupElementBuilder.create(it).withTypeText("X回転範囲"))
            }
            "y_rotation" -> listOf("-180..180", "-90..90", "0..180").forEach {
              result.addElement(LookupElementBuilder.create(it).withTypeText("Y回転範囲"))
            }
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
              McFunctionConstants.CRITERIA.forEach {
                  result.addElement(LookupElementBuilder.create(it))
              }
          }
        }
      }
    )

    // 5. NBTデータの補完 (summon/data merge/give コマンドの {} 内)
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
          val fileText = position.containingFile?.text ?: return
          val offset = position.textOffset
          if (offset <= 0) return

          // カーソル位置より前のテキストで {} の中にいるか確認
          val prefix = fileText.substring(0, offset)
          val depth = prefix.count { it == '{' } - prefix.count { it == '}' }
          if (depth <= 0) return

          // 直前のコマンドを確認
          var lineStart = offset - 1
          while (lineStart >= 0 && prefix[lineStart] != '\n' && prefix[lineStart] != '\r') lineStart--
          lineStart++
          val linePrefix = prefix.substring(lineStart).trimStart()

          val nbtKeys: List<String> = when {
            linePrefix.startsWith("summon ") -> McFunctionNbtConstants.ENTITY_NBT_KEYS
            linePrefix.startsWith("give ") -> McFunctionNbtConstants.ITEM_NBT_KEYS
            linePrefix.contains("data merge entity") || linePrefix.contains("data merge block") || linePrefix.contains("data merge storage") -> McFunctionNbtConstants.GENERIC_NBT_KEYS
            linePrefix.startsWith("data ") -> McFunctionNbtConstants.GENERIC_NBT_KEYS
            else -> return
          }

          for (key in nbtKeys) {
            val typeHint = McFunctionNbtConstants.NBT_KEY_TYPES[key]
            val element = if (typeHint != null) {
              LookupElementBuilder.create(key)
                .withTypeText(typeHint)
                .withIcon(McFunctionIcons.NBT)
            } else {
              LookupElementBuilder.create(key)
                .withIcon(McFunctionIcons.NBT)
            }
            result.addElement(element)
          }
        }
      }
    )

    // 6. 動的リソース (scoreboard objectives, tags, storage) の補完
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
      val regex = Regex("tag\\s+\\S+\\s+add\\s+([a-zA-Z0-9_.-]+)")
      regex.findAll(text).forEach {
        tags.add(it.groupValues[1])
      }
    }
    tags.forEach {
      result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.TAG))
    }
  }

  private fun addTeamNames(parameters: CompletionParameters, result: CompletionResultSet) {
    val project = parameters.originalFile.project
    val files = FilenameIndex.getAllFilesByExt(project, "mcfunction", GlobalSearchScope.allScope(project))
    val teams = mutableSetOf<String>()
    for (file in files) {
      val psiFile = PsiManager.getInstance(project).findFile(file) ?: continue
      val text = psiFile.text
      val regex = Regex("team\\s+add\\s+([a-zA-Z0-9_.-]+)")
      regex.findAll(text).forEach { teams.add(it.groupValues[1]) }
    }
    teams.forEach {
      result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
    }
  }

  private fun addPredicateNames(parameters: CompletionParameters, result: CompletionResultSet) {
    val project = parameters.originalFile.project
    val files = FilenameIndex.getAllFilesByExt(project, "json", GlobalSearchScope.allScope(project))
    for (file in files) {
      val path = file.path.replace("\\", "/")
      if (!path.contains("/predicates/")) continue
      val dataIndex = path.indexOf("/data/")
      if (dataIndex == -1) continue
      val relativePath = path.substring(dataIndex + 6)
      val parts = relativePath.split("/")
      if (parts.size >= 3 && parts[1] == "predicates") {
        val namespace = parts[0]
        val predPath = parts.drop(2).joinToString("/").removeSuffix(".json")
        result.addElement(LookupElementBuilder.create("$namespace:$predPath").withIcon(McFunctionIcons.FUNCTION))
      }
    }
  }

  private fun addScoreboardObjectivesForSelector(parameters: CompletionParameters, result: CompletionResultSet) {
    val project = parameters.originalFile.project
    val files = FilenameIndex.getAllFilesByExt(project, "mcfunction", GlobalSearchScope.allScope(project))
    val objectives = mutableSetOf<String>()
    for (file in files) {
      val psiFile = PsiManager.getInstance(project).findFile(file) ?: continue
      val text = psiFile.text
      val regex = Regex("scoreboard\\s+objectives\\s+add\\s+([a-zA-Z0-9_.-]+)")
      regex.findAll(text).forEach { objectives.add(it.groupValues[1]) }
    }
    objectives.forEach {
      result.addElement(LookupElementBuilder.create("{$it=}").withTypeText("スコア条件"))
    }
  }

  private fun addStorageNames(parameters: CompletionParameters, result: CompletionResultSet) {
      val project = parameters.originalFile.project
      val files = FilenameIndex.getAllFilesByExt(project, "mcfunction", GlobalSearchScope.allScope(project))
      val storages = mutableSetOf("minecraft:default") // デフォルト

      for (file in files) {
          val psiFile = PsiManager.getInstance(project).findFile(file) ?: continue
          val text = psiFile.text
          // data ... storage <name>
          val regex = Regex("storage\\s+([a-zA-Z0-9_.-]+:[a-zA-Z0-9_./-]+|[a-zA-Z0-9_./-]+)")
          regex.findAll(text).forEach {
              storages.add(it.groupValues[1])
          }
      }
      storages.forEach {
          result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.NBT))
      }
  }

  private fun addIds(ids: List<String>, result: CompletionResultSet) {
    ids.forEach {
      val id = it.removePrefix("minecraft:")
      result.addElement(LookupElementBuilder.create(id).withIcon(McFunctionIcons.COMMAND))
      result.addElement(LookupElementBuilder.create(it).withIcon(McFunctionIcons.COMMAND))
    }
  }

  private fun getPreviousVisibleElement(position: PsiElement): PsiElement? {
    var prev = position.prevSibling
    var current = position
    while (prev == null && current.parent != null && current.parent !is com.intellij.psi.PsiFile) {
      current = current.parent
      prev = current.prevSibling
    }

    while (prev != null && (prev.node.elementType == com.intellij.psi.TokenType.WHITE_SPACE || prev.node.elementType == McFunctionTypes.CONTINUATION_TOKEN)) {
      if (prev.prevSibling != null) {
        prev = prev.prevSibling
      } else {
        var p = prev.parent
        prev = null
        while (p != null && p !is com.intellij.psi.PsiFile && prev == null) {
          prev = p.prevSibling
          p = p.parent
        }
      }
    }
    return prev
  }

  private fun isAfterRun(element: PsiElement): Boolean {
    val prev = getPreviousVisibleElement(element) ?: return false
    if (prev.text.lowercase() == "run") return true

    // PSI構造による判定: execute コマンドの文脈で run トークンが存在するか
    var parent = element.parent
    while (parent != null) {
      if (parent.toString().contains("McFunctionExecuteCommand")) {
        val parentText = parent.text.lowercase()
        // " run " または行末の " run" を検出
        if (Regex("\\brun\\b").containsMatchIn(parentText)) {
          return true
        }
      }
      parent = parent.parent
    }
    return false
  }

  private fun isInsideSelectorType(element: PsiElement): Boolean {
      val text = element.containingFile.text
      val offset = element.textOffset
      if (offset <= 0) return false
      val prefix = text.substring(0, offset)
      // 直前が [ または , で、その後に type= が続いているか、あるいは type= の直後かを確認
      return prefix.trimEnd().endsWith("type=") || prefix.trimEnd().endsWith("type =")
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
          // 候補を追加
          result.addElement(
            LookupElementBuilder.create("$namespace:$functionPath")
              .withIcon(McFunctionIcons.FUNCTION)
          )
          // minecraft: 省略形
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

  private fun getIconForCommand(command: String): Icon = McFunctionIcons.forCommand(command.lowercase())

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
