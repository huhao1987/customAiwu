package hh.gametool.citra_cheat_tool.Beans.AiWu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Children(
    val cheatCode: String,
    val cheatId: Int,
    val custom: Boolean,
    val desc: String,
    val isCustom: Boolean,
    val status: Int
): Parcelable