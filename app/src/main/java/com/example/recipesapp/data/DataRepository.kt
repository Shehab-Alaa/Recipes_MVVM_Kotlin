package com.example.recipesapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipesapp.data.local.db.DatabaseRepository
import com.example.recipesapp.data.model.Result
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.data.remote.ApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataRepository(private val apiRepository: ApiRepository, private val databaseRepository: DatabaseRepository, private val ioDispatcher: CoroutineDispatcher) : DataRepositorySource{

    override suspend fun requestRecipes(): Flow<Result<MutableList<Recipe>>> {
        return flow{
            emit(apiRepository.fetchLiveRecipesData())
        }.flowOn(ioDispatcher)
    }

    override fun fetchLocalRecipes(): LiveData<MutableList<Recipe>> {
        return databaseRepository.getLiveFavoriteRecipes()
    }

    override fun insertLocalRecipe(recipe: Recipe) {
        databaseRepository.addFavoriteRecipe(recipe)
    }

    override fun deleteLocalRecipe(recipeID: String) {
        databaseRepository.deleteFavoriteRecipe(recipeID)
    }

    override fun isFavoriteRecipe(recipeID: String): MutableLiveData<Boolean> {
        return databaseRepository.checkFavoriteRecipe(recipeID)
    }

}