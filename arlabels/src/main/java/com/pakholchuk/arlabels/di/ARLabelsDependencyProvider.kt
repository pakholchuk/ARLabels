package com.pakholchuk.arlabels.di

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

interface ARLabelsDependencyProvider {
    fun getARViewLifecycleOwner(): LifecycleOwner
    fun getPermissionActivity(): AppCompatActivity
}
