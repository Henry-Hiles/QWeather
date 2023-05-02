package com.henryhiles.qweather.domain.repository

import com.henryhiles.qweather.domain.mappers.toGeocodingData
import com.henryhiles.qweather.domain.remote.GeocodingApi
import com.henryhiles.qweather.domain.util.Resource
import com.henryhiles.qweather.domain.geocoding.GeocodingData

class GeocodingRepository(private val api: GeocodingApi) {
    suspend fun getGeocodingData(location: String): Resource<List<GeocodingData>> {
        return try {
            Resource.Success(
                data = api.getGeocodingData(location = location).toGeocodingData()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}