package com.example.recipesapp.ui.main.recipe

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.BR
import com.example.recipesapp.R
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.databinding.FragmentRecipesBinding
import com.example.recipesapp.ui.base.BaseFragment
import com.example.recipesapp.utils.AppConstants
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf


class RecipesFragment : BaseFragment<FragmentRecipesBinding, RecipesViewModel>() , RecipesAdapter.RecipesAdapterListener {

    private lateinit var recipesViewModel: RecipesViewModel
    private val recipesAdapter: RecipesAdapter = RecipesAdapter(mutableListOf(),this)
    private val sharedPreferences : SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get saved SortedBy from SharedPreferences
        val sortedBy = getSavedSortedRecipesChoice()
        // send saved SortedBy to ViewModel as default parameter
        recipesViewModel = getViewModel { parametersOf(SavedStateHandle(mapOf(AppConstants.SORTED_BY to sortedBy))) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initRecipesRecyclerView()

        // when switch Button (Clicked) ==> Send Choice to ViewModel to (Sort Recipes List & Change Live Data to make UI observe changes (Checked or Not))
        // then Save SortedBy choice (SharedPreference)
        getViewDataBinding().sortedBySwitch.setOnCheckedChangeListener { compoundButton , isChecked ->
            if (isChecked){
                getViewModel().sortRecipesBy(AppConstants.FATS)
                with(receiver = sharedPreferences.edit()){
                    putString(AppConstants.SORTED_BY , AppConstants.SORTED_BY_FATS)
                    apply()
                }
            }else{
                getViewModel().sortRecipesBy(AppConstants.CALORIES)
                with(receiver = sharedPreferences.edit()){
                    putString(AppConstants.SORTED_BY , AppConstants.SORTED_BY_CALORIES)
                    apply()
                }
            }
        }


        getViewDataBinding().recipeSearchBox.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // filter ViewModel recipes list according to search entered
                getViewModel().filterRecipesData(getViewDataBinding().recipeSearchBox.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

    }

    private fun getSavedSortedRecipesChoice() : Boolean {
        return when (sharedPreferences.getString(AppConstants.SORTED_BY , AppConstants.SORTED_BY_DEFAULT)){
            AppConstants.SORTED_BY_CALORIES -> AppConstants.CALORIES
            AppConstants.SORTED_BY_FATS -> AppConstants.FATS
            else -> {
                // hit Shared Preference For First Time (set SortedBy Carbohydrates as default)
                with(receiver = sharedPreferences.edit()){
                    putString(AppConstants.SORTED_BY , AppConstants.SORTED_BY_CALORIES)
                    apply()
                }
                AppConstants.CALORIES
            }
        }
    }

    private fun initRecipesRecyclerView(){
        getViewDataBinding().recipesRv.layoutManager = LinearLayoutManager(context)
        getViewDataBinding().recipesRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        getViewDataBinding().recipesRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        getViewDataBinding().recipesRv.adapter = recipesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main , menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_booksFragment_to_favoriteBooksFragment)
            getNavController().navigate(RecipesFragmentDirections.actionRecipesFragmentToFavoriteRecipesFragment())
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(view: View, item: Recipe) {
        getNavController().navigate(RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(item))
    }

    override fun onRetryClick() {
        getViewModel().fetchRecipes()
    }

    override val layoutId: Int
        get() = R.layout.fragment_recipes
    override val bindingVariable: Int
        get() = BR.recipesViewModel

    override fun getViewModel(): RecipesViewModel {
        return recipesViewModel
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }


}