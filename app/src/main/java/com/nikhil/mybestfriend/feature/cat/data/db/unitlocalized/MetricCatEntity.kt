package com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized

import androidx.room.ColumnInfo

data class MetricCatEntity (
    @ColumnInfo(name = "metricWeight")
    override val weight: String
) : UnitCatEntity