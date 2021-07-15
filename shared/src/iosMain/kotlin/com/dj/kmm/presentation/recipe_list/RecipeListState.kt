package com.dj.kmm.presentation.recipe_list

import com.dj.kmm.domain.model.GenericMessageInfo
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.util.Queue

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = emptyList(),
    val bottomRecipe: Recipe?=null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
) {

    // For IOS
    constructor() : this(
        isLoading = false,
        page = 1,
        query = "",
        selectedCategory = null,
        recipes = listOf(),
        bottomRecipe = null,
        queue = Queue(mutableListOf())
    )

    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE=30
    }
}