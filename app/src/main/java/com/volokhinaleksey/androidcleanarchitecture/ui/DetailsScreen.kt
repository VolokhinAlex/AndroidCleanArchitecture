package com.volokhinaleksey.androidcleanarchitecture.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.ui.image_loader.ImageLoader
import org.koin.compose.koinInject
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DetailsScreen(
    photoUI: PhotoUI,
    imageLoader: ImageLoader = koinInject()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    )
    {
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            // Image
            Box(modifier = Modifier.fillMaxSize(0.5f)) {
                imageLoader.LoadImage(
                    modifier = Modifier,
                    url = photoUI.urls.regular,
                    contentDescription = photoUI.description,
                    contentScale = ContentScale.Inside
                )
            }
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(text = "Photo created at:\n${formattedData(photoUI.createdAt)}", fontSize = 15.sp)
                Text(text = "Photo updated at:\n${formattedData(photoUI.updatedAt)}", fontSize = 15.sp)
            }
        }
        Text(text = "Likes: ${photoUI.likes}")
        Text(text = "Photo description: ${photoUI.description}")
    }

}

private fun formattedData(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy HH:mm:ss", Locale.getDefault())
    return inputFormat.parse(inputDate)?.let { outputFormat.format(it) }.orEmpty()
}