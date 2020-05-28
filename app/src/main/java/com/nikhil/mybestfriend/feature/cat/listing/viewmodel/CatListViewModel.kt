package com.nikhil.mybestfriend.feature.cat.listing.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed
import com.nikhil.mybestfriend.feature.commons.viewmodel.BaseViewModel

class CatListViewModel : BaseViewModel() {
    val catList = MutableLiveData<List<CatBreed>>()
    val apiError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    fun refresh(){

    }
}