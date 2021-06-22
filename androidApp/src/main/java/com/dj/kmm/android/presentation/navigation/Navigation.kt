package com.dj.kmm.android.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.dj.kmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.dj.kmm.android.presentation.recipe_detail.RecipeDetailViewModel
import com.dj.kmm.android.presentation.recipe_list.RecipeListScreen
import com.dj.kmm.android.presentation.recipe_list.RecipeListViewModel

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(context = LocalContext.current,
                navBackStackEntry = navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory = factory)
            RecipeListScreen(
                onSelectRecipe = { recipeId ->
                    navController.navigate("${Screen.RecipeDetail.route}/$recipeId")
                }
            )
        }
        composable(route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument(name = "recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeDetailViewModel =
                viewModel("RecipeDetailViewModel", factory)
            RecipeDetailScreen(
                recipe = viewModel.recipe.value
            )

        }
    }
}