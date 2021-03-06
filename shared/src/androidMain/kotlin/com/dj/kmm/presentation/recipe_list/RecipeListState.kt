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
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
)