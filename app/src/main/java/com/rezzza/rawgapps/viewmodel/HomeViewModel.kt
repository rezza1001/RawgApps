package com.rezzza.rawgapps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezzza.rawgapps.api.repo.HomeRepository
import com.rezzza.rawgapps.model.ResponseApiModel

class HomeViewModel(application: Application): AndroidViewModel(application){

    private var homeRepository:HomeRepository?=null
    var postModelListLiveData : LiveData<ResponseApiModel>?=null

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
    }

    fun fetchAllPosts(page: Int){
        postModelListLiveData = homeRepository?.fetchAllGames(page)
    }

}