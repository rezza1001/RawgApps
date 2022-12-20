package com.rezzza.rawgapps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezzza.rawgapps.api.repo.DetailRepository
import com.rezzza.rawgapps.model.DetailApiModel

class DetailViewModel(application: Application): AndroidViewModel(application){

    private var repository:DetailRepository?=null
    var postModelListLiveData : LiveData<DetailApiModel>?=null

    init {
        repository = DetailRepository()
        postModelListLiveData = MutableLiveData()
    }

    fun getDetailGame(id: Int){
        postModelListLiveData = repository?.getDetailGame(id)
    }

}