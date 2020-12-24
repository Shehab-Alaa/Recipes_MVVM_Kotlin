package com.example.recipesapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipesapp.data.local.db.dao.RecipesDAO
import com.example.recipesapp.data.model.db.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract val recipesDAO: RecipesDAO
}