package com.nikhil.mybestfriend.feature.cat.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.db.localized.UnitCatEntity
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus

interface CatListRepo {
    val repoStatus: LiveData<RepoStatus>
    suspend fun getCatBread(metric:Boolean) : LiveData<out List<UnitCatEntity>>

}