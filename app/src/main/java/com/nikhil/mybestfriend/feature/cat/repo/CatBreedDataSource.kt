package com.nikhil.mybestfriend.feature.cat.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.model.CatBreed

interface CatBreedDataSource {
    val catList: LiveData<List<CatBreed>>
    suspend fun fetchCatBreed(
        limit: Int = 10,
        page: Int = 0
    )
}