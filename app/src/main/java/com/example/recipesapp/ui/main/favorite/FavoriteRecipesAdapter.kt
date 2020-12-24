package com.example.recipesapp.ui.main.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.databinding.ItemFavoriteRecipeBinding
import com.example.recipesapp.ui.base.BaseItemListener
import com.example.recipesapp.ui.base.BaseRecyclerViewAdapter
import com.example.recipesapp.ui.base.BaseViewHolder

class FavoriteRecipesAdapter(recipesItems : MutableList<Recipe>, private val recipesAdapterListener: RecipesAdapterListener) : BaseRecyclerViewAdapter<Recipe>(recipesItems) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return RecipesViewHolder(ItemFavoriteRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface RecipesAdapterListener : BaseItemListener<Recipe>

    inner class RecipesViewHolder(private val itemFavoriteRecipeBinding: ItemFavoriteRecipeBinding) : BaseViewHolder(itemFavoriteRecipeBinding.root), FavoriteRecipeItemViewModel.RecipeItemClickListener{

        override fun onBind(position: Int) {
            itemFavoriteRecipeBinding.favoriteRecipeItemViewModel = FavoriteRecipeItemViewModel(getItems()[position], this)
            itemFavoriteRecipeBinding.executePendingBindings()
        }

        override fun onItemClick(view: View, item: Recipe) {
            recipesAdapterListener.onItemClick(view, item)
        }
    }

}