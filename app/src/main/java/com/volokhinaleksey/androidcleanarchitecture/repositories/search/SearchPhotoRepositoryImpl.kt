package com.volokhinaleksey.androidcleanarchitecture.repositories.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.volokhinaleksey.androidcleanarchitecture.datasource.search.SearchDataSource
import com.volokhinaleksey.androidcleanarchitecture.datasource.search.SearchPagingSource
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.util.mapPhotoDTOToPhotoUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchPhotoRepositoryImpl(
    private val searchDataSource: SearchDataSource
) : SearchPhotoRepository {

    /**
     * A method for searching for photos in some data source.
     * @param token - Token for authorization
     * @param query - The query for which you need to find a photo
     */

    override suspend fun searchPhoto(token: String, query: String): Flow<PagingData<PhotoUI>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    searchDataSource = searchDataSource,
                    query = query,
                    token = token
                )
            }
        ).flow.map {
            it.map {
                mapPhotoDTOToPhotoUI(photoDTO = it)
            }
        }
    }

}