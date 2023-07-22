package com.volokhinaleksey.androidcleanarchitecture.models

import androidx.paging.PagingData

sealed interface PhotosResponseState {

    object Loading : PhotosResponseState
    data class Error(val message: String) : PhotosResponseState
    data class Success(val photos: PagingData<PhotoUI>) : PhotosResponseState

}
