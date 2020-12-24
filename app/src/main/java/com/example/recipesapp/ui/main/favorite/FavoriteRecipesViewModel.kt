package com.example.recipesapp.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataRepositorySource
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.ui.base.BaseViewModel

class FavoriteRecipesViewModel(dataRepository: DataRepositorySource, saveStateHandle : SavedStateHandle) : BaseViewModel(dataRepository,saveStateHandle) {

    val favoriteRecipesLiveData : LiveData<MutableList<Recipe>> by lazy{
        getDataManager().fetchLocalRecipes()
    }

}