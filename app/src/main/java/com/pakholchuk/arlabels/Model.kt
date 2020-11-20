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

@Parcelize
data class LocationData(
        val latitude: Double,
        val longitude: Double
) : Parcelable

data class OrientationData(val currentAzimuth: Float, val currentPitch: Float)