package com.dj.kmm.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.kmm.interactors.recipe_list.SearchRecipes
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

    init{
        loadRecipes()
    }
    private fun loadRecipes() {
        searchRecipes.execute(
            page = 1,
            query = "chicken",
        ).onEach { dataState ->
            println("RecipeListViewModel: ${dataState.isLoading}")
            dataState.data?.let { recipes ->
                println("RecipeListViewModel: $recipes")
            }
            dataState.message?.let{message->
                println("RecipeListViewModel: $message")
            }
        }.launchIn(viewModelScope)
    }
}