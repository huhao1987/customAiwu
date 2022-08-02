package hh.game.customaiwuclient.Models.AiWu


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AiWuOnlineCheat(
    @SerializedName("Code")
    var code: String?,
    @SerializedName("Data")
    var `data`: Data?
): Parcelable {

    @Parcelize
    data class Data(
        @SerializedName("AiwuCheat")
        var aiwuCheat: List<AiwuCheat?>?,
        @SerializedName("Cover")
        var cover: String?,
        @SerializedName("Id")
        var id: String?,
        @SerializedName("RomName")
        var romName: String?,
        @SerializedName("UserCheat")
        var userCheat: List<UserCheat?>?
    ):Parcelable {
        @Parcelize
        data class AiwuCheat(
            @SerializedName("Code")
            var code: String?,
            @SerializedName("GoodSum")
            var goodSum: String?,
            @SerializedName("Id")
            var id: String?,
            @SerializedName("list")
            var list: List<customChest>?,
            @SerializedName("Status")
            var status: String?,
            @SerializedName("Title")
            var title: String?,
            @SerializedName("TypeId")
            var typeId: String?
        ):Parcelable {
            @Parcelize
            data class customChest(
                @SerializedName("Code")
                var code: String?,
                @SerializedName("EmuId")
                var emuId: Int?,
                @SerializedName("ExId")
                var exId: Int?,
                @SerializedName("Explain")
                var explain: String?,
                @SerializedName("GoodSum")
                var goodSum: Int?,
                @SerializedName("Id")
                var id: Int?,
                @SerializedName("isAudit")
                var isAudit: Boolean?,
                @SerializedName("PostDate")
                var postDate: String?,
                @SerializedName("Status")
                var status: Int?,
                @SerializedName("Title")
                var title: String?,
                @SerializedName("TypeId")
                var typeId: Int?,
                @SerializedName("UserId")
                var userId: Int?
            ):Parcelable
        }
        @Parcelize
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
        ):Parcelable
    }
}