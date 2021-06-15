package com.dj.kmm.datasource.network

import com.dj.kmm.datasource.network.model.RecipeDto
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.util.DateTimeUtil
import io.ktor.client.*

expect class KtorClientFactory(){
    fun build(): HttpClient
}

fun RecipeDto.toRecipe(): Recipe {
    val dateTimeUtil = DateTimeUtil()
    return Recipe(
        id=pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = dateTimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = dateTimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}