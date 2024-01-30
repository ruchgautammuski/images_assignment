package com.example.galleryapp.domain

import com.example.galleryapp.data.ResponseModelClass
import com.example.galleryapp.data.StandardResponse
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {
    suspend fun getPhotos(): Flow<StandardResponse<List<ResponseModelClass>>>
    suspend fun getRandom(): Flow<StandardResponse<List<ResponseModelClass>>>
}