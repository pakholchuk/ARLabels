package com.pakholchuk.arlabels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.netguru.arlocalizerview.PermissionResult
import kotlinx.coroutines.Job

interface IARLabelsViewModel {
    val permissionState: MutableLiveData<PermissionResult>
    val _compassUpdate: MutableLiveData<CompassData>
    val compassUpdate: LiveData<CompassData>
    fun getUpdates(): Job
    fun setARLabelData(list: List<ARLabelData>)
    fun setLowPassFilterAlpha(lowPassFilterAlpha: Float)
    fun checkPermissions()
    fun onRequestPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
}