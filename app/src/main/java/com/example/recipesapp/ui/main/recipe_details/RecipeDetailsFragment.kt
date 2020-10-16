package com.example.recipesapp.ui.main.recipe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.recipesapp.databinding.FragmentRecipeDetailsBinding
import com.example.recipesapp.BR
import com.example.recipesapp.R
import com.example.recipesapp.ui.base.BaseFragment
import com.example.recipesapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf


class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding, RecipeDetailsViewModel>() {

    private lateinit var recipeDetailsViewModel: RecipeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = RecipeDetailsFragmentArgs.fromBundle(requireArguments())
        recipeDetailsViewModel = getViewModel{ parametersOf(SavedStateHandle(mapOf(AppConstants.SELECTED_RECIPE to args.selectedRecipe)))}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initToolbar()
        return getRootView()
    }

    private fun initToolbar() {
        val layout = getViewDataBinding().collapsingToolbar
        val toolbar = getViewDataBinding().toolbar
        val navController = activity?.let { Navigation.findNavController(it,R.id.nav_host_fragment) }
        val appBarConfiguration = navController?.graph?.let { AppBarConfiguration.Builder(it).build() }
        if (navController != null) {
            if (appBarConfiguration != null) {
                NavigationUI.setupWithNavController(layout, toolbar, navController, appBarConfiguration)
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_recipe_details
    override val bindingVariable: Int
        get() = BR.recipeDetailsViewModel

    override fun getViewModel(): RecipeDetailsViewModel {
        return recipeDetailsViewModel
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

}