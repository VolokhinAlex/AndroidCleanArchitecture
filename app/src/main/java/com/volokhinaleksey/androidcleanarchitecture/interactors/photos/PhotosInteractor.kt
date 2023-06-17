package com.volokhinaleksey.androidcleanarchitecture.interactors.photos

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI

interface PhotosInteractor {

    /**
     * Method for getting a list of photos
     * @param token - Key for authorization.
     * @param page - Page number to retrieve.
     * @param perPage - Number of items per page.
     * @param orderBy - How to sort the photos.
     */

    suspend fun getPhotos(token: String, page: Int, perPage: Int, orderBy: String): List<PhotoUI>

}