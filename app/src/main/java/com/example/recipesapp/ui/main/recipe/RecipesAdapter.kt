package com.example.recipesapp.ui.main.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.databinding.ItemEmptyRecipeBinding
import com.example.recipesapp.databinding.ItemRecipeBinding
import com.example.recipesapp.ui.base.BaseEmptyItemListener
import com.example.recipesapp.ui.base.BaseItemListener
import com.example.recipesapp.ui.base.BaseRecyclerViewAdapter
import com.example.recipesapp.ui.base.BaseViewHolder
import com.example.recipesapp.utils.AppConstants

class RecipesAdapter(recipesItems : MutableList<Recipe>, private val recipesAdapterListener: RecipesAdapterListener) : BaseRecyclerViewAdapter<Recipe>(recipesItems) {

    override fun getItemViewType(position: Int): Int {
        return if (getItems().isNotEmpty()) AppConstants.VIEW_TYPE_NORMAL else AppConstants.VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            AppConstants.VIEW_TYPE_NORMAL -> RecipesViewHolder(
                ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> EmptyViewHolder(ItemEmptyRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
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

    inner class EmptyViewHolder(private val itemEmptyRecipeBinding : ItemEmptyRecipeBinding) : BaseViewHolder(itemEmptyRecipeBinding.root), RecipeEmptyItemViewModel.RecipeEmptyItemListener {

        override fun onBind(position: Int) {
            itemEmptyRecipeBinding.emptyItemViewModel = RecipeEmptyItemViewModel(this)
            itemEmptyRecipeBinding.executePendingBindings()
        }

        override fun onRetryClick() {
            recipesAdapterListener.onRetryClick()
        }

    }
}