package com.android.spacex.api

import com.android.spacex.model.SpaceDetails
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("launches")
    suspend fun getSpaceData(): Response<ArrayList<SpaceDetails>>
}