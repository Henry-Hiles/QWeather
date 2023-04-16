package com.henryhiles.qweather.domain.remote

import com.squareup.moshi.Json

data class GeocodingLocationDto(
    @field:Json(name = "name")
    val city: String,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "admin1")
    val admin: String,
    @field:Json(name = "latitude")
    val latitude: Float,
    @field:Json(name = "longitude")
    val longitude: Float
)
