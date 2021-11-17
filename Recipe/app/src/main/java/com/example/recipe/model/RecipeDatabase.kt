package com.example.recipe.model

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipe.repo.RecipeDao

@Database(entities = [Recipe::class],version = 1)
abstract class RecipeDatabase : RoomDatabase(){
    abstract fun recipeDao() : RecipeDao

    companion object {
        const val DATABASE_NAME = "recipe_database"
        private lateinit var application: Application
        private val database: RecipeDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(application, RecipeDatabase::class.java, DATABASE_NAME).build()
        }


        fun getDatabase(application2: Application): RecipeDatabase {
            this.application = application2
            return database
        }
    }
}