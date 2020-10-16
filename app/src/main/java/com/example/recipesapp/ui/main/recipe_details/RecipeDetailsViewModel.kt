package com.example.recipesapp.ui.main.recipe_details

import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataManager
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.ui.base.BaseViewModel
import com.example.recipesapp.utils.AppConstants

class RecipeDetailsViewModel(dataManager: DataManager, saveStateHandle : SavedStateHandle) : BaseViewModel(dataManager,saveStateHandle) {

    val recipe : Recipe = getSaveStateHandle().get(AppConstants.SELECTED_RECIPE)!!

}