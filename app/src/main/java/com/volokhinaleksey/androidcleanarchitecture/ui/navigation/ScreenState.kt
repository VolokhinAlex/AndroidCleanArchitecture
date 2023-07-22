package com.volokhinaleksey.androidcleanarchitecture.ui.navigation

sealed class ScreenState(val route: String) {

    object PhotosScreen : ScreenState(route = "photos_screen")

    object DetailsScreen : ScreenState(route = "details_screen")

}
