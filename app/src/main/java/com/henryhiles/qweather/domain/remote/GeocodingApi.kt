package com.henryhiles.qweather.domain.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {
    @GET("v1/search?count=10")
    suspend fun getGeocodingData(
        @Query("name") location: String,
    ): GeocodingDto
}