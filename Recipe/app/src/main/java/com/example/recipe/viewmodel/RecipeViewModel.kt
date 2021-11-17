package com.example.recipe.viewmodel

import androidx.lifecycle.*
import com.example.recipe.model.Recipe
import com.example.recipe.repo.RecipeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import kotlinx.coroutines.flow.Flow

class RecipeViewModel(
    private val recipeRepo: RecipeRepo
) : ViewModel() {

    val recipes = recipeRepo.getAllRecipes()
        .asLiveData(viewModelScope.coroutineContext+Dispatchers.Default)

    fun addrecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeRepo.addRecipe(recipe)
        }
    }

    fun deleterecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeRepo.deleteRecipe(recipe)
        }
    }

    class Factory(
        private val recipeRepo: RecipeRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
                return RecipeViewModel(recipeRepo) as T
            } else {
                throw IllegalArgumentException("Cannot create instance of recipeViewModel")
            }
        }
    }

}