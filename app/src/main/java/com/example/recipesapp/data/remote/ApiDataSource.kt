package com.example.recipesapp.data.remote

import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.data.model.Result


interface ApiDataSource {
    suspend fun fetchLiveRecipesData() : Result<MutableList<Recipe>>
}