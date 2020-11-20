package com.pakholchuk.arlabels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class LabelProperties(
    val distance: Int,
    val positionX: Int,
    val positionY: Int,
    val alpha: Int,
    val title: String = "",
    val unitId: String = ""
)

data class UnitPoint(
    val id: String,
    val name: String,
    val location: LocationData,
)

@Parcelize
data class LocationData(
        val latitude: Double,
        val longitude: Double
) : Parcelable

data class OrientationData(val currentAzimuth: Float, val currentPitch: Float)

data class CompassData(
    val orientationData: OrientationData,
    val destinations: List<DestinationData>,
    val maxDistance: Int,
    val minDistance: Int,
    val currentLocation: LocationData
)

data class DestinationData(
    val currentDestinationAzimuth: Float,
    val distanceToDestination: Int,
    val destinationLocation: LocationData,
    val destinationUnit: UnitPoint
)