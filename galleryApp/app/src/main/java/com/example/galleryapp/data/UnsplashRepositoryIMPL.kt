package com.example.galleryapp.data

import com.example.galleryapp.domain.UnsplashRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UnsplashRepositoryIMPL(private val api: IUnsplashAPI) : UnsplashRepository {

    override suspend fun getPhotos(): Flow<StandardResponse<List<ResponseModelClass>>> = flow {
        emit(StandardResponse.Loading)
        val resp = api.getPhotos()
        try {
            if (resp.isSuccessful && resp.body() != null) {
                emit(StandardResponse.Success(resp.body()!!))
            } else {
                emit(StandardResponse.Failure)
            }
        } catch (e: Exception) {
            emit(StandardResponse.Failure)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getRandom(): Flow<StandardResponse<List<ResponseModelClass>>> = flow {
        emit(StandardResponse.Loading)
        val resp = api.getRandom()
        try {
            if (resp.isSuccessful && resp.body() != null) {
                emit(StandardResponse.Success(resp.body()!!))
            } else {
                emit(StandardResponse.Failure)
            }
        } catch (e: Exception) {
            emit(StandardResponse.Failure)
        }
    }.flowOn(Dispatchers.IO)
}