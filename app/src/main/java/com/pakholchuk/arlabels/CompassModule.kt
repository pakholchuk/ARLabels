package com.pakholchuk.arlabels

import android.hardware.SensorManager
import android.view.WindowManager
import androidx.core.content.getSystemService
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
            CoLocation.from(arLabelsDependencyProvider.getSensorsContext())


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
                arLabelsDependencyProvider.getSensorsContext().getSystemService<SensorManager>()
            )


        @JvmStatic
        @Provides
        internal fun providesWindowManager(arLabelsDependencyProvider: ARLabelsDependencyProvider) =
            requireNotNull(
                arLabelsDependencyProvider.getSensorsContext().getSystemService<WindowManager>()
            )
    }
}
