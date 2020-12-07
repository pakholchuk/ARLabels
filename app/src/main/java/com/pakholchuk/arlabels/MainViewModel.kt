package com.pakholchuk.arlabels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor (private val repository: CompassRepository): ViewModel() {
    private val _compassUpdate = MutableLiveData<CompassData>()
    val compassUpdate: LiveData<CompassData> = _compassUpdate

    fun getUpdates() = viewModelScope.launch {
        repository.getCompassUpdates().collectLatest {
            _compassUpdate.postValue(it)
        }
    }

    fun setARLabelData(list: List<ARLabelData>) {
        repository.labelDataList = list
    }

    fun setLowPassFilterAlpha(lowPassFilterAlpha: Float) {
        repository.setLowPassFilterAlpha(lowPassFilterAlpha)
    }

}