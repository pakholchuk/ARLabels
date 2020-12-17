package com.pakholchuk.arlabels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pakholchuk.arlabels.LabelProperties
import com.pakholchuk.arlabels.LocationData

abstract class LabelsAdapter<VH : LabelViewHolder> {

    abstract fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup?): VH
    abstract fun onDrawLabel(viewHolder: VH, labelProperties: LabelProperties)
    abstract fun getLocationsList(): List<LocationData>
}