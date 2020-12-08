package com.pakholchuk.arlabels.di

import com.pakholchuk.arlabels.IARLabelsViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        CompassModule::class
    ]
)
internal interface ARLabelsComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance arLabelsDependencyProvider: ARLabelsDependencyProvider): ARLabelsComponent
    }

    fun arLabelsViewModel(): IARLabelsViewModel
    fun arLabelsDependencyProvider(): ARLabelsDependencyProvider
}
