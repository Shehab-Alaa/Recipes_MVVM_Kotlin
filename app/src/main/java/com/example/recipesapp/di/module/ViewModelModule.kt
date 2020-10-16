package com.example.recipesapp.di.module

import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataManager
import com.example.recipesapp.ui.main.MainViewModel
import com.example.recipesapp.ui.main.favorite.FavoriteRecipesViewModel
import com.example.recipesapp.ui.main.recipe.RecipesViewModel
import com.example.recipesapp.ui.main.recipe_details.RecipeDetailsViewModel
import com.example.recipesapp.ui.main.splash.SplashScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    single { provideDataManager()}

    viewModel { (handle : SavedStateHandle) -> MainViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> RecipesViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> RecipeDetailsViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> FavoriteRecipesViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> SplashScreenViewModel(get() , handle) }
}

private fun provideDataManager() = DataManager()
