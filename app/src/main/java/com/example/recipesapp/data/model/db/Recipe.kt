package com.example.recipesapp.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Recipe : Serializable {

    private val calories: String? = null

    @SerializedName("carbos")
    private val carbos: String? = null

    @SerializedName("description")
    private val description: String? = null

    @SerializedName("difficulty")
    private val difficulty: Int? = null

    @SerializedName("fats")
    private val fats: String? = null

    @SerializedName("headline")
    private val headline: String? = null

    @SerializedName("id")
    private val id: String? = null

    @SerializedName("image")
    private val image: String? = null

    @SerializedName("name")
    private val name: String? = null

    @SerializedName("proteins")
    private val proteins: String? = null

    @SerializedName("thumb")
    private val thumb: String? = null

    @SerializedName("time")
    private val time: String? = null

    @SerializedName("country")
    private val country: String? = null

    fun getRecipeThumbnail() : String? { return thumb }
    fun getRecipeName() : String? { return name }
    fun getRecipeImage() : String? { return  image }
    fun getRecipeDescription() : String? { return description }
    fun getRecipeCalories() : String? { return calories}
    fun getRecipeProteins() : String? { return proteins }
    fun getRecipeCarbohydrates() : String? { return carbos }
    fun getRecipeFats() : String? { return fats }



}