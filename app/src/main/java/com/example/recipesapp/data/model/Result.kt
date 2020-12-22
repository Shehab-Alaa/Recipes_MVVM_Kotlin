package com.example.recipesapp.data.model

sealed class Result<T>(
     var data: T? = null,
     val errorMessage: String? = null
) {
     class Success<T>(data: T) : Result<T>(data)
     class Error<T>(errorMessage: String?) : Result<T>(null , errorMessage)
}