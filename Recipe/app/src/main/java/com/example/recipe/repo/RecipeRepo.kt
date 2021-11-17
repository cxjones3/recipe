package com.example.recipe.repo

import android.app.Application
import com.example.recipe.model.Recipe
import com.example.recipe.model.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RecipeRepo(private val application: Application) {

    private val recipeDao by lazy{
        RecipeDatabase.getDatabase(application).recipeDao()
    }

    fun getAllRecipes() = recipeDao.getAllRecipes().flowOn(Dispatchers.IO)

    suspend fun addRecipe(recipe: Recipe){
        withContext(Dispatchers.IO){
            recipeDao.insertRecipe(recipe)
        }
    }

    suspend fun deleteRecipe(recipe: Recipe){
        withContext(Dispatchers.IO){
            recipeDao.deleteRecipe(recipe)
        }
    }
}