package com.volokhinaleksey.androidcleanarchitecture.repositories

import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount

interface LaunchCounterRepository {

    /**
     * A method for saving the number of runs to the data source.
     * @param launchCount - Number of app launches to save
     */

    fun saveLaunchCount(launchCount: DataLaunchCount)

    /**
     * A method for getting the number of runs from a data source.
     */

    fun getLaunchCount(): DataLaunchCount

}