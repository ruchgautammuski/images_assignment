package com.example.galleryapp.data

sealed class StandardResponse<out T> {
    class Success<T>(val data: T) : StandardResponse<T>()
    data object Loading : StandardResponse<Nothing>()
    data object Failure : StandardResponse<Nothing>()
}