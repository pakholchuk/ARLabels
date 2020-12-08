package com.pakholchuk.arlabels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ARLabelsViewModel @Inject constructor(
    private val repository: CompassRepository,
    private val permissionManager: PermissionManager
) : ViewModel(), IARLabelsViewModel {

    override val permissionState: MutableLiveData<PermissionResult> = MutableLiveData()

    override val _compassUpdate = MutableLiveData<CompassData>()
    override val compassUpdate: LiveData<CompassData> = _compassUpdate

    override fun getUpdates() = viewModelScope.launch {
        repository.getCompassUpdates().collectLatest {
            _compassUpdate.postValue(it)
        }
    }

    override fun setARLabelData(list: List<ARLabelData>) {
        repository.labelDataList = list
    }

    override fun setLowPassFilterAlpha(lowPassFilterAlpha: Float) {
        repository.setLowPassFilterAlpha(lowPassFilterAlpha)
    }

    override fun checkPermissions() {
        if (permissionManager.areAllPermissionsGranted()) {
            permissionState.postValue(
                PermissionResult.GRANTED
            )
        } else permissionManager.requestAllPermissions()
    }

    override fun onRequestPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val permissionResult =
            permissionManager.getPermissionsRequestResult(requestCode, grantResults)
        permissionState.postValue(
            permissionResult
        )
    }

}