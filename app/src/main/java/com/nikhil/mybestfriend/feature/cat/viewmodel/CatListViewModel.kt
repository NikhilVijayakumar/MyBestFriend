package com.nikhil.mybestfriend.feature.cat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikhil.mybestfriend.feature.cat.model.CatBreed

class CatListViewModel : ViewModel(){
    val catList = MutableLiveData<List<CatBreed>>()
    val apiError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    fun refresh(){

    }
}