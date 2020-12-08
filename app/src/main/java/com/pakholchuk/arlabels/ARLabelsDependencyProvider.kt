package com.pakholchuk.arlabels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LifecycleOwner

interface ARLabelsDependencyProvider {
    fun getSensorsContext(): Context
    fun getARViewLifecycleOwner(): LifecycleOwner
    fun getPermissionActivity(): Activity
}
