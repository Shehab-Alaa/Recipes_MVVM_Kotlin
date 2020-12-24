package com.example.recipesapp.ui.main.recipe

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.DataRepositorySource
import com.example.recipesapp.data.model.Result
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.ui.base.BaseViewModel
import com.example.recipesapp.utils.AppConstants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesViewModel(private val dataRepository: DataRepositorySource, saveStateHandle: SavedStateHandle) : BaseViewModel(dataRepository,saveStateHandle) {

    private val sortedBy : MutableLiveData<Boolean> = MutableLiveData(getSaveStateHandle().get(AppConstants.SORTED_BY) ?: AppConstants.CALORIES)
    val sortedByLiveData : LiveData<Boolean> get() = sortedBy

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipesData = MutableLiveData<Result<MutableList<Recipe>>>()
    val recipesLiveData : LiveData<Result<MutableList<Recipe>>> get() = recipesData

    fun fetchRecipes() {
        viewModelScope.launch {
            recipesData.value = Result.Loading()
            dataRepository.requestRecipes().collect{
                recipesData.value = it
            }
        }
    }

    fun sortRecipesBy(boolean : Boolean?){
        if (boolean == AppConstants.FATS){
            sortedBy.postValue(AppConstants.FATS)
            recipesData.postValue(Result.Success(recipesData.value?.data?.sortedBy { it.fats } as MutableList<Recipe>))
        }else if (boolean == AppConstants.CALORIES){
            sortedBy.postValue(AppConstants.CALORIES)
            recipesData.postValue(Result.Success(recipesData.value?.data?.sortedBy { it.calories } as MutableList<Recipe>))
        }
    }

    fun filterRecipesData(searchInput : String){
        recipesData.postValue(Result.Success(recipesData.value?.data?.filter { it.name!!.contains(searchInput) } as MutableList<Recipe>))
        if (recipesData.value!!.data.isNullOrEmpty()){
            recipesData.postValue(Result.Error(AppConstants.ERROR_MESSAGE))
        }
    }

}