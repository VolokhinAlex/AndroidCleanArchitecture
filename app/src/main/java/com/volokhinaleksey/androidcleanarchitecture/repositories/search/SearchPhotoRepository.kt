package com.volokhinaleksey.androidcleanarchitecture.repositories.search

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI

interface SearchPhotoRepository {

    suspend fun searchPhoto(token: String, query: String): List<PhotoUI>

}