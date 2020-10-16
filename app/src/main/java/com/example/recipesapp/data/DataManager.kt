package com.example.recipesapp.data

import com.example.recipesapp.data.remote.ApiRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class DataManager() : KoinComponent{

    private val mApiRepository: ApiRepository by inject()

    fun getApiRepository() : ApiRepository{
        return mApiRepository
    }
}