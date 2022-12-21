package com.rezzza.rawgapps.api.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezzza.rawgapps.api.ApiConfig
import com.rezzza.rawgapps.api.CallApiService
import com.rezzza.rawgapps.api.service.ApiInterface
import com.rezzza.rawgapps.model.ResponseApiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindRepository {

    var pageSize : Int = 20
    var  apiInterface : ApiInterface?= null

    init {
        apiInterface = CallApiService.getApiClient().create(ApiInterface::class.java)
    }

    fun findGames(search: String): LiveData<ResponseApiModel> {
        val data = MutableLiveData<ResponseApiModel>()

        val url = ApiConfig.GET_GAMES_ALL+"?key="+ApiConfig.API_KEY+"&page=1&page_size="+pageSize+"&search="+search
        Log.d("HomeRepository","API $url")

        apiInterface?.getAllGames(url)?.enqueue(object : Callback<ResponseApiModel> {

            override fun onFailure(call: Call<ResponseApiModel>, t: Throwable) {
                data.value = null
                Log.e("HomeRepository","response "+t.message);
            }

            override fun onResponse(
                call: Call<ResponseApiModel>,
                response: Response<ResponseApiModel>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    Log.e("HomeRepository","response "+response.code());
                    data.value = null
                }
            }
        })
        return data
    }
}