package com.nikhil.mybestfriend.feature.cat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikhil.mybestfriend.feature.cat.model.CatDetails

class CatDetailViewModel : ViewModel() {
    val catDetails = MutableLiveData<CatDetails>()
    fun refresh() {

    }
}