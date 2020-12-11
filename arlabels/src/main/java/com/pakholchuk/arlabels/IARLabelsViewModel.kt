package com.pakholchuk.arlabels

import androidx.lifecycle.LiveData
import com.pakholchuk.arlabels.adapter.LabelsAdapter
import com.pakholchuk.arlabels.data.PermissionResult
import kotlinx.coroutines.Job

internal interface IARLabelsViewModel {
    val permissionState: LiveData<PermissionResult>
    val compassUpdate: LiveData<CompassData>
    fun getUpdates(): Job
    fun setAdapter(adapter: LabelsAdapter<*>)
    fun setLowPassFilterAlpha(lowPassFilterAlpha: Float)
    fun checkPermissions()
    fun onRequestPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
}