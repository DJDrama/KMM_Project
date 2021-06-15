package com.dj.kmm.interactors.recipe_list

import com.dj.kmm.datasource.network.RecipeService
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {
        try {
            emit(DataState.loading())
            val recipes = recipeService.search(page = page, query = query)
            emit(DataState.data(data = recipes))
        } catch (e: Exception) {
            //how can we emit an error?
        }
    }
}