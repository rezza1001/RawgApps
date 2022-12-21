package com.rezzza.rawgapps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezzza.rawgapps.api.repo.FindRepository
import com.rezzza.rawgapps.model.ResponseApiModel

class FindViewModel(application: Application): AndroidViewModel(application){

    private var homeRepository:FindRepository?=null
    var postModelListLiveData : LiveData<ResponseApiModel>?=null

    init {
        homeRepository = FindRepository()
        postModelListLiveData = MutableLiveData()
    }

    fun findGames(search: String){
        postModelListLiveData = homeRepository?.findGames(search)
    }

}