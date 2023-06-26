package com.volokhinaleksey.androidcleanarchitecture.datasource.photos

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoDTO
import com.volokhinaleksey.androidcleanarchitecture.network.ApiHolder

class PhotosDataSourceImpl(
    private val apiHolder: ApiHolder
) : PhotosDataSource {

    /**
     * Method for getting a list of photos from a remote data source on the server
     * @param token - Key for authorization.
     * @param page - Page number to retrieve.
     * @param perPage - Number of items per page.
     * @param orderBy - How to sort the photos.
     */

    override suspend fun getPhotos(
        token: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): List<PhotoDTO> {
        return apiHolder.photosApiService.getPhotos(
            token = token,
            page = page,
            perPage = perPage,
            orderBy = orderBy
        )
    }

}