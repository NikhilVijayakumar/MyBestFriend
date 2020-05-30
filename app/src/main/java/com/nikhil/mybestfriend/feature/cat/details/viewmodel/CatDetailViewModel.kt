package com.nikhil.mybestfriend.feature.cat.details.viewmodel

import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity
import com.nikhil.mybestfriend.feature.cat.data.repo.CatDetailRepo
import com.nikhil.mybestfriend.feature.commons.enums.UnitSystem
import com.nikhil.mybestfriend.feature.commons.utils.lazyDeferred
import com.nikhil.mybestfriend.feature.commons.viewmodel.BaseViewModel


class CatDetailViewModel(val catDetailRepo: CatDetailRepo) : BaseViewModel() {
    var data: UnitCatEntity? = null
    private val unitSystem = UnitSystem.METRIC
    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val unitCatEntity by lazyDeferred {
        data?.let {
            catDetailRepo.callCatDetailApi(it.id)
            return@let catDetailRepo.getCatDetails(it, isMetricUnit)
        }
    }

    fun addUnitCatEntity(entity: UnitCatEntity) {
        data = entity
    }

}
