package hh.gametool.citra_cheat_tool.Beans.AiWu

data class AiWuCheat(
    val cheatCode: String?=null,
    val cheatId: Int,
    val checkbox: Boolean,
    val children: ArrayList<Children>?=null,
    val custom: Boolean,
    val desc: String,
    val isCustom: Boolean,
    val status: Int
)