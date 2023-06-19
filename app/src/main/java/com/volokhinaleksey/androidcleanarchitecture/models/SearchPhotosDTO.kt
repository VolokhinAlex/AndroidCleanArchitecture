package com.volokhinaleksey.androidcleanarchitecture.models

data class SearchPhotosDTO(
    val total: Long?,
    val totalPages: Int?,
    val results: List<PhotoDTO>
)
