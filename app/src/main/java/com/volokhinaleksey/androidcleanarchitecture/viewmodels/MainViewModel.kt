package com.volokhinaleksey.androidcleanarchitecture.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.volokhinaleksey.androidcleanarchitecture.interactors.LaunchCounterInteractor
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.base.BaseViewModel

class MainViewModel(
    private val launchCounterInteractor: LaunchCounterInteractor
) : BaseViewModel<DataLaunchCount>() {

    private val _isShowingEvaluationWindow: MutableLiveData<Boolean> = MutableLiveData()
    val isShowingEvaluationWindow: LiveData<Boolean> get() = _isShowingEvaluationWindow

    fun saveLaunchCount(count: DataLaunchCount) {
        launchCounterInteractor.saveLaunchCount(dataLaunchCount = count)
    }

    fun getLaunchCount() {
        mutableData.value = launchCounterInteractor.getLaunchCount()
    }

    fun isShowingEvaluationWindow(count: Long) {
        _isShowingEvaluationWindow.value =
            launchCounterInteractor.isShowEvaluationWindow(value = count)
    }
}