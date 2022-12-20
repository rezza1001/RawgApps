package com.rezzza.rawgapps.api.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezzza.rawgapps.api.CallApiService
import com.rezzza.rawgapps.api.data.ApiInterface
import com.rezzza.rawgapps.model.ResponseApiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    var  apiInterface : ApiInterface?= null
    init {
        apiInterface = CallApiService.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllGames(): LiveData<ResponseApiModel> {
        val data = MutableLiveData<ResponseApiModel>()

        apiInterface?.getAllGames()?.enqueue(object : Callback<ResponseApiModel> {

            override fun onFailure(call: Call<ResponseApiModel>, t: Throwable) {
                data.value = null
                Log.e("TAGRZ","response "+t.message);
            }

            override fun onResponse(
                call: Call<ResponseApiModel>,
                response: Response<ResponseApiModel>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    Log.e("TAGRZ","response "+response.code());
                    data.value = null
                }
            }
        })
        return data
    }
}