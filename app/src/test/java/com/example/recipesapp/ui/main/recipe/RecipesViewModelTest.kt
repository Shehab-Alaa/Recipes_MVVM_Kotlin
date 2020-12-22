package com.example.recipesapp.ui.main.recipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.data.DataRepository
import com.example.recipesapp.data.model.Result
import com.example.recipesapp.data.model.Result.Error
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.util.TestCoroutineRule
import com.example.recipesapp.util.TestModelsGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecipesViewModelTest {

    private lateinit var recipesViewModel: RecipesViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val dataRepository : DataRepository = mockk()

    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    // sets the main dispatcher to testCoroutineDispatcher, It also creates testCoroutineScope to run our tests
    @ExperimentalCoroutinesApi
    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    // Executes each task synchronously using Architecture Components. (testing with LiveData)
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun beforeAll(){
        MockitoAnnotations.initMocks(this)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get Recipes List`  (){

        // Let's do an answer for the liveData
        val recipesModel = testModelsGenerator.getRecipesData()

        // 1- Mocks call
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(Result.Success(recipesModel))
        }

        // 2- Real Call
        recipesViewModel = RecipesViewModel(dataRepository, SavedStateHandle())
        recipesViewModel.fetchRecipes()
        recipesViewModel.recipesLiveData.observeForever{}

        // 3- verify
        val isEmptyList = recipesViewModel.recipesLiveData.value?.data.isNullOrEmpty()
        assertEquals(recipesModel, recipesViewModel.recipesLiveData.value?.data)
        assertEquals(false, isEmptyList)
    }

    @Test
    fun `get Recipes Empty List` (){

        // Let's do an answer for the liveData
        val recipesModel = testModelsGenerator.getEmptyRecipesData()

        // 1- Mocks call
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(Result.Success(recipesModel))
        }

        // 2- Real Call
        recipesViewModel = RecipesViewModel(dataRepository, SavedStateHandle())
        recipesViewModel.fetchRecipes()
        recipesViewModel.recipesLiveData.observeForever{}

        // 3- verify
        val isEmptyList = recipesViewModel.recipesLiveData.value?.data.isNullOrEmpty()
        assertEquals(recipesModel, recipesViewModel.recipesLiveData.value?.data)
        assertEquals(true, isEmptyList)
    }

    @Test
    fun `get Recipes Error`() {

        // Let's do an answer for the liveData
        val error: Result<MutableList<Recipe>> = Result.Error("NETWORK_ERROR")

        //1- Mock calls
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(error)
        }

        // 2- Real Call
        recipesViewModel = RecipesViewModel(dataRepository, SavedStateHandle())
        recipesViewModel.fetchRecipes()
        recipesViewModel.recipesLiveData.observeForever{}

        // 3- Verify
        assertEquals("NETWORK_ERROR", recipesViewModel.recipesLiveData.value?.errorMessage)
    }


}