package com.volokhinaleksey.androidcleanarchitecture.interactors

import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount

interface LaunchCounterInteractor {

    fun saveLaunchCount(dataLaunchCount: DataLaunchCount)

    fun getLaunchCount(): DataLaunchCount

    fun isShowEvaluationWindow(value: Long): Boolean
}