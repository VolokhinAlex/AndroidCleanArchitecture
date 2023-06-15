package com.volokhinaleksey.androidcleanarchitecture.repositories

import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount

interface LaunchCounterRepository {

    fun saveLaunchCount(value: DataLaunchCount)

    fun getLaunchCount(): DataLaunchCount

}