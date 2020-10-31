package com.example.recipesapp.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recipe(

    val calories: String? ,

    @SerializedName("carbos")
    val carbos: String? ,

    @SerializedName("description")
    val description: String? ,

    @SerializedName("difficulty")
    val difficulty: Int? ,

    @SerializedName("fats")
    val fats: String? ,

    @SerializedName("headline")
    val headline: String? ,

    @SerializedName("id")
    val id: String? ,

    @SerializedName("image")
    val image: String? ,

    @SerializedName("name")
    val name: String? ,

    @SerializedName("proteins")
    val proteins: String? ,

    @SerializedName("thumb")
    val thumb: String? ,

    @SerializedName("time")
    val time: String? ,

    @SerializedName("country")
    val country: String? ) : Serializable