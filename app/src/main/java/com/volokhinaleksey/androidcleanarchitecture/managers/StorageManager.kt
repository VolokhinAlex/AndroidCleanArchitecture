package com.volokhinaleksey.androidcleanarchitecture.managers

interface StorageManager {

    /**
     * A method for storing numeric values.
     * @param key - The key to save.
     * @param value - The value to save.
     */

    fun saveLong(key: String, value: Long)

    /**
     * A method for getting a numeric value.
     * @param key - The key for getting a numeric value.
     * @param defaultValue - Standard numeric value.
     */

    fun getLong(key: String, defaultValue: Long): Long

}