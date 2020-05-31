package com.nikhil.mybestfriend.feature.cat.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat")
data class CatEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val lifeSpan: String,
    val origin: String,
    val imperialWeight: String,
    val metricWeight: String,
    var url: String? = null,
    val rating: Float
)