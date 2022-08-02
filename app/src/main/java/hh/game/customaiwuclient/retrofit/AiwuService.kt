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
        @Field("VersionCode")VersionCode:String="2360",
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
        @Field("VersionCode")VersionCode:String="2360",
        @Field("isLogin")isLogin:Int=0,
        @Field("Act")Act:String="Page",
        @Field("Page")Page:Int=1,
        @Field("Channel")Channel:String="25az",

        /**
         * IndexType could be the search type
         * 0 is for all games
         * 2 is emu
         * 1 is for Android games
         * ClassType is for emu type
         * 0 is for everything
         * 1 is for Java
         * 2 is for PSP
         * 3 is for GBA
         * 4 is for FC
         * 6 is for SFC
         * 7 is for Mame
         * 8 is for mame+
         * 9 is for NDS
         * 10 is for GBC
         * 11 is for MD
         * 12 is for 3ds
         * 13 is for wii
         * NGC 14
         * ONS 15
         * easyRPG 16
         * PS1 17
         * DC 18
         * N64 19
         * SS 20
         * OpenBOR 21
         * PS2 22
         * SMS 23
         * NS 24
         * PCE 25
         * DOS 26
         */
        @Field("IndexType")IndexType:Int=2,
        @Field("ClassType")ClassType:Int=0,
        @Field("Key")Key:String
    ): Call<SearchResult>
}