package com.volokhinaleksey.androidcleanarchitecture.models

/**
 *
 * An object for storing links to a photo of various sizes and of various quality
 *
 * @param raw - Link to the raw photo
 * @param full - Link to the full photo
 * @param regular - Link to a regular photo
 * @param small - Link to a small photo
 * @param thumb - Link to a reduced to small size copy of the photo
 *
 */

data class PhotoUrlsDTO(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?,
)