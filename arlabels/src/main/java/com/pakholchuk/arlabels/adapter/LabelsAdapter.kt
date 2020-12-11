package com.pakholchuk.arlabels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pakholchuk.arlabels.LocationData

abstract class LabelsAdapter<VH : LabelViewHolder> {
    abstract fun createViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup?): VH
    abstract fun getItemCount(): Int
    abstract fun getLocationData(listPosition: Int): LocationData
}