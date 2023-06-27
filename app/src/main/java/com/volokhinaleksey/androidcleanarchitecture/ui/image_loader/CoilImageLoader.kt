package com.volokhinaleksey.androidcleanarchitecture.ui.image_loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.volokhinaleksey.androidcleanarchitecture.R

class CoilImageLoader : ImageLoader {

    /**
     * The method downloads an image from the network and inserts it into a composable element
     *
     * @param modifier - An object of the Modifier class for configuring a composable element
     * @param url - Image url to get it
     * @param contentDescription - Picture Description
     * @param contentScale - Represents a rule to apply to scale a source rectangle to be inscribed into a destination
     */

    @Composable
    override fun LoadImage(
        modifier: Modifier,
        url: String,
        contentDescription: String,
        contentScale: ContentScale
    ) {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxWidth(),
            contentScale = contentScale,
            loading = {
                Box(
                    modifier = Modifier.background(Color.Gray).height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_image_search_24),
                        contentDescription = "placeholder"
                    )
                }
            }
        )
    }

}