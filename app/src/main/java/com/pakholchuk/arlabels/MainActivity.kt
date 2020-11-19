package com.pakholchuk.arlabels

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.ui.tooling.preview.Preview
import com.pakholchuk.arlabels.databinding.ActivityMainBinding
import com.pakholchuk.arlabels.ui.ARLabelsTheme
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        binding.compose.setContent {
            Labels(labels = labels, onLabelClick = ::onLabelClick)
        }

    }

    fun onLabelClick(id: String) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
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