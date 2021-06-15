package com.dj.kmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.dj.kmm.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
){
    if(recipe===null){
        Text("RecipeId is null!")
    }else{
        Column{
            Text("RecipeDetail Id : ${recipe.title}")
        }
    }
}