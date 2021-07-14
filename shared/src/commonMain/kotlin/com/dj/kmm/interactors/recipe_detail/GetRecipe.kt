package com.dj.kmm.interactors.recipe_detail

import com.dj.kmm.datasource.cache.RecipeCache
import com.dj.kmm.domain.model.GenericMessageInfo
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.model.UIComponentType
import com.dj.kmm.domain.util.CommonFlow
import com.dj.kmm.domain.util.DataState
import com.dj.kmm.domain.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache,
) {
    fun execute(recipeId: Int): CommonFlow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())
            delay(1500) // For Testing
            val recipe = recipeCache.get(recipeId = recipeId)
            emit(DataState.data(message = null, data = recipe))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = GenericMessageInfo.Builder()
                .id("SearchRecipes.Error")
                .title("Error")
                .uiComponentType(UIComponentType.Dialog)
                .description(e.message ?: "Unknown Error")
            ))
        }
    }.asCommonFlow()
}