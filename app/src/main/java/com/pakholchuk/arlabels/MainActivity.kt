package com.pakholchuk.arlabels

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.map
import androidx.ui.tooling.preview.Preview
import com.pakholchuk.arlabels.databinding.ActivityMainBinding
import com.pakholchuk.arlabels.ui.ARLabelsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
const val TAG = "fatal_log"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    private val REQUEST_CODE_PERMISSIONS = 10
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        val labels = mutableListOf<LabelProperties>()
        for (i in -10..10) labels.add(
            LabelProperties(
                10, i*30, i*50, 50, "this is $i", "label$i"
            )
        )
        val unitPoints = mutableListOf<UnitPoint>(
            UnitPoint("1", "House", LocationData(55.696153, 37.325086)),
            UnitPoint("1", "Perekrestok", LocationData(55.694620, 37.331619)),
            UnitPoint("1", "Dixie", LocationData(55.697598, 37.323788)),
            UnitPoint("1", "New 5'ka", LocationData(55.695553, 37.319179)),
            UnitPoint("1", "Old 5'ka", LocationData(55.695278, 37.333144)),
            UnitPoint("1", "Ex-Lenta", LocationData(55.696173, 37.335708)),
            UnitPoint("1", "Skolkovo Station", LocationData(55.699940, 37.342510)),
            UnitPoint("1", "Nik's House", LocationData(55.705159, 37.318376)),
            UnitPoint("1", "SkolTech", LocationData(55.698964, 37.359287)),
            UnitPoint("1", "Post Office", LocationData(55.700725, 37.322044)),
            UnitPoint("1", "Na Rogakh", LocationData(55.695476, 37.324371)),
            UnitPoint("1", "Vkusvill", LocationData(55.696438, 37.323202)),
            UnitPoint("1", "Toilet", LocationData(55.694228, 37.328708)),
            UnitPoint("1", "New Parking", LocationData(55.692683, 37.325444)),
            UnitPoint("1", "Bakovka station", LocationData(55.682830, 37.315526)),

            )

        viewModel.setUnitPoints(unitPoints)
        viewModel.getUpdates()

        binding.compose.setContent {
            val labelsState = viewModel.compassUpdate.map {
                ARLabelUtils.prepareLabelsProperties(it, binding.viewFinder.measuredWidth, binding.viewFinder.measuredHeight)
            }.observeAsState(initial = listOf())

            Labels(labels = labelsState.value, onLabelClick = ::onLabelClick)
//            Labels(labels = labels, onLabelClick = ::onLabelClick)
        }

    }

    fun onLabelClick(id: String) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }

    fun labelsFlow() = callbackFlow<List<LabelProperties>> {
        viewModel.compassUpdate.map {
            ARLabelUtils.prepareLabelsProperties(it, binding.viewFinder.measuredWidth, binding.viewFinder.measuredHeight)
        }.observe(this@MainActivity, {

        })
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCamera() {
        val cameraProviderFuture
                = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            val preview = androidx.camera.core.Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(binding.viewFinder.surfaceProvider) }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview
                )
            } catch (e: Exception) {
                Log.e("TAG", "Use case binding failed", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val labels = mutableListOf<LabelProperties>()
//    for (i in 1..10) labels.add(
//        LabelProperties(
//            10, i*10f, i*30f, 50, "this is $i", "label$i"
//        )
//    )
//
//    Labels(labels = labels, onLabelClick = {})
//
//}