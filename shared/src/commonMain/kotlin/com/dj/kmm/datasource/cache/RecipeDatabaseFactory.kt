package com.dj.kmm.datasource.cache

import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.util.DateTimeUtil
import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase(): RecipeDatabase{
        return RecipeDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun Recipe_Entity.toRecipe(): Recipe {
    val dateTimeUtil = DateTimeUtil()
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertStringToIngredientList(),
        dateAdded = dateTimeUtil.toLocalDate(date_added),
        dateUpdated = dateTimeUtil.toLocalDate(date_updated)
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe>{
    return map{
        it.toRecipe()
    }
}

fun List<String>.convertIngredientListToString(): String{
    val ingredientsString = StringBuilder()
    for(ingredient in this){
        ingredientsString.append("$ingredient,")
    }
    return ingredientsString.toString()
}

fun String.convertStringToIngredientList(): List<String>{
    val list: ArrayList<String>  = ArrayList()
    for(ingredient in split(",")){
        list.add(ingredient)
    }
    return list
}