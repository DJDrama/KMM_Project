package com.dj.kmm.presentation.recipe_list

import com.dj.kmm.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = emptyList(),
)