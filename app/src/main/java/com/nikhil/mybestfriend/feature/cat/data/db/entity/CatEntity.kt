package com.nikhil.mybestfriend.feature.cat.data.db.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

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
    val rating: Int
) : Serializable