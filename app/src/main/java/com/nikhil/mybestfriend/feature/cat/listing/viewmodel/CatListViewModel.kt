package com.nikhil.mybestfriend.feature.cat.listing.viewmodel

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepo
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import com.nikhil.mybestfriend.feature.commons.enums.UnitSystem
import com.nikhil.mybestfriend.feature.commons.utils.lazyDeferred
import com.nikhil.mybestfriend.feature.commons.viewmodel.BaseViewModel
import com.nikhil.mybestfriend.feature.preferences.data.PreferenceHelper

class CatListViewModel(
    private val catListRepo: CatListRepo,
    private val preferenceHelper: PreferenceHelper) : BaseViewModel() {

    private val unitSystem
        get() = if(preferenceHelper.isMetric()){
            UnitSystem.METRIC
        }else{
            UnitSystem.IMPERIAL
        }


    val status: LiveData<RepoStatus> = catListRepo.repoStatus


    private val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val catList by lazyDeferred {
        catListRepo.getCatBread(isMetricUnit)
    }

}