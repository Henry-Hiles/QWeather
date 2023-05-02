package com.henryhiles.qweather.domain.geocoding

import kotlinx.serialization.Serializable

@Serializable
data class GeocodingData(
    val location: String,
    val longitude: Float,
    val latitude: Float
)
