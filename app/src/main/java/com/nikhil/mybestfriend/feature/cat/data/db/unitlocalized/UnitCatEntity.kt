package com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized

import java.io.Serializable

interface UnitCatEntity : Serializable {
    val id: String
    val name: String
    val description: String
    val lifeSpan: String
    val origin: String
    val weight: String
    var url: String?
    val rating: Int

}