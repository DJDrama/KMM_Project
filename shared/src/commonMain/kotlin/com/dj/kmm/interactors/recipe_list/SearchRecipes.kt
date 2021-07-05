package com.dj.kmm.interactors.recipe_list

import com.dj.kmm.datasource.cache.RecipeCache
import com.dj.kmm.datasource.network.RecipeService
import com.dj.kmm.domain.model.GenericMessageInfo
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.model.UIComponentType
import com.dj.kmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {
        try {
            emit(DataState.loading())
            delay(500) // fake delay

            // Test
            if (query == "error") {
                throw Exception("Forcing an Error(Search Failed)")
            }

            val recipes = recipeService.search(page = page, query = query)




            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(query = query, page = page)
            }
            emit(DataState.data(data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message?: "Unknown Error")
            ))
        }
    }
}