package com.example.recipesapp.data

import com.example.recipesapp.data.model.Result
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.data.remote.ApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataRepository(private val apiRepository: ApiRepository , private val ioDispatcher: CoroutineDispatcher) : DataRepositorySource{

    override suspend fun requestRecipes(): Flow<Result<MutableList<Recipe>>> {
        return flow{
            emit(apiRepository.fetchLiveRecipesData())
        }.flowOn(ioDispatcher)
    }


}