package com.dj.kmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.interactors.recipe_list.SearchRecipes
import com.dj.kmm.presentation.recipe_list.RecipeListEvents
import com.dj.kmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes,
) : ViewModel() {
    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
       onTriggerEvent(RecipeListEvents.LoadRecipes)
    }

     fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            is RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            is RecipeListEvents.NextPage -> {
                nextPage()
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }
            dataState.message?.let { message ->
                handleError(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun handleError(errorMessage: String) {
    }
}