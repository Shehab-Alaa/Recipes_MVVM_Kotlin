package com.example.recipesapp.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.example.recipesapp.data.DataRepository
import com.example.recipesapp.data.DataRepositorySource
import com.example.recipesapp.data.local.db.AppDatabase
import com.example.recipesapp.data.local.db.DatabaseRepository
import com.example.recipesapp.data.remote.ApiRepository
import com.example.recipesapp.data.remote.network.ApiService
import com.example.recipesapp.ui.main.MainViewModel
import com.example.recipesapp.ui.main.favorite.FavoriteRecipesViewModel
import com.example.recipesapp.ui.main.recipe.RecipesViewModel
import com.example.recipesapp.ui.main.recipe_details.RecipeDetailsViewModel
import com.example.recipesapp.ui.main.splash.SplashScreenViewModel
import com.example.recipesapp.utils.AppConstants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    single { provideDataManager(get() , get() , get())}
    single { provideApiRepository(get())}
    single { provideDatabaseRepository(get())}
    single { provideCoroutineDispatcher()}
    single { provideAppDatabase(provideAppContext(get()) , provideDatabaseName())}

    viewModel { (handle : SavedStateHandle) -> MainViewModel(get() , handle ) }
    viewModel { (handle : SavedStateHandle) -> RecipesViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> RecipeDetailsViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> FavoriteRecipesViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> SplashScreenViewModel(get() , handle) }
}


private fun provideDataManager(apiRepository: ApiRepository, databaseRepository: DatabaseRepository, ioDispatcher : CoroutineDispatcher) : DataRepositorySource = DataRepository(apiRepository, databaseRepository ,ioDispatcher)
private fun provideApiRepository(apiService: ApiService) = ApiRepository(apiService)
private fun provideCoroutineDispatcher() = Dispatchers.IO

private fun provideDatabaseName() = AppConstants.DATABASE_NAME
private fun provideAppDatabase(context : Context, databaseName : String) = Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
    .allowMainThreadQueries().build()
private fun provideAppContext(application: Application) = application
private fun provideDatabaseRepository(appDatabase: AppDatabase) = DatabaseRepository(appDatabase)


