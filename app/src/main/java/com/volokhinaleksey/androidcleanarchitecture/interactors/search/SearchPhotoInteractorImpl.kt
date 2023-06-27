package com.volokhinaleksey.androidcleanarchitecture.interactors.search

import androidx.paging.PagingData
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.repositories.search.SearchPhotoRepository
import kotlinx.coroutines.flow.Flow

class SearchPhotoInteractorImpl(
    private val searchPhotoRepository: SearchPhotoRepository
) : SearchPhotoInteractor {

    /**
     * A method for searching for photos in some repository.
     * @param token - Token for authorization
     * @param query - The query for which you need to find a photo
     */

    override suspend fun searchPhoto(token: String, query: String): Flow<PagingData<PhotoUI>> {
        return searchPhotoRepository.searchPhoto(token = token, query = query)
    }

}