package com.volokhinaleksey.androidcleanarchitecture.managers

import android.content.SharedPreferences

class StorageManagerImpl(
    private val sharedPreferences: SharedPreferences
) : StorageManager {

    override fun saveLong(key: String, value: Long) {
        sharedPreferences
            .edit()
            .putLong(key, value)
            .apply()
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

}