package com.example.recipesapp.ui.main.recipe_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataRepositorySource
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.ui.base.BaseViewModel
import com.example.recipesapp.utils.AppConstants

class RecipeDetailsViewModel(dataRepository: DataRepositorySource, saveStateHandle : SavedStateHandle) : BaseViewModel(dataRepository,saveStateHandle) {

    val recipe : Recipe? = getSaveStateHandle().get(AppConstants.SELECTED_RECIPE)

    val isFavoriteRecipe : MutableLiveData<Boolean> by lazy {
        getDataManager().isFavoriteRecipe(recipe?.id.toString())
    }

    fun onFavoriteBtnClick() {
        if(isFavoriteRecipe.value!!){
            getDataManager().deleteLocalRecipe(recipe?.id.toString())
            isFavoriteRecipe.postValue(false)
        }else{
            if (recipe != null) {
                getDataManager().insertLocalRecipe(recipe)
            }
            isFavoriteRecipe.postValue(true)
        }
    }
}