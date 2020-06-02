package com.nikhil.mybestfriend.feature.auth.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val email: String,
    val password: String)