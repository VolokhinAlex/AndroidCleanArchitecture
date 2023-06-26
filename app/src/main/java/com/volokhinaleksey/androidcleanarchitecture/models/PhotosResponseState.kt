package com.volokhinaleksey.androidcleanarchitecture.models

sealed interface PhotosResponseState {

    object Loading : PhotosResponseState
    data class Error(val message: String) : PhotosResponseState
    data class Success(val photos: List<PhotoUI>) : PhotosResponseState

}
