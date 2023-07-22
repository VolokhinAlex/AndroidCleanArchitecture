package com.volokhinaleksey.androidcleanarchitecture.ui.image_loader

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

interface ImageLoader {

    /**
     * Method for getting and inserting an image into a container
     *
     * @param modifier - An object of the Modifier class for configuring a composable element
     * @param url - Image url to get it
     * @param contentDescription - Picture Description
     * @param contentScale - Represents a rule to apply to scale a source rectangle to be inscribed into a destination
     */

    @Composable
    fun LoadImage(
        modifier: Modifier,
        url: String,
        contentDescription: String,
        contentScale: ContentScale
    )

}