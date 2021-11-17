package com.example.recipe.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recs")
data class Recipe (
    @PrimaryKey(autoGenerate = true)val id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "descriptoin")val description : String,
    @ColumnInfo(name = "ingredients")val ingredients : String
)