package com.nikhil.mybestfriend.feature.cat.data.api.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikhil.mybestfriend.feature.commons.data.api.service.CatAPIService
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatDetails
import com.nikhil.mybestfriend.feature.commons.exception.NoConnectionException

class CatDetailDataSourceImpl(private val apiService: CatAPIService) :
    CatDetailDataSource {

    private val _catDetailList = MutableLiveData<List<CatDetails>>()
    override val catDetailList: LiveData<List<CatDetails>>
        get() = _catDetailList

    override suspend fun fetchCatBreed(breedIds: String, includeBreeds: Boolean) {
        try {
            val catDetails = apiService
                .getCatDetails(breedIds, includeBreeds)
                .await()
            _catDetailList.postValue(catDetails)
        } catch (e: NoConnectionException) {
            Log.e("CatDetailDataSourceImpl", e.message)
        }
    }
}