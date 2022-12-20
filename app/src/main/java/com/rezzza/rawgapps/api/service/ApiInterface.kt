package com.rezzza.rawgapps.api.service

import com.rezzza.rawgapps.model.DetailApiModel
import com.rezzza.rawgapps.model.ResponseApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getAllGames(@Url url: String): Call<ResponseApiModel>

    @GET
    fun getDetail(@Url url: String): Call<DetailApiModel>
}
