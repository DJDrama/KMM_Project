package com.dj.kmm.interactors.recipe_list

import com.dj.kmm.datasource.cache.RecipeCache
import com.dj.kmm.datasource.network.RecipeService
import com.dj.kmm.domain.model.Recipe
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
            val recipes = recipeService.search(page = page, query = query)

            delay(1000) // fake delay

            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(query = query, page = page)
            }
            emit(DataState.data(data = cacheResult))
        } catch (e: Exception) {
            //how can we emit an error?
        }
    }
}