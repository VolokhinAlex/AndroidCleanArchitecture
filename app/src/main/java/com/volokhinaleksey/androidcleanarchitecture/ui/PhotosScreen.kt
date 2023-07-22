package com.volokhinaleksey.androidcleanarchitecture.ui

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.volokhinaleksey.androidcleanarchitecture.PHOTO_DATA_KEY
import com.volokhinaleksey.androidcleanarchitecture.TAG
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.ui.image_loader.ImageLoader
import com.volokhinaleksey.androidcleanarchitecture.ui.navigation.ScreenState
import com.volokhinaleksey.androidcleanarchitecture.ui.navigation.navigate
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.MainViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(
    navController: NavController,
    mainViewModel: MainViewModel = koinViewModel(),
    imageLoader: ImageLoader = koinInject()
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(topBar = { SearchPhoto(mainViewModel = mainViewModel) }) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
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
                mainViewModel.photos.collectAsLazyPagingItems().let {
                    ShowPhotos(
                        photos = it,
                        imageLoader = imageLoader,
                        navController = navController
                    )
                }
            }
        }
    }

}

@Composable
private fun ShowPhotos(
    photos: LazyPagingItems<PhotoUI>,
    imageLoader: ImageLoader,
    navController: NavController
) {
    LazyColumn {
        itemsIndexed(photos) { _, photo ->
            Box(modifier = Modifier
                .padding(20.dp)
                .clickable {
                    navController.navigate(
                        ScreenState.DetailsScreen.route, bundleOf(
                            PHOTO_DATA_KEY to photo
                        )
                    )
                }) {
                imageLoader.LoadImage(
                    modifier = Modifier,
                    url = photo?.urls?.full.orEmpty(),
                    contentDescription = photo?.description.orEmpty(),
                    contentScale = ContentScale.Inside
                )
            }
        }
        item {
            photos.apply {
                when {
                    loadState.refresh is LoadState.Loading -> LoadingProgressBar()
                    loadState.append is LoadState.Loading -> LoadingProgressBar()
                    loadState.refresh is LoadState.Error -> {
                        val message = photos.loadState.refresh as LoadState.Error
                        Log.e(TAG, message.error.localizedMessage.orEmpty())
                    }

                    loadState.append is LoadState.Error -> {
                        val message = photos.loadState.append as LoadState.Error
                        Log.e(TAG, message.error.localizedMessage.orEmpty())
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SearchPhoto(mainViewModel: MainViewModel) {
    val state = rememberSearchState()
    SearchBar(
        query = state.query,
        onQueryChange = { state.query = it },
        onSearchFocusChange = { state.focused = it },
        onClearQuery = { state.query = TextFieldValue(text = "") },
        onBack = {
            state.query = TextFieldValue(text = "")
            mainViewModel.getPhotos()
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