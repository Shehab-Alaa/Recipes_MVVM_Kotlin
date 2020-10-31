package com.example.recipesapp.data.model

sealed class Result<T>(
     val data: T? = null,
     val errorMessage: String? = null,
     val statusCode: Int? = null
) {
     class Success<T>(data: T) : Result<T>(data)
     class Error<T>(errorMessage: String?,statusCode: Int? = null) : Result<T>(null , errorMessage , statusCode)
}