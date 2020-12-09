package com.pakholchuk.arlabels

//import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.pakholchuk.arlabels.databinding.ActivityMainBinding
import com.pakholchuk.arlabels.databinding.MyLabelItemBinding
import com.pakholchuk.arlabels.di.ARLabelsDependencyProvider
//import com.pakholchuk.arlabels.ui.purple200

const val TAG = "fatal_log"

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private val viewModel by viewModels<com.pakholchuk.arlabels.ARLabelsViewModel>()


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
       /* val homePoints = mutableListOf<com.pakholchuk.arlabels.Point>(
            com.pakholchuk.arlabels.Point(
                "1",
                "House",
                com.pakholchuk.arlabels.LocationData(55.696153, 37.325086)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Perekrestok",
                com.pakholchuk.arlabels.LocationData(55.694620, 37.331619)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Dixie",
                com.pakholchuk.arlabels.LocationData(55.697598, 37.323788)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "New 5'ka",
                com.pakholchuk.arlabels.LocationData(55.695553, 37.319179)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Old 5'ka",
                com.pakholchuk.arlabels.LocationData(55.695278, 37.333144)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Ex-Lenta",
                com.pakholchuk.arlabels.LocationData(55.696173, 37.335708)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Skolkovo Station",
                com.pakholchuk.arlabels.LocationData(55.699940, 37.342510)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Nik's House",
                com.pakholchuk.arlabels.LocationData(55.705159, 37.318376)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "SkolTech",
                com.pakholchuk.arlabels.LocationData(55.698964, 37.359287)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Post Office",
                com.pakholchuk.arlabels.LocationData(55.700725, 37.322044)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Na Rogakh",
                com.pakholchuk.arlabels.LocationData(55.695476, 37.324371)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Vkusvill",
                com.pakholchuk.arlabels.LocationData(55.696438, 37.323202)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Toilet",
                com.pakholchuk.arlabels.LocationData(55.694228, 37.328708)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "New Parking",
                com.pakholchuk.arlabels.LocationData(55.692683, 37.325444)
            ),
            com.pakholchuk.arlabels.Point(
                "1",
                "Bakovka station",
                com.pakholchuk.arlabels.LocationData(55.682830, 37.315526)
            ),
        )


//        viewModel.setARLabelData(jobPoints.map { ARLabelData(it) })
//
//        viewModel.getUpdates()

        binding.labelsView.onCreate(object : com.pakholchuk.arlabels.ARLabelsDependencyProvider {
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
            labelsDataList = jobPoints.map { com.pakholchuk.arlabels.ARLabelData(it) },
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
*/
        val jobPoints = mutableListOf(
            com.pakholchuk.arlabels.Point(
                "1",
                "first",
                com.pakholchuk.arlabels.LocationData(55.668129, 37.511553)
            ),
            com.pakholchuk.arlabels.Point(
                "2",
                "second",
                com.pakholchuk.arlabels.LocationData(55.668123, 37.512063)
            ),
            com.pakholchuk.arlabels.Point(
                "3",
                "third",
                com.pakholchuk.arlabels.LocationData(55.668158, 37.513007)
            ),
            com.pakholchuk.arlabels.Point(
                "4",
                "fourth",
                com.pakholchuk.arlabels.LocationData(55.667929, 37.513325)
            ),
            com.pakholchuk.arlabels.Point(
                "5",
                "fifth",
                com.pakholchuk.arlabels.LocationData(55.667473, 37.513726)
            ),
            com.pakholchuk.arlabels.Point(
                "6",
                "sixth",
                com.pakholchuk.arlabels.LocationData(55.667294, 37.512576)
            ),
            com.pakholchuk.arlabels.Point(
                "7",
                "seventh",
                com.pakholchuk.arlabels.LocationData(55.667262, 37.511976)
            ),
            com.pakholchuk.arlabels.Point(
                "8",
                "eighth",
                com.pakholchuk.arlabels.LocationData(55.667347, 37.511278)
            ),
            com.pakholchuk.arlabels.Point(
                "9",
                "ninth",
                com.pakholchuk.arlabels.LocationData(55.667635, 37.511111)
            ),
            com.pakholchuk.arlabels.Point(
                "19",
                "O'K",
                com.pakholchuk.arlabels.LocationData(55.666644, 37.514676)
            ),
            com.pakholchuk.arlabels.Point(
                "OFFICE",
                "Office",
                com.pakholchuk.arlabels.LocationData(55.667638, 37.511648)
            ),
            com.pakholchuk.arlabels.Point(
                "M",
                "Метро Пр.Вернадского",
                com.pakholchuk.arlabels.LocationData(55.676132, 37.505141)
            ),

            )
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

        val l = jobPoints.map { ARLabelData(it) }
        binding.labelsView.labelsDataList = l
        binding.labelsView.setOnLabelClickListener {
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show() }



        @Composable
        fun XMLLabel(labelProperties: LabelProperties) {
            val color = remember(Color.Blue) { l.find { it.point.id == labelProperties.pointID }?.additionalLabelProperties ?: Color.Red }
            Surface(
                color = color as Color,
                modifier = Modifier.clickable(onClick = { l.find { it.point.id == labelProperties.pointID }?.additionalLabelProperties = Color.Green})
            ) {

                Text(text = labelProperties.title, fontSize = 34.sp)
            }
        }
        binding.labelsView.setComposableLabel { labelProperties -> XMLLabel(
            labelProperties = labelProperties
        )  }


//        binding.labelsView.setAndroidViewLabel {
//            val s = MyLabelItemBinding.inflate(LayoutInflater.from(it))
//            class MyViewHolder(val bi: MyLabelItemBinding) : ViewHolder(bi.root) {
//                init {
//                    Log.d(TAG, "onCreateHolder: $this ")
//                }
//                override fun onBindView(labelProperties: LabelProperties) {
//                    Log.d(TAG, "onBindHolder: $this ")
//                    bi.textview.text = labelProperties.title
//                    bi.textview.textSize = 45f
//                }
//            }
//            MyViewHolder(s)
//        }

//            { view, labelProperties ->
//            view.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
//            val textView = view.findViewById<TextView>(R.id.textview)
//            Log.d(TAG, "textView found $textView")
//            textView.apply {
//                text = labelProperties.title
//                textSize = 34f
//                setTextColor(resources.getColor(android.R.color.white))
//            }
//
//        })
    }


//    @Composable
//    fun Label(arLabelProperties: com.pakholchuk.arlabels.LabelProperties, modifier: Modifier = Modifier) {
//        Surface(
//            color = Color.Black,
//            shape = RoundedCornerShape(4.dp),
//            border = BorderStroke(2.dp, purple200),
//            modifier = modifier
//        ) {
//            Column {
//                Text(
//                    modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally),
//                    text = arLabelProperties.title,
//                    color = Color.Yellow
//                )
//            }
//        }
//    }
//
//    fun onLabelClick(pointId: String) {
//        Toast.makeText(this, pointId, Toast.LENGTH_SHORT).show()
//    }
//
//    //
////    fun labelsFlow() = callbackFlow<List<LabelProperties>> {
////        viewModel.compassUpdate.map {
////            ARLabelUtils.prepareLabelsProperties(it, binding.viewFinder.measuredWidth, binding.viewFinder.measuredHeight)
////        }.observe(this@MainActivity, {
////
////        })
////    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        binding.labelsView.onRequestPermissionResult(requestCode, permissions, grantResults)
//    }
//
//    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
//        ContextCompat.checkSelfPermission(
//            baseContext, it
//        ) == PackageManager.PERMISSION_GRANTED
//    }

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