package com.dj.kmm.android.di

import com.dj.kmm.datasource.network.RecipeService
import com.dj.kmm.interactors.recipe_detail.GetRecipe
import com.dj.kmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
    ): SearchRecipes {
        return SearchRecipes(recipeService = recipeService)
    }


    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeService: RecipeService,
    ): GetRecipe {
        return GetRecipe(recipeService = recipeService)
    }
}