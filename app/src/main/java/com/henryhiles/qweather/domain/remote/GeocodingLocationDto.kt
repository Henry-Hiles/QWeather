package com.henryhiles.qweather.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingLocationDto(
    val name: String,
    val country: String,
    @SerialName("admin1")
    val admin: String? = null,
    val latitude: Float,
    val longitude: Float
)