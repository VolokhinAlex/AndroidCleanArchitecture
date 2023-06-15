package com.volokhinaleksey.androidcleanarchitecture.interactors

import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount

interface LaunchCounterInteractor {

    /**
     * A method for saving the number of launches.
     * @param dataLaunchCount - The object is the number of runs to save.
     */

    fun saveLaunchCount(dataLaunchCount: DataLaunchCount)

    /**
     * Method for getting the number of launches.
     */

    fun getLaunchCount(): DataLaunchCount

    /**
     * A method that returns whether the application evaluation window should be shown or not based on the number of launches.
     * @param launchCount - Number of app launches
     */

    fun isShowEvaluationWindow(launchCount: Long): Boolean
}