package com.dj.kmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    recipeId: Int?
){
    if(recipeId==null){
        Text("RecipeId is null!")
    }else{
        Column{
            Text("RecipeDetail Id : ${recipeId}")
        }
    }
}