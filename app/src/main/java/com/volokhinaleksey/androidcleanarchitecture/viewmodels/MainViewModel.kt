package com.volokhinaleksey.androidcleanarchitecture.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.volokhinaleksey.androidcleanarchitecture.BuildConfig
import com.volokhinaleksey.androidcleanarchitecture.interactors.launch_count.LaunchCounterInteractor
import com.volokhinaleksey.androidcleanarchitecture.interactors.photos.PhotosInteractor
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.models.PhotosResponseState
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel(
    private val launchCounterInteractor: LaunchCounterInteractor,
    private val photosInteractor: PhotosInteractor,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel<DataLaunchCount>() {

    private val _isShowingEvaluationWindow: MutableLiveData<Boolean> = MutableLiveData()
    val isShowingEvaluationWindow: LiveData<Boolean> get() = _isShowingEvaluationWindow

    private val _photos: MutableLiveData<PhotosResponseState> = MutableLiveData()
    val photos: LiveData<PhotosResponseState> get() = _photos

    fun saveLaunchCount(count: DataLaunchCount) {
        launchCounterInteractor.saveLaunchCount(dataLaunchCount = count)
    }

    fun getLaunchCount() {
        mutableData.value = launchCounterInteractor.getLaunchCount()
    }

    fun isShowingEvaluationWindow(count: Long) {
        _isShowingEvaluationWindow.value =
            launchCounterInteractor.isShowEvaluationWindow(launchCount = count)
    }

    fun getPhotos(page: Int, perPage: Int, orderBy: String = "") {
        _photos.value = PhotosResponseState.Loading
        viewModelScope.launch(dispatcher + CoroutineExceptionHandler { _, throwable ->
            _photos.postValue(PhotosResponseState.Error(throwable.localizedMessage.orEmpty()))
        }) {
            val responseResult = photosInteractor.getPhotos(
                token = "Client-ID ${BuildConfig.PHOTOS_API_KEY}",
                page = page,
                perPage = perPage,
                orderBy = orderBy
            )
            _photos.postValue(PhotosResponseState.Success(photos = responseResult))
        }
    }

}