package com.rezzza.rawgapps.api.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezzza.rawgapps.api.ApiConfig
import com.rezzza.rawgapps.api.CallApiService
import com.rezzza.rawgapps.api.service.ApiInterface
import com.rezzza.rawgapps.model.DetailApiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository {

    var  apiInterface : ApiInterface?= null

    init {
        apiInterface = CallApiService.getApiClient().create(ApiInterface::class.java)
    }

    fun getDetailGame(id: Int): LiveData<DetailApiModel> {
        val data = MutableLiveData<DetailApiModel>()

        val url = ApiConfig.GET_GAMES_ALL+"/"+id+"?key="+ApiConfig.API_KEY
        Log.d("DetailRepository","API $url")

        apiInterface?.getDetail(url)?.enqueue(object : Callback<DetailApiModel> {

            override fun onFailure(call: Call<DetailApiModel>, t: Throwable) {
                data.value = null
                Log.e("DetailRepository","response "+t.message);
            }

            override fun onResponse(
                call: Call<DetailApiModel>,
                response: Response<DetailApiModel>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    Log.e("DetailRepository","response "+response.code());
                    data.value = null
                }
            }
        })
        return data
    }
}