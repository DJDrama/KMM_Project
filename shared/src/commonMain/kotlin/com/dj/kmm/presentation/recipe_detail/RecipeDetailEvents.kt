package com.dj.kmm.presentation.recipe_detail

sealed class RecipeDetailEvents {
    data class GetRecipe(val recipeId: Int) : RecipeDetailEvents()
    object OnRemovedHeadMessageFromQueue: RecipeDetailEvents()
}
