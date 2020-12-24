package com.example.recipesapp.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.utils.AppConstants
import io.reactivex.Single

@Dao
interface RecipesDAO {

    @Insert
    fun insert(recipe: Recipe)

    @Query("select * from ${AppConstants.DATABASE_NAME}")
    fun loadAll(): LiveData<MutableList<Recipe>>

    @Query("delete from ${AppConstants.DATABASE_NAME} where id = :recipeID")
    fun delete(recipeID: String)

    @Query("select COUNT(*) from ${AppConstants.DATABASE_NAME} where id = :recipeID")
    fun isExist(recipeID: String): Single<Int>

}