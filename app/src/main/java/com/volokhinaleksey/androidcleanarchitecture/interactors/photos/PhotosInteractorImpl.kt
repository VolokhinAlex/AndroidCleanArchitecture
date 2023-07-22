package com.volokhinaleksey.androidcleanarchitecture.interactors.photos

import androidx.paging.PagingData
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.repositories.photos.PhotosRepository
import kotlinx.coroutines.flow.Flow

class PhotosInteractorImpl(
    private val photosRepository: PhotosRepository
) : PhotosInteractor {

    /**
     * Method for getting a list of photos
     * @param token - Key for authorization.
     * @param orderBy - How to sort the photos.
     */

    override suspend fun getPhotos(
        token: String,
        orderBy: String
    ): Flow<PagingData<PhotoUI>> {
        return photosRepository.getPhotos(
            token = token,
            orderBy = orderBy
        )
    }

}