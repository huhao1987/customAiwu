package hh.game.customaiwuclient.VM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hh.game.customaiwuclient.Models.Detail
import hh.game.customaiwuclient.Models.SearchResult
import hh.game.customaiwuclient.retrofit.AiwuService
import hh.game.customaiwuclient.retrofit.GeRespo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    var detail:MutableLiveData<Detail>?=null
    var searchResult:MutableLiveData<SearchResult>?=null
    var searchresultlist=MutableLiveData<ArrayList<SearchResult.Data>>()
    fun getDetail(packagename:String):LiveData<Detail>?{
        detail=MutableLiveData()
        GeRespo.getDetail(packagename,object:Callback<Detail>{
            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                detail?.postValue(response.body())
            }

            override fun onFailure(call: Call<Detail>, t: Throwable) {

            }
        })
        return detail
    }

    fun getSearch(keyword:String,page:Int=1):LiveData<SearchResult>?{
        searchResult=MutableLiveData()
        GeRespo.getSearchResult(keyword,page,object:Callback<SearchResult>{
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                searchResult?.postValue(response.body())
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {

            }
        })
        return searchResult
    }

    fun getAllSearch(searchnames:ArrayList<String>):MutableLiveData<ArrayList<SearchResult.Data>>{
        GlobalScope.launch {
            var temp=ArrayList<SearchResult.Data>()
            for(a in 239..242){
                var t= GeRespo.SearchInstance()
                    .create(AiwuService::class.java).getSearchResult(Key = searchnames[a]).execute().body()!!.data?.get(0)
                t?.aiWuOnlineCheat=GeRespo.DetailInstance().create(AiwuService::class.java).getCheatCode(Id = t!!.emuId).execute().also {
                    Log.d("thenetwork::",it.message())
                }.body()
                temp.add(t!!)
            }
            searchresultlist.postValue(temp)
        }

        return searchresultlist
    }


}