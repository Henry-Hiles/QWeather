package com.henryhiles.qweather.domain.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat

class LocationTracker constructor(
    private val application: Application
) {
    fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER
        )
        if (!hasAccessFineLocationPermission || !isGpsEnabled) return null

        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//        return suspendCancellableCoroutine { cont ->
//            locationManager.getLastKnownLocation.apply {
//                if (isComplete) {
//                    if (isSuccessful) {
//                        cont.resume(result)
//                    } else {
//                        cont.resume(null)
//                    }
//                    return@suspendCancellableCoroutine
//                }
//                addOnSuccessListener {
//                    cont.resume(it)
//                }
//                addOnFailureListener {
//                    cont.resume(null)
//                }
//                addOnCanceledListener {
//                    cont.cancel()
//                }
//            }
//        }
    }
}