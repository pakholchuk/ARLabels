package com.pakholchuk.arlabels

//import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pakholchuk.arlabels.databinding.ActivityMainBinding
import com.pakholchuk.arlabels.ui.purple200

const val TAG = "fatal_log"

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ARLabelsViewModel>()

    private val REQUEST_CODE_PERMISSIONS = 10
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (allPermissionsGranted()) {
//            startCamera()
//        } else {
//            ActivityCompat.requestPermissions(
//                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
//        }
//        val labels = mutableListOf<LabelProperties>()
//        for (i in -10..10) labels.add(
//            LabelProperties(
//                10, i*30, i*50, 50, "this is $i", "label$i"
//            )
//        )
        val homePoints = mutableListOf<Point>(
            Point("1", "House", LocationData(55.696153, 37.325086)),
            Point("1", "Perekrestok", LocationData(55.694620, 37.331619)),
            Point("1", "Dixie", LocationData(55.697598, 37.323788)),
            Point("1", "New 5'ka", LocationData(55.695553, 37.319179)),
            Point("1", "Old 5'ka", LocationData(55.695278, 37.333144)),
            Point("1", "Ex-Lenta", LocationData(55.696173, 37.335708)),
            Point("1", "Skolkovo Station", LocationData(55.699940, 37.342510)),
            Point("1", "Nik's House", LocationData(55.705159, 37.318376)),
            Point("1", "SkolTech", LocationData(55.698964, 37.359287)),
            Point("1", "Post Office", LocationData(55.700725, 37.322044)),
            Point("1", "Na Rogakh", LocationData(55.695476, 37.324371)),
            Point("1", "Vkusvill", LocationData(55.696438, 37.323202)),
            Point("1", "Toilet", LocationData(55.694228, 37.328708)),
            Point("1", "New Parking", LocationData(55.692683, 37.325444)),
            Point("1", "Bakovka station", LocationData(55.682830, 37.315526)),
        )
        val jobPoints = mutableListOf(
            Point("1", "first", LocationData(55.668129, 37.511553)),
            Point("1", "second", LocationData(55.668123, 37.512063)),
            Point("1", "third", LocationData(55.668158, 37.513007)),
            Point("1", "fourth", LocationData(55.667929, 37.513325)),
            Point("1", "fifth", LocationData(55.667473, 37.513726)),
            Point("1", "sixth", LocationData(55.667294, 37.512576)),
            Point("1", "seventh", LocationData(55.667262, 37.511976)),
            Point("1", "eighth", LocationData(55.667347, 37.511278)),
            Point("1", "ninth", LocationData(55.667635, 37.511111)),
            Point("1", "O'K", LocationData(55.666644, 37.514676)),
            Point("OFFICE", "Office", LocationData(55.667638, 37.511648)),
            Point("M", "Метро Пр.Вернадского", LocationData(55.676132, 37.505141)),

            )

//        viewModel.setARLabelData(jobPoints.map { ARLabelData(it) })
//
//        viewModel.getUpdates()

        binding.labelsView.onCreate(object : ARLabelsDependencyProvider {
            override fun getSensorsContext(): Context {
                return this@MainActivity
            }

            override fun getARViewLifecycleOwner(): LifecycleOwner {
                return this@MainActivity
            }

            override fun getPermissionActivity(): Activity {
                return this@MainActivity
            }
        })
        binding.labelsView.setUpLabelsView(
            labelsDataList = jobPoints.map { ARLabelData(it) },
            onLabelClick = ::onLabelClick,
//            label = { props, modifier -> Label(props, modifier) }
        )
//        binding.compose.setContent {
//            val labelsState = viewModel.compassUpdate.map { compassData ->
//                ARLabelUtils.prepareLabelsProperties(compassData, binding.viewFinder.width, binding.viewFinder.height)
//            }.observeAsState(initial = listOf())
//            labelsState.value.find { it.positionX > 0 && it.positionX < binding.viewFinder.width }
//                ?.let { viewModel.setLowPassFilterAlpha(adjustLowPassFilterAlphaValue(it.positionX.toFloat(), binding.viewFinder.width)) }
//
//            Labels(labels = labelsState.value, onLabelClick = ::onLabelClick)
////            Labels(labels = labels, onLabelClick = ::onLabelClick)
//        }

    }


    @Composable
    fun Label(arLabelProperties: LabelProperties, modifier: Modifier = Modifier) {
        Surface(
            color = Color.Black,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(2.dp, purple200),
            modifier = modifier
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally),
                    text = arLabelProperties.title,
                    color = Color.Yellow
                )
            }
        }
    }

    fun onLabelClick(pointId: String) {
        Toast.makeText(this, pointId, Toast.LENGTH_SHORT).show()
    }

    //
//    fun labelsFlow() = callbackFlow<List<LabelProperties>> {
//        viewModel.compassUpdate.map {
//            ARLabelUtils.prepareLabelsProperties(it, binding.viewFinder.measuredWidth, binding.viewFinder.measuredHeight)
//        }.observe(this@MainActivity, {
//
//        })
//    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        binding.labelsView.onRequestPermissionResult(requestCode, permissions, grantResults)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

//    private fun startCamera() {
//        val cameraProviderFuture
//                = ProcessCameraProvider.getInstance(this)
//        cameraProviderFuture.addListener(Runnable {
//            val cameraProvider = cameraProviderFuture.get()
//            val preview = androidx.camera.core.Preview.Builder()
//                .build()
//                .also { it.setSurfaceProvider(binding.viewFinder.surfaceProvider) }
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    this, cameraSelector, preview
//                )
//            } catch (e: Exception) {
//                Log.e("TAG", "Use case binding failed", e)
//            }
//        }, ContextCompat.getMainExecutor(this))
//    }

//    @SuppressLint("MissingSuperCall")
//    override fun onRequestPermissionsResult(
//        requestCode: Int, permissions: Array<String>, grantResults:
//        IntArray) {
//        if (requestCode == REQUEST_CODE_PERMISSIONS) {
//            if (allPermissionsGranted()) {
//                startCamera()
//            } else {
//                Toast.makeText(this,
//                    "Permissions not granted by the user.",
//                    Toast.LENGTH_SHORT).show()
//                finish()
//            }
//        }
//    }
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