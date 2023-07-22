package com.volokhinaleksey.androidcleanarchitecture.repositories.photos

import androidx.paging.PagingData
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    /**
     * A method for getting a list of photos from some data source
     * @param token - Key for authorization.
     * @param orderBy - How to sort the photos.
     */

    suspend fun getPhotos(
        token: String,
        orderBy: String
    ): Flow<PagingData<PhotoUI>>

}