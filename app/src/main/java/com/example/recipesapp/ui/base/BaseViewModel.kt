package com.example.recipesapp.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.recipesapp.data.DataRepositorySource

abstract class BaseViewModel(private val dataRepository: DataRepositorySource, private val savedStateHandle : SavedStateHandle) : ViewModel()  {

    val isLoading = ObservableBoolean()

    fun getDataManager() : DataRepositorySource = dataRepository

    fun getSaveStateHandle() : SavedStateHandle = savedStateHandle

    fun setIsLoading(b:Boolean){
        isLoading.set(b)
    }
}