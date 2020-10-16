package com.example.recipesapp.ui.main.favorite

import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.databinding.FragmentFavoriteRecipesBinding
import com.example.recipesapp.BR
import com.example.recipesapp.R
import com.example.recipesapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class FavoriteRecipesFragment : BaseFragment<FragmentFavoriteRecipesBinding, FavoriteRecipesViewModel>() {

    private val favoriteRecipesViewModel: FavoriteRecipesViewModel by viewModel{parametersOf(SavedStateHandle(mapOf()))}

    override val layoutId: Int
        get() = R.layout.fragment_favorite_recipes
    override val bindingVariable: Int
        get() = BR.favoriteRecipesViewModel

    override fun getViewModel(): FavoriteRecipesViewModel {
        return favoriteRecipesViewModel
    }

}