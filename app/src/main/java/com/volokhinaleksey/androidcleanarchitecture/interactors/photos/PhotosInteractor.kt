package com.volokhinaleksey.androidcleanarchitecture.interactors.photos

import androidx.paging.PagingData
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import kotlinx.coroutines.flow.Flow

interface PhotosInteractor {

    /**
     * Method for getting a list of photos
     * @param token - Key for authorization.
     * @param orderBy - How to sort the photos.
     */

    suspend fun getPhotos(token: String, orderBy: String): Flow<PagingData<PhotoUI>>

}