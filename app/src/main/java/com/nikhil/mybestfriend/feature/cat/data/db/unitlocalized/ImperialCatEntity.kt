package com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized

import androidx.room.ColumnInfo

data class ImperialCatEntity(
    @ColumnInfo(name = "imperialWeight")
    override val weight: String
) : UnitCatEntity