package com.android.spacex.api.source

import com.android.spacex.api.NetworkResult

interface SpaceDataSource {
    suspend fun getSpaceData(): NetworkResult
}