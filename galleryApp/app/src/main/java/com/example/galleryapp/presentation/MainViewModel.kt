package com.example.galleryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.galleryapp.data.IUnsplashAPI
import com.example.galleryapp.data.ResponseModelClass
import com.example.galleryapp.data.RetrofitService
import com.example.galleryapp.data.StandardResponse
import com.example.galleryapp.data.UnsplashRepositoryIMPL
import com.example.galleryapp.domain.UnsplashRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val repo: UnsplashRepository) : ViewModel() {

    private val _photosList = MutableStateFlow<List<ResponseModelClass>>(emptyList())
    val photosList: StateFlow<List<ResponseModelClass>> get() = _photosList.asStateFlow()

    private val _randomList = MutableStateFlow<List<ResponseModelClass>>(emptyList())
    val randomList: StateFlow<List<ResponseModelClass>> get() = _randomList.asStateFlow()

    fun getPhotos() {
        GlobalScope.launch(Dispatchers.Main) {
            repo.getPhotos().collectLatest {
                when (val data = it) {
                    StandardResponse.Failure -> {}
                    StandardResponse.Loading -> {}
                    is StandardResponse.Success -> {
                        _photosList.value = data.data
                    }
                }
            }
        }
    }

    fun getRandom() {
        GlobalScope.launch(Dispatchers.Main) {
            repo.getRandom().collectLatest {
                when (val data = it) {
                    StandardResponse.Failure -> {}
                    StandardResponse.Loading -> {}
                    is StandardResponse.Success -> {
                        _randomList.value = data.data
                    }
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return MainViewModel(
                    UnsplashRepositoryIMPL(
                        RetrofitService.getClient()?.create(IUnsplashAPI::class.java)!!
                    )
                ) as T
            }
        }
    }
}