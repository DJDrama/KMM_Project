package com.dj.kmm.presentation.recipe_detail

import com.dj.kmm.domain.model.GenericMessageInfo
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)