package com.example.recipesapp.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.bumptech.glide.Glide
import com.example.recipesapp.data.model.db.Recipe
import com.example.recipesapp.ui.base.BaseRecyclerViewAdapter


object BindingAdaptersUtils {

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:recyclerViewAdapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items:MutableList <T>?) {
        items?.let { (recyclerView.adapter as BaseRecyclerViewAdapter<T>?)?.addItems(it) }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:imageSrc")
    fun setImageViewSrc(imageVew: ImageView , imagePath : String?){
        Glide.with(imageVew.context)
            .load(imagePath)
            .into(imageVew);
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:pieChartData")
    fun setUpPieChart(anyChartView: AnyChartView , recipe: Recipe){
        // draw pie chart by these details from recipe
        var carbohydrates = 1
        var fats = 1
        var proteins = 1

        if (recipe.getRecipeCarbohydrates()?.isNotEmpty()!!)
           carbohydrates = recipe.getRecipeCarbohydrates()?.trim()?.get(0)?.toInt()!!
        if (recipe.getRecipeFats()?.isNotEmpty()!!)
           fats = recipe.getRecipeFats()?.trim()?.get(0)?.toInt()!!
        if (recipe.getRecipeProteins()?.isNotEmpty()!!)
           proteins = recipe.getRecipeProteins()?.trim()?.get(0)?.toInt()!!

        val dataEntries : MutableList<DataEntry> = mutableListOf()
        dataEntries.add(ValueDataEntry("Carbohydrates" , carbohydrates))
        dataEntries.add(ValueDataEntry("Fats" , fats))
        dataEntries.add(ValueDataEntry("Proteins" , proteins))

        val pieChart = AnyChart.pie()
        pieChart.data(dataEntries)
        anyChartView.setChart(pieChart)
    }

    @SuppressLint("SetTextI18n")
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:sortedByText")
    fun setSortedByText(textView: TextView, checked : Boolean){
        if (checked){
            textView.text = "Sorted By: Fats"
        }else{
            textView.text = "Sorted By: Calories"
        }
    }

}