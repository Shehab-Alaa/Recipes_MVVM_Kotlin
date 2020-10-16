package com.example.recipesapp.data.remote

import com.example.recipesapp.data.model.Result
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.data.remote.network.ApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class ApiRepository : ApiDataSource , KoinComponent {

    private val apiService: ApiService by inject()

    override suspend fun fetchLiveRecipesData(): Result<MutableList<Recipe>> {
        return try {
            val recipes = apiService.getRecipes()
            Result.Success(recipes)
        }catch (exception : Exception){
            Result.Error(exception.localizedMessage)
        }
    }
}