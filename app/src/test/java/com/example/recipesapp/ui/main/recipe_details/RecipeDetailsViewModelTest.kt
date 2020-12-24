package com.example.recipesapp.ui.main.recipe_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataRepository
import com.example.recipesapp.util.TestModelsGenerator
import com.example.recipesapp.utils.AppConstants
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


class RecipeDetailsViewModelTest{

    private lateinit var recipesDetailsViewModel: RecipeDetailsViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val dataRepository : DataRepository = mockk()

    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()


    // Executes each task synchronously using Architecture Components. (testing with LiveData)
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun beforeAll(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `on Favorite Recipe Added`(){

        val isFavorite = MutableLiveData(false)
        val expectedIsFavorite = true
        val recipeModel = testModelsGenerator.getRecipesData()[0]

        // 1- Mock Call
        every { dataRepository.isFavoriteRecipe(recipeModel.id) } returns isFavorite
        justRun { dataRepository.insertLocalRecipe(recipeModel) }   // do nothing with database dependency

        // 2- Real Call
        recipesDetailsViewModel = RecipeDetailsViewModel(dataRepository , SavedStateHandle(mapOf(AppConstants.SELECTED_RECIPE to recipeModel)))
        recipesDetailsViewModel.onFavoriteBtnClick()

        // 3- Verify
        assertEquals(expectedIsFavorite , recipesDetailsViewModel.isFavoriteRecipe.value)
    }

    @Test
    fun `on Favorite Recipe Removed`(){

        val isFavorite = MutableLiveData(true)
        val expectedIsFavorite = false
        val recipeModel = testModelsGenerator.getRecipesData()[0]

        // 1- Mock Call
        every { dataRepository.isFavoriteRecipe(recipeModel.id) } returns isFavorite
        justRun { dataRepository.deleteLocalRecipe(recipeModel.id) }   // do nothing with database dependency

        // 2- Real Call
        recipesDetailsViewModel = RecipeDetailsViewModel(dataRepository , SavedStateHandle(mapOf(AppConstants.SELECTED_RECIPE to recipeModel)))
        recipesDetailsViewModel.onFavoriteBtnClick()

        // 3- Verify
        assertEquals(expectedIsFavorite , recipesDetailsViewModel.isFavoriteRecipe.value)
    }

}