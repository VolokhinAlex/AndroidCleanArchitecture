package com.volokhinaleksey.androidcleanarchitecture.models

/**
 * Wrapper object for retrieving data using search
 * @param total - Number of search results found
 * @param totalPages - Number of pages found on request
 * @param results - Search result
 */

data class SearchPhotosDTO(
    val total: Long?,
    val totalPages: Int?,
    val results: List<PhotoDTO>
)
