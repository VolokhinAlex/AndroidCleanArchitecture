package com.volokhinaleksey.androidcleanarchitecture.datasource.search

import com.volokhinaleksey.androidcleanarchitecture.models.SearchPhotosDTO

interface SearchDataSource {

    /**
     * A method for searching for photos on request.
     * @param token - Token for authorization on the server
     * @param query - The request for which you need to find a photo
     */

    suspend fun searchPhoto(token: String, query: String): SearchPhotosDTO

}