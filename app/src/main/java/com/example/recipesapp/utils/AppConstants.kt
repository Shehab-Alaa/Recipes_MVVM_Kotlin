package com.example.recipesapp.utils

import com.example.recipesapp.BuildConfig

object AppConstants {
    const val PREF_NAME = BuildConfig.APPLICATION_ID + "_pref"

    const val VIEW_TYPE_EMPTY = 0
    const val VIEW_TYPE_NORMAL = 1

    const val SELECTED_RECIPE = "selectedRecipe"

    const val SORTED_BY = "sorted_by"
    const val SORTED_BY_DEFAULT = "sorted_by_default"
    const val SORTED_BY_CALORIES = "sorted_by_calories"
    const val SORTED_BY_FATS = "sorted_by_fats"
    const val CALORIES = false
    const val FATS = true

    const val DATABASE_NAME = "favorite_recipes"
}