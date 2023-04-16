package com.henryhiles.qweather.domain.repository

import com.henryhiles.qweather.domain.remote.GeocodingApi
import com.henryhiles.qweather.domain.remote.GeocodingLocationDto
import com.henryhiles.qweather.domain.util.Resource

class GeocodingRepository(private val api: GeocodingApi) {
    suspend fun getGeocodingData(location: String): Resource<List<GeocodingLocationDto>> {
        return try {
            Resource.Success(
                data = api.getGeocodingData(location = location).results
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}