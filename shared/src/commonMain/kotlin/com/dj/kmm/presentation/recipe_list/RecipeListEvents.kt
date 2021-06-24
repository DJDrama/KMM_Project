package com.dj.kmm.presentation.recipe_list

sealed class RecipeListEvents {
    object LoadRecipes: RecipeListEvents()
    object NextPage: RecipeListEvents()
}