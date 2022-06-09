package hh.game.customaiwuclient.Models


import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("Code")
    var code: Int,
    @SerializedName("Data")
    var `data`: Data?,
    @SerializedName("Message")
    var message:String?
) {
    data class Data(
        @SerializedName("Alias")
        var alias: String?,
        @SerializedName("CategoryId")
        var categoryId: String?,
        @SerializedName("CategoryName")
        var categoryName: String?,
        @SerializedName("ClassType")
        var classType: String?,
        @SerializedName("Edition")
        var edition: String?,
        @SerializedName("EmuId")
        var emuId: String?,
        @SerializedName("Evaluate")
        var evaluate: String?,
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
        @SerializedName("PackageName")
        var packageName: String?,
        @SerializedName("Rating")
        var rating: String?,
        @SerializedName("Tag")
        var tag: String?,
        @SerializedName("Title")
        var title: String?,
        @SerializedName("UpdateTime")
        var updateTime: String?
    )
}