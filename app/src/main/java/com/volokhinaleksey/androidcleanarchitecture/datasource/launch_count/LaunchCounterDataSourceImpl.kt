package com.volokhinaleksey.androidcleanarchitecture.datasource.launch_count

import com.volokhinaleksey.androidcleanarchitecture.managers.StorageManager
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.util.DEFAULT_LAUNCH_COUNT
import com.volokhinaleksey.androidcleanarchitecture.util.LAUNCH_COUNT

class LaunchCounterDataSourceImpl(
    private val storageManager: StorageManager
) : LaunchCounterDataSource {

    /**
     * A method for saving the number of app launches to the device's local storage.
     * @param value - Object to save with the number of app launches
     */

    override fun saveLaunchCount(value: DataLaunchCount) {
        storageManager.saveLong(key = LAUNCH_COUNT, value = value.launchCount)
    }

    /**
     * A method for getting the number of app launches of their local device storage.
     */

    override fun getLaunchCount(): DataLaunchCount {
        return DataLaunchCount(
            launchCount = storageManager.getLong(
                key = LAUNCH_COUNT,
                defaultValue = DEFAULT_LAUNCH_COUNT
            )
        )
    }

}