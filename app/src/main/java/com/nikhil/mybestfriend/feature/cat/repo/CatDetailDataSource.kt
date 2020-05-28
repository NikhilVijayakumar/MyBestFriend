package com.nikhil.mybestfriend.feature.cat.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.model.CatDetails

interface CatDetailDataSource {

    val catDetailList: LiveData<List<CatDetails>>
    suspend fun fetchCatBreed(
        breedIds: String,
        includeBreeds: Boolean = true
    )

}