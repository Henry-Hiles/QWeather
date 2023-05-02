package com.henryhiles.qweather.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingLocationDto(
    @SerialName("name")
    val city: String,
    val country: String,
    @SerialName("admin1")
    val admin: String,
    val latitude: Float,
    val longitude: Float
)
