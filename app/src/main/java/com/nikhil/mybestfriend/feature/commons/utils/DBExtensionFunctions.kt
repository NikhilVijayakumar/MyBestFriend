package com.nikhil.mybestfriend.feature.commons.utils

import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed
import com.nikhil.mybestfriend.feature.cat.data.db.entity.CatEntity

fun catRating(breed: CatBreed): Double {
    var catCommons: Double = catCommonFeatures(breed)
    var catCore: Double = catCoreFeatures(breed)
    var catproblems: Double = catIssues(breed)
    var rating = (catCommons + catCore) / 1.5
    rating -= (catproblems)
    return rating
}

fun catIssues(breed: CatBreed): Double {
    return (breed.healthIssues +
            breed.hypoallergenic +
            breed.suppressedTail +
            breed.shortLegs
            ) / 8.0
}

fun catCoreFeatures(breed: CatBreed): Double {
    return (breed.grooming +
            breed.hairless +
            breed.sheddingLevel +
            breed.intelligence +
            breed.socialNeeds
            ) / 5.0
}


fun catCommonFeatures(breed: CatBreed): Double {
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
            ) / 12.0
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