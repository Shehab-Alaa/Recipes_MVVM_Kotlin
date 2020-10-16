package com.example.recipesapp.ui.main.recipe

import com.example.recipesapp.ui.base.BaseEmptyItemListener

class RecipeEmptyItemViewModel (private val recipeEmptyItemListener : RecipeEmptyItemListener) {

    fun onRetryClick() {
        recipeEmptyItemListener.onRetryClick()
    }

    interface RecipeEmptyItemListener : BaseEmptyItemListener
}