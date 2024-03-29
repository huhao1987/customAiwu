package hh.game.customaiwuclient.Models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import hh.game.customaiwuclient.Models.AiWu.AiWuOnlineCheat
import hh.gametool.citra_cheat_tool.Beans.AiWu.AiWuCheat
import hh.gametool.citra_cheat_tool.Beans.AiWu.aiWuCheatList
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(
    @SerializedName("Code")
    var code: Int,
    @SerializedName("Data")
    var `data`: List<Data?>?,
    @SerializedName("PageIndex")
    var pageIndex: String?,
    @SerializedName("PageSize")
    var pageSize: String?,
    @SerializedName("TotalSize")
    var totalSize: String?
): Parcelable {

    @Parcelize
    data class Data(
        @SerializedName("Category")
        var category: String?,
        @SerializedName("ClassType")
        var classType: String?,
        @SerializedName("Edition")
        var edition: String?,
        @SerializedName("EmuId")
        var emuId: Int,
        @SerializedName("FileLink")
        var fileLink: String?,
        @SerializedName("FileSize")
        var fileSize: String?,
        @SerializedName("HasCheat")
        var hasCheat: String?,
        @SerializedName("Icon")
        var icon: String?,
        @SerializedName("Language")
        var language: String?,
        @SerializedName("MD5")
        var mD5: String?,
        @SerializedName("NeedRealName")
        var needRealName: String?,
        @SerializedName("PackageName")
        var packageName: String?,
        @SerializedName("Platform")
        var platform: String?,
        @SerializedName("Rating")
        var rating: String?,
        @SerializedName("Tag")
        var tag: String?,
        @SerializedName("Title")
        var title: String?,
        @SerializedName("UnZipSize")
        var unZipSize: String?,
        var aiWuOnlineCheat: AiWuOnlineCheat?=null
    ):Parcelable
}