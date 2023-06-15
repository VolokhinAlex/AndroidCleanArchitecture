package com.volokhinaleksey.androidcleanarchitecture.datasource

import com.volokhinaleksey.androidcleanarchitecture.managers.StorageManager
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.util.DEFAULT_LAUNCH_COUNT
import com.volokhinaleksey.androidcleanarchitecture.util.LAUNCH_COUNT

class LaunchCounterDataSourceImpl(
    private val storageManager: StorageManager
) : LaunchCounterDataSource {

    override fun saveLaunchCount(value: DataLaunchCount) {
        storageManager.saveLong(key = LAUNCH_COUNT, value = value.launchCount)
    }

    override fun getLaunchCount(): DataLaunchCount {
        return DataLaunchCount(
            launchCount = storageManager.getLong(
                key = LAUNCH_COUNT,
                defaultValue = DEFAULT_LAUNCH_COUNT
            )
        )
    }

}