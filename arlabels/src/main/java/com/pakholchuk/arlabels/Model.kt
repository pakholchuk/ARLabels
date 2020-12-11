package com.pakholchuk.arlabels

data class LabelProperties(
    val distance: Int,
    val positionX: Int,
    val positionY: Int,
    val alpha: Int,
)

data class LocationData(
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
    val currentDestinationAzimuth: Float,
    val distanceToDestination: Int,
    val destinationLocation: LocationData,
)
