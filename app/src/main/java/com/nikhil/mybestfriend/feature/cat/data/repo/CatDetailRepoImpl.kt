package com.nikhil.mybestfriend.feature.cat.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatDetailDataSource
import com.nikhil.mybestfriend.feature.cat.data.db.doa.CatDoa
import com.nikhil.mybestfriend.feature.cat.data.db.localized.UnitCatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatDetailRepoImpl(
    private val catDoa: CatDoa,
    private val catDetailDataSource: CatDetailDataSource
) : CatDetailRepo {
    private var entity: UnitCatEntity? = null

    init {
        catDetailDataSource.catDetailList.observeForever {

            updateCatDetails(it.get(0).url)
        }
    }

    private fun updateCatDetails(url:String) {
        GlobalScope.launch(Dispatchers.IO) {
            entity?.let {
                it.url = url
                catDoa.updateUrl(url, it.id)
            }
        }
    }


    override suspend fun getCatDetails(unitCatEntity: UnitCatEntity,metric: Boolean): LiveData<out UnitCatEntity> {
        entity = unitCatEntity
        return withContext(Dispatchers.IO) {
            return@withContext if (metric) catDoa.getCatMetricId(unitCatEntity.id)
            else catDoa.getCatImperialId(unitCatEntity.id)
        }
    }

    override suspend fun callCatDetailApi(id:String) {
     catDetailDataSource.fetchCatBreed(id)
    }
}