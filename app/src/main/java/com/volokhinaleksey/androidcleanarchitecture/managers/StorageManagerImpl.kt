package com.volokhinaleksey.androidcleanarchitecture.managers

import android.content.SharedPreferences

class StorageManagerImpl(
    private val sharedPreferences: SharedPreferences
) : StorageManager {

    /**
     * Method for saving numeric values to the device's local storage.
     * @param key - Key to save.
     * @param value - Value for saving.
     */

    override fun saveLong(key: String, value: Long) {
        sharedPreferences
            .edit()
            .putLong(key, value)
            .apply()
    }

    /**
     * Method for getting a numeric value from the device's local storage.
     * @param key - The key for getting a numeric value.
     * @param defaultValue - Standard numeric value.
     */

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

}