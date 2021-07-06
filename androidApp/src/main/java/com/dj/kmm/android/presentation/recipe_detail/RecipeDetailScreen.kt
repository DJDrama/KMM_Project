package com.dj.kmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dj.kmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.dj.kmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import com.dj.kmm.android.presentation.recipe_detail.components.RecipeView
import com.dj.kmm.android.presentation.theme.AppTheme
import com.dj.kmm.presentation.recipe_detail.RecipeDetailEvents
import com.dj.kmm.presentation.recipe_detail.RecipeDetailState
import com.dj.kmm.presentation.recipe_list.RecipeListEvents

@ExperimentalStdlibApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
) {
    AppTheme(displayProgressBar = state.isLoading, dialogQueue = state.queue,
        onRemoveHeadFromQueue = {
            onTriggerEvent(RecipeDetailEvents.OnRemovedHeadMessageFromQueue)
        }
    ) {
        if (state.recipe == null && state.isLoading) {
            // Loading
            LoadingRecipeShimmer(
                imageHeight = RECIPE_IMAGE_HEIGHT.dp,
            )
        } else if (state.recipe == null) {
            Text(text = "Unable to fetch details for this recipe.",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1)
        } else {
            // Null Safe
            RecipeView(recipe = state.recipe!!)
        }
    }
}