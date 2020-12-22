package com.example.recipesapp.ui.main.recipe

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

    private val recipesData = MutableLiveData<Result<MutableList<Recipe>>>()
    val recipesLiveData : LiveData<Result<MutableList<Recipe>>> get() = recipesData


    fun fetchRecipes() {
        viewModelScope.launch {
            dataRepository.requestRecipes().collect{
                recipesData.value = it
            }
        }
    }

    /*fun fetchRecipes(){
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
    }*/

    //TODO :: ViewModel emits Result and View Handle this Loading , Success , Error Not Adapter.
    //TODO :: Search for ProperWays to handle InternetConnectionFailure
    //TODO :: Solution ==> is to make View Handle all View logic (Visibility , Sorting)


    fun sortRecipesBy(boolean : Boolean){
        if (boolean == AppConstants.FATS){
            sortedBy.postValue(AppConstants.FATS)
            recipesData.postValue(recipesData.value?.data?.sortedBy { it.fats } as Result<MutableList<Recipe>>?)
        }else if (boolean == AppConstants.CALORIES){
            sortedBy.postValue(AppConstants.CALORIES)
            recipesData.postValue(recipesData.value?.data?.sortedBy { it.calories } as Result<MutableList<Recipe>>?)
        }
    }

    fun filterRecipesData(searchInput : String){
        recipesData.postValue(recipesData.value!!.data?.filter { it.name!!.contains(searchInput) } as Result<MutableList<Recipe>>?)
    }

}