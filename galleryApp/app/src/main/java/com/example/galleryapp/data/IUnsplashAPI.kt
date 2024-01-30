package com.example.galleryapp.data

import com.example.galleryapp.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IUnsplashAPI {

    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") id: String = Constants.ACCESS_KEY,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 100
    ): Response<List<ResponseModelClass>>

    @GET("photos/random")
    suspend fun getRandom(
        @Query("client_id") id: String = Constants.ACCESS_KEY,
        @Query("count") count: Int = 100
    ): Response<List<ResponseModelClass>>
}