package com.example.recipesapp.ui.main.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentSplashScreenBinding
import com.example.recipesapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding , SplashScreenViewModel>(){

    private val splashScreenViewModel: SplashScreenViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }
    private val splashTime = 3000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().iconImg.startAnimation(AnimationUtils.loadAnimation(context , R.anim.splash_animation))

        val r = Runnable {
            getNavController().navigate(R.id.action_splashScreenFragment_to_recipesFragment)
        }
        Handler().postDelayed(r, splashTime.toLong())
    }

    override val layoutId: Int
        get() = R.layout.fragment_splash_screen
    override val bindingVariable: Int
        get() = 0

    override fun getViewModel(): SplashScreenViewModel {
        return splashScreenViewModel
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

}