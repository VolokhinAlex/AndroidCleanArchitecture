package com.volokhinaleksey.androidcleanarchitecture.models

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

