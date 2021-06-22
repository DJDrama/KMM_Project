package com.dj.kmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dj.kmm.android.presentation.components.RecipeImage
import com.dj.kmm.android.presentation.theme.AppTheme
import com.dj.kmm.domain.model.Recipe

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
) {
    AppTheme(displayProgressBar = false) {
        if (recipe === null) {
            Text("RecipeId is null!")
        } else {
            RecipeImage(url = recipe.featuredImage, contentDescription = recipe.title)
        }
    }
}