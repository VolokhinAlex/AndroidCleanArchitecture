package com.volokhinaleksey.androidcleanarchitecture.network

/**
 * Implementation of the interface for working with unsplash photo api service.
 * The retrofit object itself is created automatically using dependency injection.
 */

class UnsplashPhotoApiHolder(override val photosApiService: PhotoApiService) : ApiHolder