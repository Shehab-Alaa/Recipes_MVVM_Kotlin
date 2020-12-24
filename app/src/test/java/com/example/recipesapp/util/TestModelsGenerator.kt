package com.example.recipesapp.util

import com.example.recipesapp.data.model.db.Recipe


class TestModelsGenerator {

    fun getRecipesData() : MutableList<Recipe>{
        return mutableListOf(Recipe(name = "A" , calories = "1" , carbos = null , country = null , fats = "1" , difficulty = 0 , id = "0" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "B" , calories = "4" , carbos = "4" , country = null , fats = "4" , difficulty = 0 , id = "1" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "C" , calories = "3" , carbos = "3" , country = null , fats = "3" , difficulty = 0 , id = "2" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "D" , calories = "2" , carbos = "2" , country = null , fats = "2" , difficulty = 0 , id = "3" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null))
    }

    fun getSortedRecipesByFats()  : MutableList<Recipe>{
        return mutableListOf(Recipe(name = "A" , calories = null , carbos = null , country = null , fats = "1" , difficulty = 0 , id = "0" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "D" , calories = null , carbos = null , country = null , fats = "2" , difficulty = 0 , id = "1" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "C" , calories = null , carbos = null , country = null , fats = "3" , difficulty = 0 , id = "2" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "B" , calories = null , carbos = null , country = null , fats = "4" , difficulty = 0 , id = "3" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null))
    }

    fun getSortedRecipesByCalories()  : MutableList<Recipe>{
        return mutableListOf(Recipe(name = "A" , calories = "1" , carbos = null , country = null , fats = "1" , difficulty = 0 , id = "0" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "D" , calories = "2" , carbos = null , country = null , fats = "2" , difficulty = 0 , id = "1" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "C" , calories = "3" , carbos = null , country = null , fats = "3" , difficulty = 0 , id = "2" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null)
            , Recipe(name = "B" , calories = "4" , carbos = null , country = null , fats = "4" , difficulty = 0 , id = "3" , description = null , headline = null , image = null , proteins = null , time = null , thumb = null))
    }


    fun getEmptyRecipesData() : MutableList<Recipe> = mutableListOf()

    fun getRecipeSearchName() = "A"

    fun getNonExistRecipeName() = "F"
}
