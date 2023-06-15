package com.volokhinaleksey.androidcleanarchitecture.datasource

import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount

interface LaunchCounterDataSource {

    fun saveLaunchCount(value: DataLaunchCount)

    fun getLaunchCount(): DataLaunchCount

}