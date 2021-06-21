package com.dj.kmm.datasource.cache

import com.dj.kmm.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.util.DateTimeUtil

class RecipeCacheImpl(
    private val recipeDatabase: RecipeDatabase,
    private val dateTimeUtil: DateTimeUtil,
) : RecipeCache {
    private val queries: RecipeDbQueries = recipeDatabase.recipeDbQueries

    override fun insert(recipe: Recipe) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            ingredients = recipe.ingredients.convertIngredientListToString(), //TODO("Convert String to List<String>")
            date_updated = dateTimeUtil.toEpochMilliseconds(recipe.dateUpdated),
            date_added = dateTimeUtil.toEpochMilliseconds(recipe.dateAdded)
        )
    }

    override fun insert(recipes: List<Recipe>) {
        for(recipe in recipes){
            insert(recipe)
        }
    }

    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query,
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page-1)* RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.getAllRecipes(
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page-1)* RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun get(recipeId: Int): Recipe? {
        return try{
            queries.getRecipeById(id = recipeId.toLong())
                .executeAsOne().toRecipe()
        }catch(e: NullPointerException){
            null
        }
    }
}