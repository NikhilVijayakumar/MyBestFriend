package com.nikhil.mybestfriend.feature.cat.model



data class CatDetails(
    val approved: Int,
    val breeds: List<CatBreed>,
    val height: Int,
    val id: String,
    val pending: Int,
    val rejected: Int,
    val url: String,
    val width: Int
)



