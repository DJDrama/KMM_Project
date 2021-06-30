package com.dj.kmm.presentation.recipe_detail

import com.dj.kmm.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
)