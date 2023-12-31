package com.volokhinaleksey.androidcleanarchitecture.models

/**
 *
 * Object for storing photo data for API
 *
 * @param id - Photo ID
 * @param createdAt - Date the photo was created
 * @param updatedAt - Photo update date
 * @param width - Photo width
 * @param height - Photo height
 * @param color - Photo Color
 * @param description - Description of the photo
 * @param urls - An object for storing links to a photo in different qualities and sizes
 * @param likes - The number of likes on the photo
 *
 */

data class PhotoDTO(
    val id: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val width: Int?,
    val height: Int?,
    val color: String?,
    val description: String?,
    val urls: PhotoUrlsDTO?,
    val likes: Long?,
)

