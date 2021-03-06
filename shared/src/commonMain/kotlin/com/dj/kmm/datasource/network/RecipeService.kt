package com.dj.kmm.datasource.network

import com.dj.kmm.domain.model.Recipe

interface RecipeService {
    suspend fun search(
        page: Int,
        query: String,
    ): List<Recipe>

    suspend fun get(
        id: Int,
    ): Recipe
}