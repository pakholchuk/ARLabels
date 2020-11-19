package com.pakholchuk.arlabels

data class LabelProperties(
    val distance: Int,
    val positionX: Int,
    val positionY: Int,
    val alpha: Int,
    val title: String,
    val unitId: String = ""
)