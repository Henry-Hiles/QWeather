package com.henryhiles.qweather.domain.mappers

import com.henryhiles.qweather.domain.remote.GeocodingDto
import com.henryhiles.qweather.domain.geocoding.GeocodingData

fun GeocodingDto.toGeocodingData(): List<GeocodingData> {
    return results.map {
        GeocodingData(
            location = "${if(it.name == it.country) "" else "${it.name}, "}${if(it.admin == null) "" else "${it.admin}, "}${it.country}",
            longitude = it.longitude,
            latitude = it.latitude,
        )
    }
}