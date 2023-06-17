package com.volokhinaleksey.androidcleanarchitecture.datasource.launch_count

import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount

interface LaunchCounterDataSource {

    /**
     * Method for saving the number of app launches
     * @param value - An object to save with the number of app launches.
     */

    fun saveLaunchCount(value: DataLaunchCount)

    /**
     * Method for getting the number of app launches.
     */

    fun getLaunchCount(): DataLaunchCount

}