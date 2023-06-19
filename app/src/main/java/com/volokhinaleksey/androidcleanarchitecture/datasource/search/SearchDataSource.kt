package com.volokhinaleksey.androidcleanarchitecture.datasource.search

import com.volokhinaleksey.androidcleanarchitecture.models.SearchPhotosDTO

interface SearchDataSource {

    suspend fun searchPhoto(token: String, query: String): SearchPhotosDTO

}