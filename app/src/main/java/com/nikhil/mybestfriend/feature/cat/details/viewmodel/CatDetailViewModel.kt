package com.nikhil.mybestfriend.feature.cat.details.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatDetails
import com.nikhil.mybestfriend.feature.commons.viewmodel.BaseViewModel

class CatDetailViewModel : BaseViewModel() {
    val catDetails = MutableLiveData<CatDetails>()
    fun refresh() {

    }
}