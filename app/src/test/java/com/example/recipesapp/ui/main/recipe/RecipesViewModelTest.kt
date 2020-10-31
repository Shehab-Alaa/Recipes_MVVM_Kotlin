package com.example.recipesapp.ui.main.recipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataRepository
import com.example.recipesapp.data.model.Result
import com.example.recipesapp.data.remote.ApiRepository
import com.example.recipesapp.data.remote.network.ApiService
import com.example.recipesapp.util.MainCoroutineRule
import com.example.recipesapp.util.TestModelsGenerator
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class RecipesViewModelTest {

    private lateinit var recipesViewModel: RecipesViewModel

    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    // Set the main coroutines dispatcher for unit testing.
   /* @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()*/

    // Executes each task synchronously using Architecture Components.
   /* @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()*/


    @Test
    fun `get Recipes List`  (){

    }

    @Test
    fun `get Recipes Empty List` (){

        // Arrange


        // Act


        // Assert
    }

    @Test
    fun `get Recipes Response Error` (){

        // Arrange


        // Act


        // Assert

    }



}