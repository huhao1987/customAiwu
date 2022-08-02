package hh.gametool.citra_cheat_tool.Beans.AiWu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class aiWuCheatList(
    val aiWuCheatList: List<AiWuCheat>,
    val customCheatList: List<AiWuCheat>,
    val gameName: String,
    val romName: String,
    val shareCheatList: List<AiWuCheat>
):Parcelable
