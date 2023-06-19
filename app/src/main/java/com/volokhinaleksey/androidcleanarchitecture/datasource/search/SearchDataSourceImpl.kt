package com.volokhinaleksey.androidcleanarchitecture.datasource.search

import com.volokhinaleksey.androidcleanarchitecture.models.SearchPhotosDTO
import com.volokhinaleksey.androidcleanarchitecture.network.ApiHolder

class SearchDataSourceImpl(
    private val apiHolder: ApiHolder
) : SearchDataSource {

    override suspend fun searchPhoto(token: String, query: String): SearchPhotosDTO {
        return apiHolder.photosApiService.searchPhoto(token = token, query = query)
    }

}