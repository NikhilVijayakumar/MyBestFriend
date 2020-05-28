package com.nikhil.mybestfriend.feature.cat.data.api.response

import com.google.gson.annotations.SerializedName
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed


data class CatDetails(
    val approved: Int,
    @SerializedName("breed_ids")
    val breedIds: String,
    val breeds: List<CatBreed>,
    @SerializedName("created_at")
    val createdAt: String,
    val height: Int,
    val id: String,
    @SerializedName("original_filename")
    val originalFilename: String,
    val pending: Int,
    val rejected: Int,
    @SerializedName("sub_id")
    val subId: String,
    val url: String,
    val width: Int
)



