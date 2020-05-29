package com.nikhil.mybestfriend.feature.cat.data.db.doa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikhil.mybestfriend.feature.cat.data.db.entity.CatEntity
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.ImperialCatEntity
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.MetricCatEntity

@Dao
interface CatDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(catEntity: CatEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(catEntityList: List<CatEntity>)

    @Query("select * from cat")
    fun getCatMetric(): LiveData<List<MetricCatEntity>>

    @Query("select * from cat")
    fun getCatImperial(): LiveData<List<ImperialCatEntity>>
}