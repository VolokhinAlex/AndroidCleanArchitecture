package com.volokhinaleksey.androidcleanarchitecture.repositories.photos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.volokhinaleksey.androidcleanarchitecture.datasource.photos.PhotosDataSource
import com.volokhinaleksey.androidcleanarchitecture.datasource.photos.PhotosPagingSource
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.util.mapPhotoDTOToPhotoUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhotosRepositoryImpl(
    private val photosDataSource: PhotosDataSource
) : PhotosRepository {

    /**
     * A method for getting a list of photos from some data source
     * @param token - Key for authorization.
     * @param orderBy - How to sort the photos.
     */

    override suspend fun getPhotos(
        token: String,
        orderBy: String
    ): Flow<PagingData<PhotoUI>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PhotosPagingSource(
                    photosDataSource = photosDataSource,
                    orderBy = orderBy, token = token
                )
            }
        ).flow.map {
            it.map {
                mapPhotoDTOToPhotoUI(it)
            }
        }
    }
}