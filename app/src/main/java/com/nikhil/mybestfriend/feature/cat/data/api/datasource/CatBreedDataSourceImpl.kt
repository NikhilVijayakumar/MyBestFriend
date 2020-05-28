package com.nikhil.mybestfriend.feature.cat.data.api.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikhil.mybestfriend.feature.commons.data.api.service.CatAPIService
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed
import com.nikhil.mybestfriend.feature.commons.exception.NoConnectionException

class CatBreedDataSourceImpl(private val apiService: CatAPIService) :
    CatBreedDataSource {
    private val _catList = MutableLiveData<List<CatBreed>>()
    override val catList: LiveData<List<CatBreed>>
        get() = _catList

    override suspend fun fetchCatBreed(limit: Int, page: Int) {
        try {
            val catBreedData = apiService
                .getCatBreed(limit, page)
                .await()
            _catList.postValue(catBreedData)
        } catch (e: NoConnectionException) {
            Log.e("CatBreedDataSourceImpl", e.message)
        }

    }
}