package com.android.spacex.api.source.remote

import android.util.Log
import com.android.spacex.api.ApiService
import com.android.spacex.api.NetworkResult
import com.android.spacex.api.source.SpaceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class SpaceRepository(private val service: ApiService) : SpaceDataSource {

    /**
     * Network/Retrofit API call to fetch SpaceX Data
     */
    override suspend fun getSpaceData() = withContext(Dispatchers.IO) {
        try {
            val response = service.getSpaceData()
            Log.v("Response = ", "" + response.isSuccessful + response.body());
            if (response.isSuccessful) {
                NetworkResult.ApiSuccess(response.body() ?: ArrayList())
            } else {
                NetworkResult.ApiError(response.code())
            }
        } catch (e: HttpException) {
            NetworkResult.ApiError(e.code())
        } catch (e: Throwable) {
            NetworkResult.ApiException(e)
        }
    }
}