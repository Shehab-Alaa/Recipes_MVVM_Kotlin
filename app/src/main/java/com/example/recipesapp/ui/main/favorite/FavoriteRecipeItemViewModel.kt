package com.example.recipesapp.ui.main.favorite

import android.view.View
import androidx.databinding.ObservableField
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.ui.base.BaseItemListener

class FavoriteRecipeItemViewModel(private val recipe: Recipe,
                                  private val recipeItemClickListener: RecipeItemClickListener ) {

    val recipeThumbnail: ObservableField<String> = ObservableField<String>(recipe.thumb)
    val recipeName: ObservableField<String> = ObservableField<String>(recipe.name)

    fun onItemClick(view : View){
        recipeItemClickListener.onItemClick(view , recipe)
    }

    interface RecipeItemClickListener : BaseItemListener<Recipe>  // to be implemented by the adapter.

}