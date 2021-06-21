package com.dj.kmm.android.di

import com.dj.kmm.android.BaseApplication
import com.dj.kmm.datasource.cache.DriverFactory
import com.dj.kmm.datasource.cache.RecipeCache
import com.dj.kmm.datasource.cache.RecipeCacheImpl
import com.dj.kmm.datasource.cache.RecipeDatabase
import com.dj.kmm.datasource.cache.RecipeDatabaseFactory
import com.dj.kmm.domain.util.DateTimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDatabase: RecipeDatabase,
    ): RecipeCache {
        return RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            dateTimeUtil = DateTimeUtil(),
        )
    }
}