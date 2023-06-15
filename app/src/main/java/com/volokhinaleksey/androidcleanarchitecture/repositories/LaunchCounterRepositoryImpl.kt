package com.volokhinaleksey.androidcleanarchitecture.repositories

import com.volokhinaleksey.androidcleanarchitecture.datasource.LaunchCounterDataSource
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount

class LaunchCounterRepositoryImpl(
    private val launchCounterDataSource: LaunchCounterDataSource
) : LaunchCounterRepository {

    override fun saveLaunchCount(value: DataLaunchCount) {
        launchCounterDataSource.saveLaunchCount(value = value)
    }

    override fun getLaunchCount(): DataLaunchCount = launchCounterDataSource.getLaunchCount()

}