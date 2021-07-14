package com.dj.kmm.di

import com.dj.kmm.datasource.cache.DriverFactory
import com.dj.kmm.datasource.cache.RecipeCache
import com.dj.kmm.datasource.cache.RecipeCacheImpl
import com.dj.kmm.datasource.cache.RecipeDatabase
import com.dj.kmm.datasource.cache.RecipeDatabaseFactory
import com.dj.kmm.domain.util.DateTimeUtil

class CacheModule {
    private val driverFactory: DriverFactory by lazy { DriverFactory() }
    private val recipeDatabase: RecipeDatabase by lazy {
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }
    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            dateTimeUtil = DateTimeUtil()
        )
    }
}