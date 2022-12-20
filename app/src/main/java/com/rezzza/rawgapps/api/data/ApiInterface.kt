package com.rezzza.rawgapps.api.data

import com.rezzza.rawgapps.model.ResponseApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("games?key=d093283ffe954163af359e04218a0025")
    fun getAllGames(): Call<ResponseApiModel>
}
