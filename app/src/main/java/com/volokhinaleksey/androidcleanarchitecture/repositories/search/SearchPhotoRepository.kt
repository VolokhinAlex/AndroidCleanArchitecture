package com.volokhinaleksey.androidcleanarchitecture.repositories.search

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI

interface SearchPhotoRepository {

    /**
     * A method for searching for photos in some data source.
     * @param token - Token for authorization
     * @param query - The query for which you need to find a photo
     */

    suspend fun searchPhoto(token: String, query: String): List<PhotoUI>

}