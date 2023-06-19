package com.volokhinaleksey.androidcleanarchitecture.repositories.search

import com.volokhinaleksey.androidcleanarchitecture.datasource.search.SearchDataSource
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.util.mapPhotoDTOToPhotoUI

class SearchPhotoRepositoryImpl(
    private val searchDataSource: SearchDataSource
) : SearchPhotoRepository {

    override suspend fun searchPhoto(token: String, query: String): List<PhotoUI> {
        return searchDataSource.searchPhoto(token = token, query = query).results.map {
            mapPhotoDTOToPhotoUI(photoDTO = it)
        }
    }

}