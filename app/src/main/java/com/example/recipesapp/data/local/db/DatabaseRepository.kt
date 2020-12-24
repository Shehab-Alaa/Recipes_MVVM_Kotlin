package com.example.recipesapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipesapp.data.model.db.Recipe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DatabaseRepository(private val appDatabase: AppDatabase) : DbDataSource {

    override fun getLiveFavoriteRecipes(): LiveData<MutableList<Recipe>> {
        return appDatabase.recipesDAO.loadAll()
    }

    override fun addFavoriteRecipe(recipe: Recipe) {
        appDatabase.recipesDAO.insert(recipe)
    }

    override fun deleteFavoriteRecipe(recipeID: String) {
        appDatabase.recipesDAO.delete(recipeID)
    }

    override fun checkFavoriteRecipe(recipeID: String): MutableLiveData<Boolean> {
        val isFavorite : MutableLiveData<Boolean> = MutableLiveData()
        appDatabase.recipesDAO.isExist(recipeID).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())
            .subscribe ({
                if (it == 0){
                    isFavorite.postValue(false)
                }else
                    isFavorite.postValue(true)
            },{

            })
        return isFavorite
    }

}