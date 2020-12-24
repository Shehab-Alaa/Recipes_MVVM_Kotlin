package com.example.recipesapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipesapp.data.model.db.Recipe

interface DbDataSource {
    fun getLiveFavoriteRecipes(): LiveData<MutableList<Recipe>>
    fun addFavoriteRecipe(recipe: Recipe)
    fun deleteFavoriteRecipe(recipeID: String)
    fun checkFavoriteRecipe(recipeID: String): MutableLiveData<Boolean>
}