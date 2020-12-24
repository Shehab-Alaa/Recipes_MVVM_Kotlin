package com.example.recipesapp.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.BR
import com.example.recipesapp.R
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.databinding.FragmentFavoriteRecipesBinding
import com.example.recipesapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class FavoriteRecipesFragment : BaseFragment<FragmentFavoriteRecipesBinding, FavoriteRecipesViewModel>(),
    FavoriteRecipesAdapter.RecipesAdapterListener {

    private val favoriteRecipesViewModel: FavoriteRecipesViewModel by viewModel{parametersOf(SavedStateHandle(mapOf()))}
    private val favoriteRecipesAdapter : FavoriteRecipesAdapter = FavoriteRecipesAdapter(mutableListOf() , this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initToolbar()
        return getRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingView()
        initRecipesRecyclerView()
        getViewModel().favoriteRecipesLiveData.observe(this , {
            handleRecipesDateView(it)
        })
    }

    private fun initToolbar() {
        val toolbar = getViewDataBinding().toolbar
        val navController = activity?.let { Navigation.findNavController(it,R.id.nav_host_fragment) }
        val appBarConfiguration = navController?.graph?.let { AppBarConfiguration.Builder(it).build() }
        if (navController != null) {
            if (appBarConfiguration != null) {
                NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
            }
        }
    }

    private fun initRecipesRecyclerView(){
        getViewDataBinding().favoriteRecipesRv.layoutManager = LinearLayoutManager(context)
        getViewDataBinding().favoriteRecipesRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        getViewDataBinding().favoriteRecipesRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        getViewDataBinding().favoriteRecipesRv.adapter = favoriteRecipesAdapter
    }

    private fun handleRecipesDateView(recipesList: MutableList<Recipe>) {
        if (recipesList.isNullOrEmpty())
            showDataView(false)
        else
            showDataView(true)
    }

    private fun showLoadingView(){
        getViewDataBinding().recipesLoadingLayout.visibility = View.VISIBLE
        getViewDataBinding().recipesSuccessLayout.visibility = View.GONE
        getViewDataBinding().recipesEmptyLayout.visibility = View.GONE
    }

    private fun showDataView(showData: Boolean) {
        getViewDataBinding().recipesEmptyLayout.visibility = if (showData) View.GONE else View.VISIBLE
        getViewDataBinding().recipesSuccessLayout.visibility = if (showData) View.VISIBLE else View.GONE
        getViewDataBinding().recipesLoadingLayout.visibility = View.GONE
    }

    override fun onItemClick(view: View, item: Recipe) {
        getNavController().navigate(FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToRecipeDetailsFragment(item))
    }

    override val layoutId: Int
        get() = R.layout.fragment_favorite_recipes
    override val bindingVariable: Int
        get() = BR.favoriteRecipesViewModel

    override fun getViewModel(): FavoriteRecipesViewModel {
        return favoriteRecipesViewModel
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }
}