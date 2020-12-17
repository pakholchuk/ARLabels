package com.pakholchuk.arlabels.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

interface ARLabelsDependencyProvider {
    fun getARViewLifecycleOwner(): LifecycleOwner
    fun getPermissionActivity(): AppCompatActivity
}
