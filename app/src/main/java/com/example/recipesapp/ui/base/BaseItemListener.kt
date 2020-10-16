package com.example.recipesapp.ui.base

import android.view.View

interface BaseItemListener<T> {
    fun onItemClick(view: View, item: T)
}