package com.example.recipesapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipesapp.data.model.Result
import com.example.recipesapp.data.model.db.Recipe
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource{
    suspend fun requestRecipes() : Flow<Result<MutableList<Recipe>>>

    fun fetchLocalRecipes(): LiveData<MutableList<Recipe>>
    fun insertLocalRecipe(recipe: Recipe)
    fun deleteLocalRecipe(recipeID: String)
    fun isFavoriteRecipe(recipeID : String) : MutableLiveData<Boolean>
}