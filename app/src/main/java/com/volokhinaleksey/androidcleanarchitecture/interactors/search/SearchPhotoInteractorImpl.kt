package com.volokhinaleksey.androidcleanarchitecture.interactors.search

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.repositories.search.SearchPhotoRepository

class SearchPhotoInteractorImpl(
    private val searchPhotoRepository: SearchPhotoRepository
) : SearchPhotoInteractor {

    override suspend fun searchPhoto(token: String, query: String): List<PhotoUI> {
        return if (query.isNotEmpty()) {
            searchPhotoRepository.searchPhoto(token = token, query = query)
        } else {
            listOf()
        }
    }

}