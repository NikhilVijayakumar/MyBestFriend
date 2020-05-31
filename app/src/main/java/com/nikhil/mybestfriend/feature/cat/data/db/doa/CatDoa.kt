package com.nikhil.mybestfriend.feature.cat.data.db.doa

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nikhil.mybestfriend.feature.cat.data.db.entity.CatEntity
import com.nikhil.mybestfriend.feature.cat.data.db.localized.ImperialCatEntity
import com.nikhil.mybestfriend.feature.cat.data.db.localized.MetricCatEntity

@Dao
interface CatDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(catEntity: CatEntity)

    @Transaction
    open fun updateData(catEntityList: List<CatEntity>) {
        deleteAllCats()
        insertAll(catEntityList)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(catEntityList: List<CatEntity>)

    @Query("DELETE FROM cat")
    fun deleteAllCats()

    @Query("select * from cat")
    fun getCatMetric(): LiveData<List<MetricCatEntity>>

    @Query("select * from cat")
    fun getCatImperial(): LiveData<List<ImperialCatEntity>>

    @Query("UPDATE cat SET url=:url WHERE id = :id")
    fun updateUrl(url: String, id: String)


    @Query("select * from cat WHERE id = :id")
    fun getCatMetricId( id: String): LiveData<MetricCatEntity>

    @Query("select * from cat WHERE id = :id")
    fun getCatImperialId( id: String): LiveData<ImperialCatEntity>

}