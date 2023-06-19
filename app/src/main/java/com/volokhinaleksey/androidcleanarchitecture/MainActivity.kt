package com.volokhinaleksey.androidcleanarchitecture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.models.PhotosResponseState
import com.volokhinaleksey.androidcleanarchitecture.ui.SearchBar
import com.volokhinaleksey.androidcleanarchitecture.ui.image_loader.ImageLoader
import com.volokhinaleksey.androidcleanarchitecture.ui.rememberSearchState
import com.volokhinaleksey.androidcleanarchitecture.ui.theme.AndroidCleanArchitectureTheme
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.MainViewModel
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by inject()

    private val imageLoader: ImageLoader by inject()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getLaunchCount()
        mainViewModel.getPhotos(page = 1, perPage = 100)
        setContent {
            AndroidCleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(topBar = { SearchPhoto() }) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            mainViewModel.data.observeAsState().value?.let {
                                Log.e("launchCount", it.launchCount.toString())
                                LaunchedEffect(key1 = true) {
                                    mainViewModel.isShowingEvaluationWindow(it.launchCount)
                                }
                                if (savedInstanceState == null) {
                                    LaunchedEffect(key1 = true) {
                                        val count = it.launchCount + 1
                                        mainViewModel.saveLaunchCount(count = DataLaunchCount(count))
                                    }
                                }
                            }
                            mainViewModel.isShowingEvaluationWindow.observeAsState().value?.let {
                                if (it) {
                                    Log.e(
                                        "SHOW REVIEW",
                                        "You need to display the application evaluation window"
                                    )
                                } else {
                                    Log.e(
                                        "SHOW REVIEW",
                                        "You don't need to show the evaluation window"
                                    )
                                }
                            }
                            mainViewModel.photos.observeAsState().value?.let {
                                RenderPhotosState(state = it)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RenderPhotosState(state: PhotosResponseState) {
        when (state) {
            is PhotosResponseState.Error -> Log.e(TAG, state.message)
            PhotosResponseState.Loading -> Log.d(TAG, "Loading...")
            is PhotosResponseState.Success -> {
                val photos = state.photos
                Log.d(TAG, "Response Success, Result - $photos")
                ShowPhotos(photos)
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun SearchPhoto() {
        val state = rememberSearchState()
        SearchBar(
            query = state.query,
            onQueryChange = { state.query = it },
            onSearchFocusChange = { state.focused = it },
            onClearQuery = { state.query = TextFieldValue(text = "") },
            onBack = {
                state.query = TextFieldValue(text = "")
                mainViewModel.getPhotos(page = 1, perPage = 100)
            },
            searching = state.searching,
            focused = state.focused,
            modifier = Modifier
        )
        LaunchedEffect(state.query.text) {
            state.searching = true
            delay(300)
            if (state.focused) {
                mainViewModel.searchPhoto(query = state.query.text)
            }
            state.searching = false
        }
    }

    @Composable
    private fun ShowPhotos(photos: List<PhotoUI>) {
        LazyColumn {
            itemsIndexed(photos) { _, photo ->
                Box(modifier = Modifier.padding(20.dp)) {
                    imageLoader.LoadImage(
                        modifier = Modifier,
                        url = photo.urls.full,
                        contentDescription = photo.description,
                        contentScale = ContentScale.Inside
                    )
                }
            }
        }
    }

}

@Composable
fun Greeting(text: String) {
    Text(
        text = text,
        modifier = Modifier
    )
}