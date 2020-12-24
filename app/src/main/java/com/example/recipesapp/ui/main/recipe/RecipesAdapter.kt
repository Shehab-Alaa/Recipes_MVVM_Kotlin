package com.example.recipesapp.ui.main.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.databinding.ItemRecipeBinding
import com.example.recipesapp.ui.base.BaseEmptyItemListener
import com.example.recipesapp.ui.base.BaseItemListener
import com.example.recipesapp.ui.base.BaseRecyclerViewAdapter
import com.example.recipesapp.ui.base.BaseViewHolder

class RecipesAdapter(recipesItems : MutableList<Recipe>, private val recipesAdapterListener: RecipesAdapterListener) : BaseRecyclerViewAdapter<Recipe>(recipesItems) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return  RecipesViewHolder(ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface RecipesAdapterListener : BaseItemListener<Recipe>, BaseEmptyItemListener

    inner class RecipesViewHolder(private val itemRecipeBinding: ItemRecipeBinding) : BaseViewHolder(itemRecipeBinding.root), RecipeItemViewModel.RecipeItemClickListener{

        override fun onBind(position: Int) {
            itemRecipeBinding.recipeItemViewModel = RecipeItemViewModel(getItems()[position], this)
            itemRecipeBinding.executePendingBindings()
        }

        override fun onItemClick(view: View, item: Recipe) {
            recipesAdapterListener.onItemClick(view, item)
        }
    }

}