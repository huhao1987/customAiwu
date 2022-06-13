package hh.game.customaiwuclient.retrofit

import hh.game.customaiwuclient.Models.AiWu.AiWuOnlineCheat
import hh.game.customaiwuclient.Models.Detail
import hh.game.customaiwuclient.Models.SearchResult
import hh.gametool.citra_cheat_tool.Beans.AiWu.AiWuCheat
import hh.gametool.citra_cheat_tool.Beans.AiWu.aiWuCheatList
import retrofit2.Call
import retrofit2.http.*

interface AiwuService {
    @FormUrlEncoded
    @POST("v2/App/AppGet.aspx")
    fun getDetail(
        @Field("Serial")Serial:String="00000000-28fb-4bae-ffff-ffffef05ac4a",
        @Field("VersionCode")VersionCode:String="2353",
        @Field("isLogin")isLogin:Int=0,
        @Field("Act")Act:String="getEmuGamebyPackageName",
        @Field("PackageName")PackageName:String,
        @Field("Time")Time:Long,
        @Field("Sign")Sign:String
        ): Call<Detail>

    @FormUrlEncoded
    @POST("EmuInit.aspx")
    fun getCheatCode(
        @Field("VersionCode")VersionCode:String="11300",
        @Field("ApiVersionCode")ApiVersionCode:Int=1,
        @Field("Id")Id:Int=0
    ): Call<AiWuOnlineCheat>


    @FormUrlEncoded
    @POST("Search_Market_v2.aspx")
    fun getSearchResult(
        @Field("Serial")Serial:String="00000000-28fb-4bae-ffff-ffffef05ac4a",
        @Field("VersionCode")VersionCode:String="2353",
        @Field("isLogin")isLogin:Int=0,
        @Field("Act")Act:String="Page",
        @Field("Page")Page:Int=1,
        @Field("IndexType")IndexType:Int=2,
        @Field("ClassType")ClassType:Int=12,
        @Field("Key")Key:String
    ): Call<SearchResult>
}