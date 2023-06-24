package com.volokhinaleksey.androidcleanarchitecture.viewmodels.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    protected val mutableData: MutableLiveData<T> = MutableLiveData()
    val data: LiveData<T> get() = mutableData

}