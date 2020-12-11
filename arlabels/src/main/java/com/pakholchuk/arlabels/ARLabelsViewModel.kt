package com.pakholchuk.arlabels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pakholchuk.arlabels.adapter.LabelsAdapter
import com.pakholchuk.arlabels.data.CompassRepository
import com.pakholchuk.arlabels.data.PermissionManager
import com.pakholchuk.arlabels.data.PermissionResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
internal class ARLabelsViewModel @Inject constructor(
    private val repository: CompassRepository,
    private val permissionManager: PermissionManager
) : ViewModel(), IARLabelsViewModel {

    private val _permissionState = MutableLiveData<PermissionResult>()
    override val permissionState: LiveData<PermissionResult> = _permissionState

    private val _compassUpdate = MutableLiveData<CompassData>()
    override val compassUpdate: LiveData<CompassData> = _compassUpdate

    override fun getUpdates() = viewModelScope.launch {
        repository.getCompassUpdates().collectLatest {
            _compassUpdate.postValue(it)
        }
    }

    override fun setAdapter(adapter: LabelsAdapter<*>) {
        repository.adapter = adapter
    }

    override fun setLowPassFilterAlpha(lowPassFilterAlpha: Float) {
        repository.setLowPassFilterAlpha(lowPassFilterAlpha)
    }

    override fun checkPermissions() {
        if (permissionManager.areAllPermissionsGranted()) {
            _permissionState.postValue(
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
        _permissionState.postValue(
            permissionResult
        )
    }

}