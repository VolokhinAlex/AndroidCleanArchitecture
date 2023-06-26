package com.volokhinaleksey.androidcleanarchitecture.repositories.photos

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI

interface PhotosRepository {

    /**
     * A method for getting a list of photos from some data source
     * @param token - Key for authorization.
     * @param page - Page number to retrieve.
     * @param perPage - Number of items per page.
     * @param orderBy - How to sort the photos.
     */

    suspend fun getPhotos(token: String, page: Int, perPage: Int, orderBy: String): List<PhotoUI>

}