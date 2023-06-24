package com.volokhinaleksey.androidcleanarchitecture.repositories

import com.volokhinaleksey.androidcleanarchitecture.datasource.LaunchCounterDataSource
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount

class LaunchCounterRepositoryImpl(
    private val launchCounterDataSource: LaunchCounterDataSource
) : LaunchCounterRepository {

    /**
     * Method for saving the number of runs to the data source
     * @param launchCount - Number of app launches to save
     */

    override fun saveLaunchCount(launchCount: DataLaunchCount) {
        launchCounterDataSource.saveLaunchCount(value = launchCount)
    }

    /**
     * A method for getting the number of runs from a data source.
     */

    override fun getLaunchCount(): DataLaunchCount = launchCounterDataSource.getLaunchCount()

}