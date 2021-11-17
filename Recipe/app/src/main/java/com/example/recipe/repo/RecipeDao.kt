package com.example.recipe.repo

import android.view.View
import androidx.room.*
import com.example.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recs")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)

    @Insert
    fun insertAllRecipes(vararg recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)
}