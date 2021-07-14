package com.dj.kmm.di

import com.dj.kmm.interactors.recipe_detail.GetRecipe
import com.dj.kmm.presentation.recipe_detail.RecipeDetailEvents

class GetRecipeModule(
    private val cacheModule: CacheModule,
    private val networkModule: NetworkModule,
) {

    val getRecipe: GetRecipe by lazy {
        GetRecipe(
            recipeCache = cacheModule.recipeCache
        )
    }
}