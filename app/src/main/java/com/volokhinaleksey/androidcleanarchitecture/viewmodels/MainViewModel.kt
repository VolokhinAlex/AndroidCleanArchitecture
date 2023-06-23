package com.volokhinaleksey.androidcleanarchitecture.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.volokhinaleksey.androidcleanarchitecture.BuildConfig
import com.volokhinaleksey.androidcleanarchitecture.interactors.launch_count.LaunchCounterInteractor
import com.volokhinaleksey.androidcleanarchitecture.interactors.photos.PhotosInteractor
import com.volokhinaleksey.androidcleanarchitecture.interactors.search.SearchPhotoInteractor
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.models.PhotosResponseState
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel(
    private val launchCounterInteractor: LaunchCounterInteractor,
    private val photosInteractor: PhotosInteractor,
    private val searchPhotoInteractor: SearchPhotoInteractor,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel<DataLaunchCount>() {

    private val _isShowingEvaluationWindow: MutableLiveData<Boolean> = MutableLiveData()
    val isShowingEvaluationWindow: LiveData<Boolean> get() = _isShowingEvaluationWindow

    private val _photos: MutableLiveData<PhotosResponseState> = MutableLiveData()
    val photos: LiveData<PhotosResponseState> get() = _photos

    init {
        getPhotos(page = 1, perPage = 100)
    }

    /**
     * Method for saving the number of app launches
     * @param count - Number of app launches
     */

    fun saveLaunchCount(count: DataLaunchCount) {
        launchCounterInteractor.saveLaunchCount(dataLaunchCount = count)
    }

    /**
     * Method for getting the number of app launches
     */

    fun getLaunchCount() {
        mutableData.value = launchCounterInteractor.getLaunchCount()
    }

    /**
     * Method for determining whether to show the application evaluation window or not
     * @param count - The number of app launches to check whether the evaluation window needs to be shown
     */

    fun isShowingEvaluationWindow(count: Long) {
        _isShowingEvaluationWindow.value =
            launchCounterInteractor.isShowEvaluationWindow(launchCount = count)
    }

    /**
     * Method for getting a list of photos
     * @param page - The page to get
     * @param perPage - Number of items per page
     * @param orderBy - Sort the receipt of photos by some request
     */

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

    /**
     * Method for searching photos by request
     * @param query - The request for which you want to get a list of photos.
     */

    fun searchPhoto(query: String) {
        _photos.value = PhotosResponseState.Loading
        viewModelScope.launch(dispatcher + CoroutineExceptionHandler { _, throwable ->
            _photos.postValue(PhotosResponseState.Error(throwable.localizedMessage.orEmpty()))
        }) {
            val searchResult = searchPhotoInteractor.searchPhoto(
                token = "Client-ID ${BuildConfig.PHOTOS_API_KEY}",
                query = query
            )
            _photos.postValue(PhotosResponseState.Success(photos = searchResult))
        }
    }

}