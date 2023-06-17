package com.volokhinaleksey.androidcleanarchitecture.repositories.photos

import com.volokhinaleksey.androidcleanarchitecture.datasource.photos.PhotosDataSource
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.util.mapPhotoDTOToPhotoUI

class PhotosRepositoryImpl(
    private val photosDataSource: PhotosDataSource
) : PhotosRepository {

    /**
     * A method for getting a list of photos from some data source
     * @param page - Page number to retrieve.
     * @param perPage - Number of items per page.
     * @param orderBy - How to sort the photos.
     */

    override suspend fun getPhotos(
        token: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): List<PhotoUI> {
        return photosDataSource.getPhotos(
            token = token,
            page = page,
            perPage = perPage,
            orderBy = orderBy
        ).map {
            mapPhotoDTOToPhotoUI(it)
        }
    }

}