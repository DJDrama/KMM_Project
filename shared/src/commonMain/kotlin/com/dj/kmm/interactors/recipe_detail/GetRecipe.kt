package com.dj.kmm.interactors.recipe_detail

import com.dj.kmm.datasource.cache.RecipeCache
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache,
    ) {
    fun execute(recipeId: Int): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())
            val recipe = recipeCache.get(recipeId = recipeId)
            emit(DataState.data(message = null, data = recipe))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown Error"))
        }
    }
}