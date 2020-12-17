package com.pakholchuk.arlabels

import androidx.lifecycle.LiveData
import com.pakholchuk.arlabels.adapter.LabelsAdapter

internal interface IARLabelsViewModel {
    val compassUpdate: LiveData<CompassData>
    fun getUpdates()
    fun setAdapter(adapter: LabelsAdapter<*>)
    fun setLowPassFilterAlpha(lowPassFilterAlpha: Float)
}