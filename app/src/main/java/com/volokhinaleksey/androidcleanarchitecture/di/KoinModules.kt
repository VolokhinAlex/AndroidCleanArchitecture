package com.volokhinaleksey.androidcleanarchitecture.di

import android.content.SharedPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.androidcleanarchitecture.datasource.launch_count.LaunchCounterDataSource
import com.volokhinaleksey.androidcleanarchitecture.datasource.launch_count.LaunchCounterDataSourceImpl
import com.volokhinaleksey.androidcleanarchitecture.datasource.photos.PhotosDataSource
import com.volokhinaleksey.androidcleanarchitecture.datasource.photos.PhotosDataSourceImpl
import com.volokhinaleksey.androidcleanarchitecture.datasource.search.SearchDataSource
import com.volokhinaleksey.androidcleanarchitecture.datasource.search.SearchDataSourceImpl
import com.volokhinaleksey.androidcleanarchitecture.interactors.launch_count.LaunchCounterInteractor
import com.volokhinaleksey.androidcleanarchitecture.interactors.launch_count.LaunchCounterInteractorImpl
import com.volokhinaleksey.androidcleanarchitecture.interactors.photos.PhotosInteractor
import com.volokhinaleksey.androidcleanarchitecture.interactors.photos.PhotosInteractorImpl
import com.volokhinaleksey.androidcleanarchitecture.interactors.search.SearchPhotoInteractor
import com.volokhinaleksey.androidcleanarchitecture.interactors.search.SearchPhotoInteractorImpl
import com.volokhinaleksey.androidcleanarchitecture.managers.StorageManager
import com.volokhinaleksey.androidcleanarchitecture.managers.StorageManagerImpl
import com.volokhinaleksey.androidcleanarchitecture.network.ApiHolder
import com.volokhinaleksey.androidcleanarchitecture.network.PhotoApiService
import com.volokhinaleksey.androidcleanarchitecture.network.UnsplashPhotoApiHolder
import com.volokhinaleksey.androidcleanarchitecture.repositories.launch_count.LaunchCounterRepository
import com.volokhinaleksey.androidcleanarchitecture.repositories.launch_count.LaunchCounterRepositoryImpl
import com.volokhinaleksey.androidcleanarchitecture.repositories.photos.PhotosRepository
import com.volokhinaleksey.androidcleanarchitecture.repositories.photos.PhotosRepositoryImpl
import com.volokhinaleksey.androidcleanarchitecture.repositories.search.SearchPhotoRepository
import com.volokhinaleksey.androidcleanarchitecture.repositories.search.SearchPhotoRepositoryImpl
import com.volokhinaleksey.androidcleanarchitecture.ui.image_loader.CoilImageLoader
import com.volokhinaleksey.androidcleanarchitecture.ui.image_loader.ImageLoader
import com.volokhinaleksey.androidcleanarchitecture.util.CLEAN_ARCHITECTURE_APP
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Gson> {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
            .create(PhotoApiService::class.java)
    }
    single<ApiHolder> { UnsplashPhotoApiHolder(get()) }
}

val managersModule = module {
    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            CLEAN_ARCHITECTURE_APP,
            android.content.Context.MODE_PRIVATE
        )
    }
    single<StorageManager> { StorageManagerImpl(get()) }
}

val dataSourcesModule = module {
    single<LaunchCounterDataSource> { LaunchCounterDataSourceImpl(get()) }
    single<PhotosDataSource> { PhotosDataSourceImpl(get()) }
    single<SearchDataSource> { SearchDataSourceImpl(get()) }
}

val repositoriesModule = module {
    single<LaunchCounterRepository> { LaunchCounterRepositoryImpl(get()) }
    single<PhotosRepository> { PhotosRepositoryImpl(get()) }
    single<SearchPhotoRepository> { SearchPhotoRepositoryImpl(get()) }
}

val interactorsModule = module {
    factory<LaunchCounterInteractor> { LaunchCounterInteractorImpl(get()) }
    factory<PhotosInteractor> { PhotosInteractorImpl(get()) }
    factory<SearchPhotoInteractor> { SearchPhotoInteractorImpl(get()) }
}

val mainScreenModule = module {
    factory<ImageLoader> { CoilImageLoader() }
    factory { Dispatchers.IO }
    viewModel { MainViewModel(get(), get(), get(), get()) }
}