package com.nikhil.mybestfriend.feature.commons.utils

import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed
import com.nikhil.mybestfriend.feature.cat.data.db.entity.CatEntity

fun catRating(breed: CatBreed): Int {
    var catCommons: Int = catCommonFeatures(breed)
    var catCore: Int = catCoreFeatures(breed)
    var catproblems: Int = catIssues(breed)
    var rating = (catCommons + catCore) / 2
    rating -= (catproblems) / 3
    return rating
}

fun catIssues(breed: CatBreed): Int {
    return (breed.healthIssues +
            breed.hypoallergenic +
            breed.suppressedTail +
            breed.shortLegs
            ) / 4
}

fun catCoreFeatures(breed: CatBreed): Int {
    return (breed.grooming +
            breed.hairless +
            breed.sheddingLevel +
            breed.intelligence +
            breed.socialNeeds
            ) / 5
}


fun catCommonFeatures(breed: CatBreed): Int {
    return (breed.adaptability +
            breed.affectionLevel +
            breed.childFriendly +
            breed.dogFriendly +
            breed.energyLevel +
            breed.experimental +
            breed.vocalisation +
            breed.strangerFriendly +
            breed.indoor +
            breed.natural +
            breed.rare +
            breed.rex
            ) / 12
}

fun CatBreed.toCatEntity(): CatEntity {
    return CatEntity(
        this.id,
        this.name,
        this.description,
        this.lifeSpan,
        this.origin,
        this.weight.imperial + " lb",
        this.weight.metric + " kg",
        null,
        catRating(this)
    )
}

fun List<CatBreed>.toCatEntityList(): List<CatEntity> {
    val entityList: MutableList<CatEntity> = ArrayList<CatEntity>()
    for (breed in this){
        entityList.add(breed.toCatEntity())
    }
    return entityList
}