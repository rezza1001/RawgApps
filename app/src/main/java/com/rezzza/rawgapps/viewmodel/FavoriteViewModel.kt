package com.rezzza.rawgapps.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezzza.rawgapps.api.repo.HomeRepository
import com.rezzza.rawgapps.database.table.GamesDB

class FavoriteViewModel(application: Application): AndroidViewModel(application){

    private var homeRepository:HomeRepository?=null
    var postModelListLiveData : LiveData<ArrayList<GamesDB>>?=null

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
    }

    fun getAllFavorite(context: Context){
        val db = GamesDB()
        val listGames = db.getAllData(context)
        val data = MutableLiveData<ArrayList<GamesDB>>()
        data.value = listGames
        postModelListLiveData = data
    }

}