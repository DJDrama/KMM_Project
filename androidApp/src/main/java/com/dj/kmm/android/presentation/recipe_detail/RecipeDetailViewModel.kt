package com.dj.kmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.interactors.recipe_detail.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
) : ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            getRecipe(recipeId)
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            println("RecipeDetailViewModel: ${dataState.isLoading}")
            dataState.data?.let { recipe ->
                println("RecipeDetailViewModel: $recipe")
                this.recipe.value = recipe
            }
        }.launchIn(viewModelScope)
    }
}