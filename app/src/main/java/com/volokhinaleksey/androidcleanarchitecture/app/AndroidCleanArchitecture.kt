package com.volokhinaleksey.androidcleanarchitecture.app

import android.app.Application
import com.volokhinaleksey.androidcleanarchitecture.di.dataSourcesModule
import com.volokhinaleksey.androidcleanarchitecture.di.interactorsModule
import com.volokhinaleksey.androidcleanarchitecture.di.mainScreenModule
import com.volokhinaleksey.androidcleanarchitecture.di.managersModule
import com.volokhinaleksey.androidcleanarchitecture.di.networkModule
import com.volokhinaleksey.androidcleanarchitecture.di.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidCleanArchitecture : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidCleanArchitecture)
            modules(
                listOf(
                    networkModule,
                    managersModule,
                    dataSourcesModule,
                    repositoriesModule,
                    interactorsModule,
                    mainScreenModule,
                )
            )
        }
    }

}