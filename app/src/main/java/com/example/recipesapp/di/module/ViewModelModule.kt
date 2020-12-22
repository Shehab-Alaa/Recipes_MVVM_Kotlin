package com.example.recipesapp.di.module

import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataRepository
import com.example.recipesapp.data.DataRepositorySource
import com.example.recipesapp.data.remote.ApiRepository
import com.example.recipesapp.data.remote.network.ApiService
import com.example.recipesapp.ui.main.MainViewModel
import com.example.recipesapp.ui.main.favorite.FavoriteRecipesViewModel
import com.example.recipesapp.ui.main.recipe.RecipesViewModel
import com.example.recipesapp.ui.main.recipe_details.RecipeDetailsViewModel
import com.example.recipesapp.ui.main.splash.SplashScreenViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    single { provideDataManager(get() , get())}
    single { provideApiRepository(get()) }
    single { provideCoroutineDispatcher() }

    viewModel { (handle : SavedStateHandle) -> MainViewModel(get() , handle ) }
    viewModel { (handle : SavedStateHandle) -> RecipesViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> RecipeDetailsViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> FavoriteRecipesViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> SplashScreenViewModel(get() , handle) }
}


private fun provideDataManager(apiRepository: ApiRepository, ioDispatcher : CoroutineDispatcher) : DataRepositorySource = DataRepository(apiRepository,ioDispatcher)
private fun provideApiRepository(apiService: ApiService) = ApiRepository(apiService)
private fun provideCoroutineDispatcher() = Dispatchers.IO



