package com.volokhinaleksey.androidcleanarchitecture.interactors.search

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.repositories.search.SearchPhotoRepository

class SearchPhotoInteractorImpl(
    private val searchPhotoRepository: SearchPhotoRepository
) : SearchPhotoInteractor {

    /**
     * A method for searching for photos in some repository.
     * @param token - Token for authorization
     * @param query - The query for which you need to find a photo
     */

    override suspend fun searchPhoto(token: String, query: String): List<PhotoUI> {
        return if (query.isNotEmpty()) {
            searchPhotoRepository.searchPhoto(token = token, query = query)
        } else {
            listOf()
        }
    }

}