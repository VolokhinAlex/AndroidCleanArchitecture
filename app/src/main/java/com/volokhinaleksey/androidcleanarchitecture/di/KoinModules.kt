package com.volokhinaleksey.androidcleanarchitecture.di

import android.content.SharedPreferences
import com.volokhinaleksey.androidcleanarchitecture.datasource.LaunchCounterDataSource
import com.volokhinaleksey.androidcleanarchitecture.datasource.LaunchCounterDataSourceImpl
import com.volokhinaleksey.androidcleanarchitecture.interactors.LaunchCounterInteractor
import com.volokhinaleksey.androidcleanarchitecture.interactors.LaunchCounterInteractorImpl
import com.volokhinaleksey.androidcleanarchitecture.managers.StorageManager
import com.volokhinaleksey.androidcleanarchitecture.managers.StorageManagerImpl
import com.volokhinaleksey.androidcleanarchitecture.repositories.LaunchCounterRepository
import com.volokhinaleksey.androidcleanarchitecture.repositories.LaunchCounterRepositoryImpl
import com.volokhinaleksey.androidcleanarchitecture.util.CLEAN_ARCHITECTURE_APP
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

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
}

val repositoriesModule = module {
    single<LaunchCounterRepository> { LaunchCounterRepositoryImpl(get()) }
}

val interactorsModule = module {
    single<LaunchCounterInteractor> { LaunchCounterInteractorImpl(get()) }
}

val mainScreenModule = module {
    viewModel { MainViewModel(get()) }
}