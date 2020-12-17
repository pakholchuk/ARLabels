package com.pakholchuk.arlabels

data class LabelProperties(
    val id: String,
    val distance: Int,
    val positionX: Int,
    val positionY: Int,
    val alpha: Int,
)

data class LocationData(
    val id: String,
    val latitude: Double,
    val longitude: Double
)

data class OrientationData(val currentAzimuth: Float, val currentPitch: Float)

data class CompassData(
    val orientationData: OrientationData,
    val destinations: List<DestinationData>,
    val maxDistance: Int,
    val minDistance: Int,
    val currentLocation: LocationData,
)

data class DestinationData(
    val id: String,
    val currentDestinationAzimuth: Float,
    val distanceToDestination: Int,
    val destinationLocation: LocationData,
)
