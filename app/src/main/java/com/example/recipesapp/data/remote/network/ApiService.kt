package com.example.recipesapp.data.remote.network

import com.example.recipesapp.data.model.db.Recipe
import retrofit2.http.GET

interface ApiService {

    @GET(ApiEndPoint.END_POINT)
    suspend fun getRecipes() : MutableList<Recipe>

}