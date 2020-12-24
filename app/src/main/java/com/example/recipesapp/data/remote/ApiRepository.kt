package com.example.recipesapp.data.remote

import com.example.recipesapp.data.model.Result
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.data.remote.network.ApiService

class ApiRepository(private val apiService: ApiService) : ApiDataSource {

    override suspend fun fetchLiveRecipesData(): Result<MutableList<Recipe>> {
        return try {
            val recipes = apiService.getRecipes()
            Result.Success(recipes as ArrayList<Recipe>)
        }catch (exception : Exception){
            Result.Error(exception.localizedMessage)
        }
    }
}