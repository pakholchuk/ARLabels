package com.pakholchuk.arlabels

//import dagger.hilt.android.AndroidEntryPoint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.LifecycleOwner
import com.pakholchuk.arlabels.adapter.LabelViewHolder
import com.pakholchuk.arlabels.adapter.LabelsAdapter
import com.pakholchuk.arlabels.databinding.ActivityMainBinding
import com.pakholchuk.arlabels.databinding.MyLabelItemBinding
import com.pakholchuk.arlabels.di.ARLabelsDependencyProvider
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