package com.volokhinaleksey.androidcleanarchitecture.managers

interface StorageManager {

    fun saveLong(key: String, value: Long)

    fun getLong(key: String, defaultValue: Long): Long

}