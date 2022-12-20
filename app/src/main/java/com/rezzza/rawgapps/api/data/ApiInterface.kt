package com.rezzza.rawgapps.api.data

import com.rezzza.rawgapps.model.ResponseApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getAllGames(@Url url: String): Call<ResponseApiModel>
}
