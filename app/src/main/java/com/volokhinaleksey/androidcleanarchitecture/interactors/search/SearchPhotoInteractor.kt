package com.volokhinaleksey.androidcleanarchitecture.interactors.search

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI

interface SearchPhotoInteractor {

    suspend fun searchPhoto(token: String, query: String): List<PhotoUI>

}