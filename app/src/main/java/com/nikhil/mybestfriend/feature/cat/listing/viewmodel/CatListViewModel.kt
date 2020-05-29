package com.nikhil.mybestfriend.feature.cat.listing.viewmodel

import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepo
import com.nikhil.mybestfriend.feature.commons.enums.UnitSystem
import com.nikhil.mybestfriend.feature.commons.utils.lazyDeferred
import com.nikhil.mybestfriend.feature.commons.viewmodel.BaseViewModel

class CatListViewModel(val catListRepo: CatListRepo) : BaseViewModel() {

    private val unitSystem = UnitSystem.METRIC

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val catList by lazyDeferred {
        catListRepo.getCatBread(isMetricUnit)
    }

}