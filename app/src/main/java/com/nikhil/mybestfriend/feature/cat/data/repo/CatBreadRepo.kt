package com.nikhil.mybestfriend.feature.cat.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity

interface CatBreadRepo {
    suspend fun getCatBread(metric:Boolean) : LiveData<out List<UnitCatEntity>>

}