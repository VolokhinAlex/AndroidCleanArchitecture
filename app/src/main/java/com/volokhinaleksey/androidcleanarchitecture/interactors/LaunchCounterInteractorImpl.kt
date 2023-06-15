package com.volokhinaleksey.androidcleanarchitecture.interactors

import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.repositories.LaunchCounterRepository

class LaunchCounterInteractorImpl(
    private val repository: LaunchCounterRepository
) : LaunchCounterInteractor {

    override fun saveLaunchCount(dataLaunchCount: DataLaunchCount) {
        repository.saveLaunchCount(value = dataLaunchCount)
    }

    override fun getLaunchCount(): DataLaunchCount = repository.getLaunchCount()

    override fun isShowEvaluationWindow(value: Long): Boolean {
        return value == 2L || (value - 2L) % 4L == 0L
    }

}