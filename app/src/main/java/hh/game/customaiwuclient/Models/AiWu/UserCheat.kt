package hh.game.customaiwuclient.Models.AiWu


import com.google.gson.annotations.SerializedName

data class UserCheat(
    @SerializedName("Code")
    var code: String?,
    @SerializedName("GoodSum")
    var goodSum: String?,
    @SerializedName("Id")
    var id: String?,
    @SerializedName("Status")
    var status: String?,
    @SerializedName("Title")
    var title: String?
)