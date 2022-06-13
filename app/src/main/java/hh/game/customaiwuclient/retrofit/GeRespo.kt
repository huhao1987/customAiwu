package hh.game.customaiwuclient.retrofit

import hh.game.customaiwuclient.Models.AiWu.AiWuOnlineCheat
import hh.game.customaiwuclient.Models.Detail
import hh.game.customaiwuclient.Models.SearchResult
import hh.game.customaiwuclient.Utils.AiwuGameUtils
import hh.gametool.citra_cheat_tool.Beans.AiWu.AiWuCheat
import hh.gametool.citra_cheat_tool.Beans.AiWu.aiWuCheatList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class GeRespo {
    companion object {
        private var retrofit: Retrofit? = null
        private var logging = HttpLoggingInterceptor().also {
            it.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        fun setupOkhttp(): OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        fun DetailInstance(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl("https://service.25game.com/")
                .client(setupOkhttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit!!
        }

        fun SearchInstance(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl("https://search.25game.com/")
                .client(setupOkhttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit!!
        }

        fun getDetail(gameId: String, callback: Callback<Detail>) {
            val (time, sign) = AiwuGameUtils.getSign(gameId)
            DetailInstance().create(AiwuService::class.java)
                .getDetail(PackageName = gameId, Time = time, Sign = sign)
                .enqueue(object : Callback<Detail> {
                    override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                        callback.onResponse(call, response)
                    }

                    override fun onFailure(call: Call<Detail>, t: Throwable) {
                        callback.onFailure(call, t)
                    }

                })
        }

        fun getSearchResult(keyword: String, page: Int = 1, callback: Callback<SearchResult>) {
            SearchInstance().create(AiwuService::class.java)
                .getSearchResult(Page = page, Key = keyword)
                .enqueue(object : Callback<SearchResult> {
                    override fun onResponse(
                        call: Call<SearchResult>,
                        response: Response<SearchResult>
                    ) {
                        callback.onResponse(call, response)
                    }

                    override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                        callback.onFailure(call, t)
                    }
                })
        }

        fun getCheatCode(emuid: Int, callback: Callback<AiWuOnlineCheat>) {
            DetailInstance().create(AiwuService::class.java).getCheatCode(Id = emuid)
                .enqueue(object : Callback<AiWuOnlineCheat> {
                    override fun onResponse(
                        call: Call<AiWuOnlineCheat>,
                        response: Response<AiWuOnlineCheat>
                    ) {
                        callback.onResponse(call, response)
                    }

                    override fun onFailure(call: Call<AiWuOnlineCheat>, t: Throwable) {
                        callback.onFailure(call, t)
                    }
                })
        }
    }
}