package com.pakholchuk.arlabels

import androidx.lifecycle.LiveData
import com.pakholchuk.arlabels.adapter.LabelsAdapter
import kotlinx.coroutines.Job

internal interface IARLabelsViewModel {
    val compassUpdate: LiveData<CompassData>
    fun getUpdates(): Job
    fun setAdapter(adapter: LabelsAdapter<*>)
    fun setLowPassFilterAlpha(lowPassFilterAlpha: Float)
}