package com.volokhinaleksey.androidcleanarchitecture.datasource.search

import com.volokhinaleksey.androidcleanarchitecture.models.SearchPhotosDTO
import com.volokhinaleksey.androidcleanarchitecture.network.ApiHolder

class SearchDataSourceImpl(
    private val apiHolder: ApiHolder
) : SearchDataSource {

    /**
     * A method for searching for photos on request.
     * @param token - Token for authorization on the server
     * @param query - The request for which you need to find a photo
     */

    override suspend fun searchPhoto(
        token: String,
        query: String,
        page: Int,
        perPage: Int
    ): SearchPhotosDTO {
        return apiHolder.photosApiService.searchPhoto(
            token = token, query = query,
            page = page,
            perPage = perPage
        )
    }

}