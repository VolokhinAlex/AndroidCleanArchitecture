package com.volokhinaleksey.androidcleanarchitecture.interactors

import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.repositories.LaunchCounterRepository

class LaunchCounterInteractorImpl(
    private val repository: LaunchCounterRepository
) : LaunchCounterInteractor {

    /**
     * A method for saving the number of runs from the repository.
     * @param dataLaunchCount - The object is the number of runs to save.
     */

    override fun saveLaunchCount(dataLaunchCount: DataLaunchCount) {
        repository.saveLaunchCount(launchCount = dataLaunchCount)
    }

    /**
     * Method for getting the number of runs from the repository.
     */

    override fun getLaunchCount(): DataLaunchCount = repository.getLaunchCount()

    /**
     * A method that returns whether the application evaluation window should be displayed or not, depending on the number of launches.
     * If the value is 2, or every 4 starts after 2. i.e. show a window for 2, 6, 10, 14, etc. launches.
     * @param launchCount - Number of application launches
     */

    override fun isShowEvaluationWindow(launchCount: Long): Boolean {
        return launchCount == 2L || (launchCount - 2L) % 4L == 0L
    }

}