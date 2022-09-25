package com.android.spacex.api

import com.android.spacex.model.SpaceDetails

/**
 * Sealed classes for handling Api Response
 * ApiSuccess - For handling success response returned from API
 * ApiError - To handle error returned from API
 * ApiException - To handle any Exception in API call
 */
sealed interface NetworkResult {
    class ApiSuccess(val data: ArrayList<SpaceDetails>) : NetworkResult
    class ApiError(val code: Int) : NetworkResult
    class ApiException(val e: Throwable) : NetworkResult
}
