package com.nikhil.mybestfriend.feature.cat.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatBreedDataSource
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed
import com.nikhil.mybestfriend.feature.cat.data.db.doa.CatDoa
import com.nikhil.mybestfriend.feature.cat.data.db.entity.CatEntity
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity
import com.nikhil.mybestfriend.feature.commons.utils.toCatEntityList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CatListRepoImpl(
    private val catDoa: CatDoa,
    private val catBreedDataSource: CatBreedDataSource
) : CatListRepo {

    init {
        catBreedDataSource.catList.observeForever {
            saveCatBreed(it)
        }
    }

    private fun saveCatBreed(catBreedList: List<CatBreed>) {
        GlobalScope.launch(Dispatchers.IO) {
            val catEntityList:List<CatEntity> = catBreedList.toCatEntityList()
            catDoa.insertAll(catEntityList)
        }
    }

    override suspend fun getCatBread(metric: Boolean): LiveData<out List<UnitCatEntity>> {
        return withContext(Dispatchers.IO) {
            initCatBreedApi()
            return@withContext if (metric) catDoa.getCatMetric()
            else catDoa.getCatImperial()
        }

    }

    private suspend fun initCatBreedApi() {
        if (isFetchNeeded(ZonedDateTime.now().minusMinutes(60))) {
            fetchCatBreed()
        }
    }

    private suspend fun fetchCatBreed() {
        catBreedDataSource.fetchCatBreed()
    }


    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}



