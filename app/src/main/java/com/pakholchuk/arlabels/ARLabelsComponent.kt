package com.pakholchuk.arlabels

import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        CompassModule::class
    ]
)
interface ARLabelsComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance arLabelsDependencyProvider: ARLabelsDependencyProvider): ARLabelsComponent
    }

    fun arLabelsViewModel(): IARLabelsViewModel
    fun arLabelsDependencyProvider(): ARLabelsDependencyProvider
}
