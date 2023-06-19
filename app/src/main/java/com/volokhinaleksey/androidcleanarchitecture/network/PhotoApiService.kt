package com.volokhinaleksey.androidcleanarchitecture.network

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoDTO
import com.volokhinaleksey.androidcleanarchitecture.models.SearchPhotosDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Interface for interacting with the Photo API
 */

interface PhotoApiService {

    /**
     * Method for getting a list of photos
     * @param token - Key for authorization on the server
     * @param page - Page number to retrieve.
     * @param perPage - Number of items per page.
     * @param orderBy - How to sort the photos.
     */

    @GET("photos")
    suspend fun getPhotos(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String
    ): List<PhotoDTO>

    @GET("search/photos")
    suspend fun searchPhoto(
        @Header("Authorization") token: String,
        @Query("query") query: String
    ): SearchPhotosDTO
}