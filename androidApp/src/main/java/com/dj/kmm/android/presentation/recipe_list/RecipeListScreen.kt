package com.dj.kmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dj.kmm.android.presentation.recipe_list.components.SearchAppBar
import com.dj.kmm.android.presentation.theme.AppTheme
import com.dj.kmm.presentation.recipe_list.FoodCategoryUtil
import com.dj.kmm.presentation.recipe_list.RecipeListEvents
import com.dj.kmm.presentation.recipe_list.RecipeListState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(displayProgressBar = state.isLoading,
        dialogQueue = state.queue) {
        val foodCategories = remember { FoodCategoryUtil().getAllFoodCategories() }
        Scaffold(
            topBar = {
                SearchAppBar(
                    query = state.query,
                    onQueryChanged = {
                        onTriggerEvent(RecipeListEvents.OnUpdateQuery(query = it))
                    },
                    onExecuteSearch = {
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    },
                    categories = foodCategories,
                    onSelectedCategoryChanged = {
                        onTriggerEvent(RecipeListEvents.OnSelectCategory(it))
                    },
                    selectedCategory = state.selectedCategory
                )
            }
        ) {
            RecipeList(
                isLoading = state.isLoading,
                recipes = state.recipes,
                onClickRecipeListItem = onClickRecipeListItem,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                }
            )
        }
    }
}