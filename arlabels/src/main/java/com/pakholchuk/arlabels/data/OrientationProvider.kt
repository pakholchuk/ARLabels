package com.pakholchuk.arlabels.data

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.view.Surface
import android.view.WindowManager
import com.pakholchuk.arlabels.OrientationData
import com.pakholchuk.arlabels.utils.ARLabelUtils.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.mapLatest
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@ExperimentalCoroutinesApi
internal class OrientationProvider(
    private val sensorManager: SensorManager,
    private val windowManager: WindowManager
) {
    private var alpha = 0f
    private var lastCos = 0f
    private var lastSin = 0f

    private fun sensorEventsFlow() = callbackFlow<SensorEvent> {
        val sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let { trySend(event) }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                when (sensor?.type) {
                    Sensor.TYPE_ROTATION_VECTOR -> when (accuracy) {
                        SensorManager.SENSOR_STATUS_ACCURACY_LOW -> Log.d(TAG, "ACCURACY low")
                        SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM -> Log.d(TAG, "ACCURACY medium")
                        SensorManager.SENSOR_STATUS_ACCURACY_HIGH -> Log.d(TAG, "ACCURACY high")
                        else -> Unit
                    }
                    else -> Unit
                }
            }
        }
        sensorManager.registerListener(
            sensorEventListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
            SensorManager.SENSOR_DELAY_GAME
        )
        awaitClose { sensorManager.unregisterListener(sensorEventListener) }
    }

    fun getSensorUpdates(): Flow<OrientationData> =
        sensorEventsFlow().mapLatest { handleSensorEvent(it) }

    private fun handleSensorEvent(sensorEvent: SensorEvent): OrientationData {
        val rotationMatrix = FloatArray(9)
        val orientation = FloatArray(3)

        SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values)
        val adjustedRotationMatrix = getAdjustedRotationMatrix(rotationMatrix)
        SensorManager.getOrientation(adjustedRotationMatrix, orientation)
        val pitch = Math.toDegrees(orientation[1].toDouble()).toFloat()
        val azimuth = lowPassDegreesFilter(orientation[0])

        return OrientationData(azimuth, pitch)
    }

    private fun getAdjustedRotationMatrix(rotationMatrix: FloatArray): FloatArray {
        val axisXY = getProperAxis()
        val adjustedRotationMatrix = FloatArray(9)
        SensorManager.remapCoordinateSystem(
            rotationMatrix, axisXY.first,
            axisXY.second, adjustedRotationMatrix
        )
        return adjustedRotationMatrix
    }

    private fun getProperAxis(): Pair<Int, Int> {
        val worldAxisX: Int
        val worldAxisY: Int
        when (windowManager.defaultDisplay?.rotation) {
            Surface.ROTATION_90 -> {
                worldAxisX = SensorManager.AXIS_Z
                worldAxisY = SensorManager.AXIS_MINUS_X
            }
            Surface.ROTATION_180 -> {
                worldAxisX = SensorManager.AXIS_MINUS_X
                worldAxisY = SensorManager.AXIS_MINUS_Z
            }
            Surface.ROTATION_270 -> {
                worldAxisX = SensorManager.AXIS_MINUS_Z
                worldAxisY = SensorManager.AXIS_X
            }
            Surface.ROTATION_0 -> {
                worldAxisX = SensorManager.AXIS_X
                worldAxisY = SensorManager.AXIS_Z
            }
            else -> {
                worldAxisX = SensorManager.AXIS_X
                worldAxisY = SensorManager.AXIS_Z
            }
        }
        return Pair(worldAxisX, worldAxisY)
    }

    private fun lowPassDegreesFilter(azimuthRadians: Float): Float {
        lastSin = alpha * lastSin + (1 - alpha) * sin(azimuthRadians)
        lastCos = alpha * lastCos + (1 - alpha) * cos(azimuthRadians)
        return ((Math.toDegrees(atan2(lastSin, lastCos).toDouble()) + 360) % 360).toFloat()
    }

    fun setLowPassFilterAlpha(lowPassFilterAlpha: Float) {
        alpha = lowPassFilterAlpha
    }
}