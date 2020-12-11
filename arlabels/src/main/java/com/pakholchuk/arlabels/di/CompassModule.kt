package com.pakholchuk.arlabels.di

import android.hardware.SensorManager
import android.view.WindowManager
import androidx.core.content.getSystemService
import com.pakholchuk.arlabels.*
import com.pakholchuk.arlabels.data.LocationProvider
import com.pakholchuk.arlabels.data.OrientationProvider
import com.pakholchuk.arlabels.data.PermissionManager
import com.patloew.colocation.CoLocation
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class CompassModule {

    @Binds
    abstract fun provideViewModel(viewModel: ARLabelsViewModel): IARLabelsViewModel

    companion object {
        @JvmStatic
        @Provides
        fun providesPermissionManager(arLabelsDependencyProvider: ARLabelsDependencyProvider): PermissionManager {
            return PermissionManager(
                arLabelsDependencyProvider.getPermissionActivity()
            )
        }

        @Provides
        fun providesCoLocation(arLabelsDependencyProvider: ARLabelsDependencyProvider) =
            CoLocation.from(arLabelsDependencyProvider.getPermissionActivity())


        @JvmStatic
        @Provides
        fun providesLocationProvider(coLocation: CoLocation) = LocationProvider(coLocation)


        @JvmStatic
        @Provides
        internal fun provideOrientationProvider(
            sensorManager: SensorManager,
            windowManager: WindowManager
        ) = OrientationProvider(sensorManager, windowManager)


        @JvmStatic
        @Provides
        internal fun provideSensorManager(arLabelsDependencyProvider: ARLabelsDependencyProvider) =
            requireNotNull(
                arLabelsDependencyProvider.getPermissionActivity().getSystemService<SensorManager>()
            )


        @JvmStatic
        @Provides
        internal fun providesWindowManager(arLabelsDependencyProvider: ARLabelsDependencyProvider) =
            requireNotNull(
                arLabelsDependencyProvider.getPermissionActivity().getSystemService<WindowManager>()
            )
    }
}
