package com.pakholchuk.arlabels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pakholchuk.arlabels.adapter.LabelsAdapter
import com.pakholchuk.arlabels.data.CompassRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
internal class ARLabelsViewModel @Inject constructor(
    private val repository: CompassRepository
) : ViewModel(), IARLabelsViewModel {

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
}