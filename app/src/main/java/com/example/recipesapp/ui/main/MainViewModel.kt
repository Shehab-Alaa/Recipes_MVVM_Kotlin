package com.example.recipesapp.ui.main

import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataRepositorySource
import com.example.recipesapp.ui.base.BaseViewModel

class MainViewModel(dataRepository: DataRepositorySource, saveStateHandle : SavedStateHandle) : BaseViewModel(dataRepository,saveStateHandle) {
}