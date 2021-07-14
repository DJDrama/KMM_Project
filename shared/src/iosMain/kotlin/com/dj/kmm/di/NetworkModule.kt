package com.dj.kmm.di

import com.dj.kmm.datasource.network.KtorClientFactory
import com.dj.kmm.datasource.network.RecipeService
import com.dj.kmm.datasource.network.RecipeServiceImpl

class NetworkModule {

    val recipeService: RecipeService by lazy {
        RecipeServiceImpl(
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL
        )
    }
}