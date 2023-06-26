package com.volokhinaleksey.androidcleanarchitecture.util

import com.volokhinaleksey.androidcleanarchitecture.models.PhotoDTO
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUrlsDTO
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUrlsUI

fun mapPhotoDTOToPhotoUI(photoDTO: PhotoDTO): PhotoUI {
    return PhotoUI(
        id = photoDTO.id.orEmpty(),
        createdAt = photoDTO.createdAt.orEmpty(),
        updatedAt = photoDTO.updatedAt.orEmpty(),
        width = photoDTO.width ?: 0,
        height = photoDTO.height ?: 0,
        color = photoDTO.color.orEmpty(),
        urls = mapPhotoUrlsDTOToPhotoUrlsUI(
            photoDTO.urls ?: PhotoUrlsDTO(
                raw = "",
                full = "",
                regular = "",
                small = "",
                thumb = ""
            )
        ),
        description = photoDTO.description.orEmpty(),
        likes = photoDTO.likes ?: 0L
    )
}

fun mapPhotoUrlsDTOToPhotoUrlsUI(photoUrlsDTO: PhotoUrlsDTO): PhotoUrlsUI {
    return PhotoUrlsUI(
        raw = photoUrlsDTO.raw.orEmpty(),
        full = photoUrlsDTO.full.orEmpty(),
        regular = photoUrlsDTO.regular.orEmpty(),
        small = photoUrlsDTO.small.orEmpty(),
        thumb = photoUrlsDTO.thumb.orEmpty()
    )
}

fun mapPhotoUIToPhotoDTO(photo: PhotoUI): PhotoDTO {
    return PhotoDTO(
        id = photo.id,
        createdAt = photo.createdAt,
        updatedAt = photo.updatedAt,
        width = photo.width,
        height = photo.height,
        color = photo.color,
        urls = mapPhotoUrlsUIToPhotoUrlsDTO(photo.urls),
        description = photo.description,
        likes = photo.likes
    )
}

fun mapPhotoUrlsUIToPhotoUrlsDTO(photoUrlsUI: PhotoUrlsUI): PhotoUrlsDTO {
    return PhotoUrlsDTO(
        raw = photoUrlsUI.raw,
        full = photoUrlsUI.full,
        regular = photoUrlsUI.regular,
        small = photoUrlsUI.small,
        thumb = photoUrlsUI.thumb
    )
}