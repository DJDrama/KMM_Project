package com.dj.kmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.kmm.datasource.network.RecipeService
import com.dj.kmm.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeService: RecipeService
) : ViewModel(){
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        try {
            savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
                viewModelScope.launch {
                    recipe.value = recipeService.get(recipeId)

                }
            }
        }catch (e: Exception){

        }
    }
}