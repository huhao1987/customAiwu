package hh.game.customaiwuclient.retrofit

import hh.game.customaiwuclient.Models.Detail
import hh.game.customaiwuclient.Models.SearchResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AiwuService {
    @FormUrlEncoded
    @POST("AppGet.aspx")
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