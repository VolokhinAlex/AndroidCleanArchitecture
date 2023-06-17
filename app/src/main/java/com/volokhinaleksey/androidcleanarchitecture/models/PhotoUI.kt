package com.volokhinaleksey.androidcleanarchitecture.models

data class PhotoUI(
    val id: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val color: String = "",
    val description: String = "",
    val urls: PhotoUrlsUI = PhotoUrlsUI(),
    val likes: Long = 0L,
)
