package com.pakholchuk.arlabels

//import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pakholchuk.arlabels.databinding.ActivityMainBinding
import com.pakholchuk.arlabels.databinding.CoBinding
import com.pakholchuk.arlabels.di.ARLabelsDependencyProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

const val TAG = "fatal_log"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ARLabelsAdapter2()
        binding.labelsView.onCreate(object : ARLabelsDependencyProvider {
            override fun getARViewLifecycleOwner(): LifecycleOwner {
                return this@MainActivity
            }
            override fun getPermissionActivity(): AppCompatActivity {
                return this@MainActivity
            }
        })
        binding.labelsView.adapter = adapter
        binding.slider.addOnChangeListener { _, value, _ ->
            binding.labelsView.maxDistance = value.roundToInt()
        }
    }

}