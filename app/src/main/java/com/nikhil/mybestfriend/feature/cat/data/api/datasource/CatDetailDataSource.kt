package com.nikhil.mybestfriend.feature.cat.data.api.datasource

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatDetails

interface CatDetailDataSource {

    val catDetailList: LiveData<List<CatDetails>>
    suspend fun fetchCatBreed(
        breedIds: String,
        includeBreeds: Boolean = true
    )

}