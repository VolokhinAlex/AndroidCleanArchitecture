package com.volokhinaleksey.androidcleanarchitecture

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoUI
import com.volokhinaleksey.androidcleanarchitecture.ui.DetailsScreen
import com.volokhinaleksey.androidcleanarchitecture.ui.PhotosScreen
import com.volokhinaleksey.androidcleanarchitecture.ui.navigation.ScreenState
import com.volokhinaleksey.androidcleanarchitecture.ui.theme.AndroidCleanArchitectureTheme
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val TAG = "MainActivity"
const val PHOTO_DATA_KEY = "photo_data_key"

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getLaunchCount()
        setContent {
            AndroidCleanArchitectureTheme {
                val navController = rememberNavController()
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
                NavHost(
                    navController = navController,
                    startDestination = ScreenState.PhotosScreen.route,
                )  {
                    composable(route = ScreenState.PhotosScreen.route) {
                        PhotosScreen(navController = navController)
                    }
                    composable(route = ScreenState.DetailsScreen.route) {
                        val photoData = it.arguments?.parcelable<PhotoUI>(PHOTO_DATA_KEY)
                        photoData?.let { data ->
                            DetailsScreen(
                                photoUI = data
                            )
                        }
                    }
                }
            }
        }
    }

}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}