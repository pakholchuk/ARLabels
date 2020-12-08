package com.pakholchuk.arlabels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@ExperimentalCoroutinesApi
class CompassRepository @Inject constructor(
    private val orientationProvider: OrientationProvider,
    private val locationProvider: LocationProvider
) {

    companion object {
        private const val MAXIMUM_ANGLE = 360
    }

    var labelDataList: List<ARLabelData> = listOf()

    fun getCompassUpdates(): Flow<CompassData> =
        orientationProvider.getSensorUpdates()
            .combine(locationProvider.getLocationUpdates()) {
                currentOrientation: OrientationData, currentLocation: LocationData ->
                val destinations = labelDataList
                    .map {
                        unitPointToDestination(
                            it.point,
                            currentLocation,
                            currentOrientation.currentAzimuth
                        )
                    }

                CompassData(
                    currentOrientation,
                    destinations,
                    getMaxDistance(destinations),
                    getMinDistance(destinations),
                    currentLocation,
                    labelDataList
                )
            }


    private fun unitPointToDestination(
        point: Point,
        currentLocation: LocationData,
        currentAzimuth: Float
    ): DestinationData {
        val headingAngle = calculateHeadingAngle(currentLocation, point.location)
        val currentDestinationAzimuth =
            (headingAngle - currentAzimuth + MAXIMUM_ANGLE) % MAXIMUM_ANGLE
        val distanceToDestination = locationProvider.getDistanceBetweenPoints(
            currentLocation,
            point.location
        )
        return DestinationData(
            currentDestinationAzimuth,
            distanceToDestination,
            point.location,
            point
        )
    }

    private fun calculateHeadingAngle(currentLocation: LocationData, destinationLocation: LocationData): Float {
        val currentLatitudeRadians = Math.toRadians(currentLocation.latitude)
        val destinationLatitudeRadians = Math.toRadians(destinationLocation.latitude)
        val deltaLongitude = Math.toRadians(destinationLocation.longitude - currentLocation.longitude)

        val y = cos(currentLatitudeRadians) * sin(destinationLatitudeRadians) -
                sin(currentLatitudeRadians) * cos(destinationLatitudeRadians) * cos(deltaLongitude)
        val x = sin(deltaLongitude) * cos(destinationLatitudeRadians)
        val headingAngle = Math.toDegrees(atan2(x, y)).toFloat()

        return (headingAngle + MAXIMUM_ANGLE) % MAXIMUM_ANGLE
    }

    private fun getMaxDistance(destinations: List<DestinationData>) =
        destinations.maxByOrNull { it.distanceToDestination }
            ?.distanceToDestination ?: 0

    private fun getMinDistance(destinations: List<DestinationData>) =
        destinations.minByOrNull { it.distanceToDestination }
            ?.distanceToDestination ?: 0

    fun setLowPassFilterAlpha(lowPassFilterAlpha: Float) {
        orientationProvider.setLowPassFilterAlpha(lowPassFilterAlpha)
    }
}