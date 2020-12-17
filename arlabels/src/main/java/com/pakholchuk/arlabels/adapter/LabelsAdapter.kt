package com.pakholchuk.arlabels.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pakholchuk.arlabels.LabelProperties
import com.pakholchuk.arlabels.LocationData
import com.pakholchuk.arlabels.utils.ARLabelUtils.TAG

abstract class LabelsAdapter<VH : LabelViewHolder> {

    abstract fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup?): VH
    abstract fun onDrawLabel(viewHolder: VH, labelProperties: LabelProperties)
    abstract fun getLocationsList(): List<LocationData>
}