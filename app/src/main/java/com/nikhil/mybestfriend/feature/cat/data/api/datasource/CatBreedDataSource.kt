package com.nikhil.mybestfriend.feature.cat.data.api.datasource

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed

interface CatBreedDataSource {
    val catList: LiveData<List<CatBreed>>
    suspend fun fetchCatBreed(
        limit: Int = 10,
        page: Int = 0
    )
}