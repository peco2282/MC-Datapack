package com.github.peco2282.mcdatapack.language.highlighting

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class McFunctionColorSettingsPage : ColorSettingsPage {

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName(): String = "McFunction"

  override fun getIcon(): Icon = McFunctionIcons.FILE

  override fun getHighlighter(): SyntaxHighlighter = McFunctionSyntaxHighlighter()

  override fun getDemoText(): String = $$"""
        # function item:common/clicked
        advancement revoke @s only item:clicked

        #特殊アイテムか検知
        execute unless data entity @s SelectedItem.components."minecraft:custom_data".item_id run return 0

        #メインハンドのアイテムをdata storage
        data modify storage use: item set from entity @s SelectedItem

        #使用中止検知用
        tag @s add item.using_item
        scoreboard players add @s item.using_timer 1

        #使用中演出
        title @s actionbar {"color":"green","text":"> 使用中 <"}
        scoreboard players add @s item.using_sound 1
        execute if score @s item.using_sound matches 20.. run function item:common/using_sound

        ##開始ボタン
        execute if data storage use: item.components."minecraft:custom_data"{item_id:"admin_start"} \
            if score @s item.using_timer >= $Start_Required_Time item.using_timer run function core:admin/start

        tp @s 0 64 0
    """.trimIndent()

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null
}

private val DESCRIPTORS = arrayOf(
  AttributesDescriptor(
    "Major Commands (execute, advancement, data, etc.)",
    McFunctionSyntaxHighlighter.MAJOR_COMMAND
  ),
  AttributesDescriptor("Sub Commands (set, modify, players, etc.)", McFunctionSyntaxHighlighter.SUB_COMMAND),
  AttributesDescriptor("Flow Keywords (if, run, unless, etc.)", McFunctionSyntaxHighlighter.FLOW_KEYWORD),
  AttributesDescriptor("Selectors (@s, @a, etc.)", McFunctionSyntaxHighlighter.SELECTOR),
  AttributesDescriptor("Macros (\$variable)", McFunctionSyntaxHighlighter.MACRO),
  AttributesDescriptor("Structural Symbols (\\, :, ., etc.)", McFunctionSyntaxHighlighter.STRUCTURE),
  AttributesDescriptor("String", McFunctionSyntaxHighlighter.STRING),
  AttributesDescriptor("Values / Literals", McFunctionSyntaxHighlighter.ARGUMENT),
  AttributesDescriptor("Comment", McFunctionSyntaxHighlighter.COMMENT)
)