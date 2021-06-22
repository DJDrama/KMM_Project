package com.dj.kmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dj.kmm.android.presentation.theme.AppTheme
import com.dj.kmm.presentation.recipe_list.RecipeListState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    // state : RecipeListState,
    // events: (RecipeListEvent) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(displayProgressBar = false) {
        RecipeList(isLoading = state.isLoading,
            recipes = state.recipes,
            onClickRecipeListItem = onClickRecipeListItem)
    }
}