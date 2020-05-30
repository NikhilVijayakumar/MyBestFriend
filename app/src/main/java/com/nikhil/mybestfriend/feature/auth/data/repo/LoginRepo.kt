package com.nikhil.mybestfriend.feature.auth.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity

interface LoginRepo {
    suspend fun getCatBread(metric:Boolean) : LiveData<out List<UnitCatEntity>>
}