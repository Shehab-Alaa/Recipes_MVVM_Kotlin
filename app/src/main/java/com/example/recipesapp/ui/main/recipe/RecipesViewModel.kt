package com.example.recipesapp.ui.main.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.DataManager
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.ui.base.BaseViewModel
import com.example.recipesapp.data.model.Result
import com.example.recipesapp.utils.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class RecipesViewModel(dataManager: DataManager, saveStateHandle : SavedStateHandle) : BaseViewModel(dataManager,saveStateHandle) {

    private val sortedBy : MutableLiveData<Boolean> = MutableLiveData(getSaveStateHandle().get(AppConstants.SORTED_BY)!!)
    private val recipesList : MutableLiveData<List<Recipe>> = MutableLiveData()
    private val coroutineExceptionHandler = CoroutineExceptionHandler{_ , throwable ->
        Log.i("Here" , "Response Handler Issue: " + throwable.localizedMessage)}

    init {
        fetchRecipes()
    }

    fun fetchRecipes(){
        viewModelScope.launch(coroutineExceptionHandler) {
            when(val result = getDataManager().getApiRepository().fetchLiveRecipesData()){
                is Result.Success<MutableList<Recipe>> -> {
                    if (sortedBy.value == AppConstants.FATS){
                        recipesList.value = result.data.sortedBy { it.getRecipeFats() }
                    }else if (sortedBy.value == AppConstants.CALORIES){
                        recipesList.value = result.data.sortedBy { it.getRecipeCalories() }
                    }
                }
                is Result.Error ->{
                    Log.i("Here" , "Recipes Request Failed")
                }
            }
        }
    }

    fun sortRecipesBy(boolean : Boolean){
        if (boolean == AppConstants.FATS){
            sortedBy.postValue(AppConstants.FATS)
            recipesList.postValue( recipesList.value?.sortedBy { it.getRecipeFats() } )
        }else if (boolean == AppConstants.CALORIES){
            sortedBy.postValue(AppConstants.CALORIES)
            recipesList.postValue( recipesList.value?.sortedBy { it.getRecipeCalories() } )
        }
    }

    fun filterRecipesData(searchInput : String){
        recipesList.postValue(recipesList.value!!.filter { it.getRecipeName()!!.contains(searchInput) })
    }

    val recipesLiveData : LiveData<List<Recipe>> get() = recipesList

    val sortedByLiveData : LiveData<Boolean> get() = sortedBy
}