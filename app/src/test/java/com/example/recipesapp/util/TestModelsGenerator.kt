package com.example.recipesapp.util

import com.example.recipesapp.data.model.db.Recipe


class TestModelsGenerator {

    fun getRecipesData() : MutableList<Recipe>{
        return mutableListOf(Recipe(name = "A" , calories = null , carbos = null , country = null , fats = null , difficulty = 0 , id = null , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "B" , calories = null , carbos = null , country = null , fats = null , difficulty = 0 , id = null , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "C" , calories = null , carbos = null , country = null , fats = null , difficulty = 0 , id = null , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "D" , calories = null , carbos = null , country = null , fats = null , difficulty = 0 , id = null , description = null , headline = null , image = null , proteins = null , time = null , thumb = null))
    }

    fun getEmptyRecipesData() : MutableList<Recipe> = mutableListOf()
}
