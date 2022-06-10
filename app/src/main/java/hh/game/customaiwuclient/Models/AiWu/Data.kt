package hh.game.customaiwuclient.Models.AiWu


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("AiwuCheat")
    var aiwuCheat: List<AiwuCheatX>?,
    @SerializedName("Cover")
    var cover: String?,
    @SerializedName("Id")
    var id: String?,
    @SerializedName("RomName")
    var romName: String?,
    @SerializedName("UserCheat")
    var userCheat: List<UserCheat>?
)