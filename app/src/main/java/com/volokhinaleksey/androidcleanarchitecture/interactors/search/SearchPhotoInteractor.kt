package com.volokhinaleksey.androidcleanarchitecture.interactors.search

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI

interface SearchPhotoInteractor {

    /**
     * A method for searching for photos in some repository.
     * @param token - Token for authorization
     * @param query - The query for which you need to find a photo
     */

    suspend fun searchPhoto(token: String, query: String): List<PhotoUI>

}