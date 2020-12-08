//package com.pakholchuk.arlabels
//
//import android.content.Context
//import android.hardware.SensorManager
//import android.view.WindowManager
//import androidx.core.content.getSystemService
//import com.pakholchuk.arlabels.App.Companion.context
//import com.patloew.colocation.CoLocation
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ApplicationComponent
//import dagger.hilt.android.qualifiers.ApplicationContext
//import javax.inject.Singleton
//
//@Module
//@InstallIn(ApplicationComponent::class)
//object AppModule {
//    @Singleton
//    @Provides
//    fun provideCompassRepository(orientationProvider: OrientationProvider, locationProvider: LocationProvider)
//            = CompassRepository(orientationProvider, locationProvider)
//
//    @Singleton
//    @Provides
//    fun provideOrientationProvider(sensorManager: SensorManager, windowManager: WindowManager) = OrientationProvider(sensorManager, windowManager)
//
//    @Provides
//    fun provideSensorManager(@ApplicationContext context: Context) =
//        requireNotNull(context.getSystemService<SensorManager>())
//
//    @Provides
//    fun providesWindowManager(@ApplicationContext context: Context) =
//        requireNotNull(context.getSystemService<WindowManager>())
//
//    @Singleton
//    @Provides
//    fun providesLocationProvider(coLocation: CoLocation) = LocationProvider(coLocation)
//
//    @Singleton
//    @Provides
//    fun providesCoLocation(@ApplicationContext context: Context) = CoLocation.from(context)
//
//}