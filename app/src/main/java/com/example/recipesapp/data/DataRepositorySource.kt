package com.example.recipesapp.data

import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.data.model.Result
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestRecipes() : Flow<Result<MutableList<Recipe>>>
}