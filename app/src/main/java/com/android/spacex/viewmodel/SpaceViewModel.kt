package com.android.spacex.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.spacex.Utils
import com.android.spacex.api.NetworkResult
import com.android.spacex.api.source.SpaceDataSource
import com.android.spacex.model.SpaceDetails
import kotlinx.coroutines.launch

/**
 * Shared ViewModel to bind views across fragments
 */
class SpaceViewModel(
    private val application: Application,
    private val repository: SpaceDataSource
) : ViewModel() {

    /**
     * LiveData to hold selected Record
     */
    val selectedSpaceX: MutableLiveData<SpaceDetails> = MutableLiveData()

    /**
     * LiveData to store SpaceX records fetched from Network
     */

    var spacexMutableData = MutableLiveData<List<SpaceDetails>?>()

    /**
     * To show error message if data not fetched
     */
    var showErrorMessage = MutableLiveData<Boolean>()

    /**
     *  To Bind with UI to display Loading Indicator
     */
    var showProgressBar = MutableLiveData<Int>(View.GONE)

    fun registerForSpacexDetails() = spacexMutableData

    init {
        showErrorMessage.postValue(false)
        fetchSpacexData()
    }

    fun fetchSpacexData() {
        if (!spacexMutableData.value.isNullOrEmpty()) return
        if (!Utils.isNetworkConnected(application)) {
            // No internet connected
            showErrorMessage.postValue(true)
            showProgressBar.postValue(View.GONE)
            return
        }
        viewModelScope.launch {
            showProgressBar.postValue(View.VISIBLE)
            val result = repository.getSpaceData()
            showProgressBar.postValue(View.GONE)

            when (result) {
                is NetworkResult.ApiSuccess -> {
                    // we received success response
                    if (!result.data.isEmpty()) {
                        showErrorMessage.postValue(false)
                        spacexMutableData.postValue(result.data)
                    } else {
                        showErrorMessage.postValue(true)
                    }
                }
                is NetworkResult.ApiError,
                is NetworkResult.ApiException -> {
                    showErrorMessage.postValue(true)
                }
            }
        }
    }

    fun updateSelectedSpaceX(spaceDetails: SpaceDetails) {
        selectedSpaceX.value = spaceDetails
    }
}