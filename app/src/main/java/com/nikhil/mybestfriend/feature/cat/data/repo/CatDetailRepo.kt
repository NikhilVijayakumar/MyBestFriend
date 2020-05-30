package com.nikhil.mybestfriend.feature.cat.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity

interface CatDetailRepo {

    suspend fun getCatDetails(
        unitCatEntity: UnitCatEntity,
        metric: Boolean
    ): LiveData<out UnitCatEntity>

    suspend fun callCatDetailApi(id: String)
}
