package com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized

import androidx.room.ColumnInfo


data class ImperialCatEntity(
    @ColumnInfo(name = "id")
    override val id: String,
    @ColumnInfo(name = "name")
    override val name: String,
    @ColumnInfo(name = "description")
    override val description: String,
    @ColumnInfo(name = "lifeSpan")
    override val lifeSpan: String,
    @ColumnInfo(name = "origin")
    override val origin: String,
    @ColumnInfo(name = "url")
    override var url: String?=null,
    @ColumnInfo(name = "rating")
    override val rating: Double,
    @ColumnInfo(name = "imperialWeight")
    override val weight: String
) : UnitCatEntity {

}