package com.example.recipesapp.ui.main

import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.R
import com.example.recipesapp.databinding.ActivityMainBinding
import com.example.recipesapp.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModel{parametersOf(SavedStateHandle(mapOf()))}

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }

}