package com.example.recipesapp.ui.main.recipe

import android.view.View
import androidx.databinding.ObservableField
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.ui.base.BaseItemListener

class RecipeItemViewModel(private val recipe: Recipe,
                          private val recipeItemClickListener: RecipeItemClickListener ) {

    val recipeThumbnail: ObservableField<String> = ObservableField<String>(recipe.getRecipeThumbnail())
    val recipeName: ObservableField<String> = ObservableField<String>(recipe.getRecipeName())

    fun onItemClick(view : View){
        recipeItemClickListener.onItemClick(view , recipe)
    }

    interface RecipeItemClickListener : BaseItemListener<Recipe>  // to be implemented by the adapter.

}