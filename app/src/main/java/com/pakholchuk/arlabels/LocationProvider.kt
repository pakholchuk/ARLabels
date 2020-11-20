package com.pakholchuk.arlabels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationRequest
import com.patloew.colocation.CoLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlin.math.roundToInt

@SuppressLint("MissingPermission")
class LocationProvider(private val context: Context){
    companion object {
        private const val LOCATION_REQUEST_INTERVAL = 5000L
        private const val FASTEST_REQUEST_INTERVAL = 20L
        private const val SMALLEST_DISPLACEMENT_NOTICED = 1f
    }

    private val locationRequest = LocationRequest().apply {
        interval = LOCATION_REQUEST_INTERVAL
        fastestInterval = FASTEST_REQUEST_INTERVAL
        smallestDisplacement = SMALLEST_DISPLACEMENT_NOTICED
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    fun getDistanceBetweenPoints(currentLocation: LocationData?,
                                 destinationLocation: LocationData?): Int {
        val locationA = Location("A")
        locationA.latitude = currentLocation?.latitude ?: 0.0
        locationA.longitude = currentLocation?.longitude ?: 0.0

        val locationB = Location("B")
        locationB.latitude = destinationLocation?.latitude ?: 0.0
        locationB.longitude = destinationLocation?.longitude ?: 0.0

        return locationA.distanceTo(locationB).roundToInt()
    }

    fun getLocationUpdates(): Flow<LocationData> =
        CoLocation.from(context)
            .getLocationUpdates(locationRequest)
            .map {
                LocationData(it.latitude, it.longitude)
            }
}