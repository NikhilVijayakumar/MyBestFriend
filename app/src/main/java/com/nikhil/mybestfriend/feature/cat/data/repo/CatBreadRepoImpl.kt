package com.nikhil.mybestfriend.feature.cat.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatBreedDataSource
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed
import com.nikhil.mybestfriend.feature.cat.data.db.doa.CatDoa
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity
import com.nikhil.mybestfriend.feature.commons.utils.toCatEntityList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class CatBreadRepoImpl(
    private val catDoa: CatDoa,
    private val catBreedDataSource: CatBreedDataSource
) : CatBreadRepo {

    init {
        catBreedDataSource.catList.observeForever {
            saveCatBreed(it)
        }
    }

    private fun saveCatBreed(catBreedList: List<CatBreed>) {
        GlobalScope.launch(Dispatchers.IO) {
            catDoa.upsert(catBreedList.toCatEntityList())
        }
    }

    override suspend fun getCatBread(metric: Boolean): LiveData<out List<UnitCatEntity>> {
        return withContext(Dispatchers.IO) {
            initCatBreed()
            return@withContext if (metric) catDoa.getCatMetric()
            else catDoa.getCatImperial()
        }

    }

    private suspend fun initCatBreed() {
        if (isFetchNeeded(ZonedDateTime.now().minusMinutes(60))) {
            fetchCatBreed()
        }
    }

    private suspend fun fetchCatBreed() {
        catBreedDataSource.fetchCatBreed();
    }

    /*Todo update time implementation */
    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}



