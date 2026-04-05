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

  override fun getDemoText(): String = """
        <cm># function item:common/clicked</cm>
        <mj>advancement</mj> <sc>revoke</sc> <sl>@s</sl> <sc>only</sc> <ns>item</ns><nc>:</nc><ag>clicked</ag>

        <cm># item[json] with '=' property keys</cm>
        <ns>minecraft</ns><nc>:</nc><jk>stone</jk><st>[</st><jk>custom_name</jk><st>=</st><st>{</st><js>"italic"</js><st>:</st><jb>false</jb><st>,</st><js>"text"</js><st>:</st><js>"木の剣"</js><st>}</st><st>,</st><ns>minecraft</ns><nc>:</nc><jk>lore</jk><st>=</st><st>[</st><st>{</st><jk>color</jk><st>:</st><js>"aqua"</js><st>,</st><jk>italic</jk><st>:</st><jb>0b</jb><st>,</st><jk>text</jk><st>:</st><js>"価格 : 10コイン"</js><st>}</st><st>]</st><st>,</st><ns>minecraft</ns><nc>:</nc><jk>unbreakable</jk><st>=</st><st>{</st><st>}</st><st>,</st><jk>item_model</jk><st>=</st><js>"minecraft:wooden_sword"</js><st>]</st>

        <cm># 特殊アイテムか検知</cm>
        <fk>execute</fk> <fk>unless</fk> <mj>data</mj> <sc>entity</sc> <sl>@s</sl> <ag>SelectedItem.components.</ag><js>"minecraft:custom_data"</js><ag>.item_id</ag> <fk>run</fk> <fk>return</fk> <jb>0</jb>

        <cm># メインハンドのアイテムをdata storage</cm>
        <mj>data</mj> <sc>modify</sc> <sc>storage</sc> <ns>use</ns><nc>:</nc><ag>item</ag> <sc>set</sc> <sc>from</sc> <sc>entity</sc> <sl>@s</sl> <ag>SelectedItem</ag>

        <cm># 使用中止検知用</cm>
        <mj>tag</mj> <sl>@s</sl> <sc>add</sc> <ag>item.using_item</ag>
        <mj>scoreboard</mj> <sc>players</sc> <sc>add</sc> <sl>@s</sl> <ag>item.using_timer</ag> <jn>1</jn>

        <cm># 使用中演出</cm>
        <mj>title</mj> <sl>@s</sl> <sc>actionbar</sc> <st>{</st><js>"color"</js><st>:</st><js>"green"</js><st>,</st><js>"text"</js><st>:</st><js>"> 使用中 <"</js><st>}</st>
        <mj>scoreboard</mj> <sc>players</sc> <sc>add</sc> <sl>@s</sl> <ag>item.using_sound</ag> <jn>1</jn>
        <fk>execute</fk> <fk>if</fk> <sc>score</sc> <sl>@s</sl> <ag>item.using_sound</ag> <fk>matches</fk> <ag>20..</ag> <fk>run</fk> <mj>function</mj> <ns>item</ns><nc>:</nc><ag>common/using_sound</ag>

        <cm>## 開始ボタン</cm>
        <fk>execute</fk> <fk>if</fk> <mj>data</mj> <sc>storage</sc> <ns>use</ns><nc>:</nc><ag>item.components.</ag><js>"minecraft:custom_data"</js><st>{</st><jk>item_id</jk><st>:</st><js>"admin_start"</js><st>}</st> <st>\</st>
            <fk>if</fk> <sc>score</sc> <sl>@s</sl> <ag>item.using_timer</ag> <st>>=</st> <mc>$" + "Start_Required_Time</mc> <ag>item.using_timer</ag> <fk>run</fk> <mj>function</mj> <ns>core</ns><nc>:</nc><ag>admin/start</ag>

        <mj>tp</mj> <sl>@s</sl> <co>0 64 0</co>
    """.trimIndent()

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> = mapOf(
    "mj" to McFunctionSyntaxHighlighter.MAJOR_COMMAND,
    "sc" to McFunctionSyntaxHighlighter.SUB_COMMAND,
    "fk" to McFunctionSyntaxHighlighter.FLOW_KEYWORD,
    "sl" to McFunctionSyntaxHighlighter.SELECTOR,
    "ns" to McFunctionSyntaxHighlighter.NAMESPACE,
    "nc" to McFunctionSyntaxHighlighter.NAMESPACE_COLON,
    "mc" to McFunctionSyntaxHighlighter.MACRO,
    "jk" to McFunctionSyntaxHighlighter.JSON_KEY,
    "js" to McFunctionSyntaxHighlighter.JSON_STRING,
    "jn" to McFunctionSyntaxHighlighter.JSON_NUMBER,
    "jb" to McFunctionSyntaxHighlighter.JSON_BOOLEAN,
    "st" to McFunctionSyntaxHighlighter.STRUCTURE,
    "sr" to McFunctionSyntaxHighlighter.STRING,
    "ag" to McFunctionSyntaxHighlighter.ARGUMENT,
    "cm" to McFunctionSyntaxHighlighter.COMMENT,
    "co" to McFunctionSyntaxHighlighter.COORDINATE
  )
}

private val DESCRIPTORS = arrayOf(
  AttributesDescriptor("Major Commands (execute, advancement, data, etc.)", McFunctionSyntaxHighlighter.MAJOR_COMMAND),
  AttributesDescriptor("Sub Commands (set, modify, players, etc.)", McFunctionSyntaxHighlighter.SUB_COMMAND),
  AttributesDescriptor("Flow Keywords (if, run, unless, etc.)", McFunctionSyntaxHighlighter.FLOW_KEYWORD),
  AttributesDescriptor("Selectors (@s, @a, etc.)", McFunctionSyntaxHighlighter.SELECTOR),
  AttributesDescriptor("Namespace", McFunctionSyntaxHighlighter.NAMESPACE),
  AttributesDescriptor("Namespace Colon (:)", McFunctionSyntaxHighlighter.NAMESPACE_COLON),
  AttributesDescriptor("Macros (\$variable)", McFunctionSyntaxHighlighter.MACRO),
  AttributesDescriptor("JSON Property Key", McFunctionSyntaxHighlighter.JSON_KEY),
  AttributesDescriptor("JSON String Value", McFunctionSyntaxHighlighter.JSON_STRING),
  AttributesDescriptor("JSON Number Value", McFunctionSyntaxHighlighter.JSON_NUMBER),
  AttributesDescriptor("JSON Boolean/Byte Value", McFunctionSyntaxHighlighter.JSON_BOOLEAN),
  AttributesDescriptor("Structural Symbols (\\, [, ], {, }, ,, =, ., ..)", McFunctionSyntaxHighlighter.STRUCTURE),
  AttributesDescriptor("String", McFunctionSyntaxHighlighter.STRING),
  AttributesDescriptor("Values / Literals", McFunctionSyntaxHighlighter.ARGUMENT),
  AttributesDescriptor("Comment", McFunctionSyntaxHighlighter.COMMENT),
  AttributesDescriptor("Coordinates (~, ^)", McFunctionSyntaxHighlighter.COORDINATE)
)