package com.henryhiles.qweather.domain.remote

import com.squareup.moshi.Json

data class GeocodingDto(
    @field:Json(name = "results")
    val results: List<GeocodingLocationDto>
)