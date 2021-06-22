package com.dj.kmm.android.presentation.recipe_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.dj.kmm.android.presentation.components.RecipeCard
import com.dj.kmm.domain.model.Recipe

@Composable
fun RecipeList(
    isLoading: Boolean,
    recipes: List<Recipe>,
    onClickRecipeListItem: (Int) -> Unit,
){
    if (isLoading && recipes.isEmpty()) {
        // Loading
    } else if (recipes.isEmpty()) {
        // Nothing To Show(No Recipes)
    } else {
        LazyColumn() {
            itemsIndexed(
                items = recipes,
            ) { index, recipe ->
                RecipeCard(recipe = recipe) {
                    onClickRecipeListItem(recipe.id)
                }
            }
        }
    }
}