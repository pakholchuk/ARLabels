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

//import com.pakholchuk.arlabels.ui.purple200

const val TAG = "fatal_log"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* val homePoints = mutableListOf<Point>(
            Point(
                "1",
                "House",
                LocationData(55.696153, 37.325086)
            ),
            Point(
                "1",
                "Perekrestok",
                LocationData(55.694620, 37.331619)
            ),
            Point(
                "1",
                "Dixie",
                LocationData(55.697598, 37.323788)
            ),
            Point(
                "1",
                "New 5'ka",
                LocationData(55.695553, 37.319179)
            ),
            Point(
                "1",
                "Old 5'ka",
                LocationData(55.695278, 37.333144)
            ),
            Point(
                "1",
                "Ex-Lenta",
                LocationData(55.696173, 37.335708)
            ),
            Point(
                "1",
                "Skolkovo Station",
                LocationData(55.699940, 37.342510)
            ),
            Point(
                "1",
                "Nik's House",
                LocationData(55.705159, 37.318376)
            ),
            Point(
                "1",
                "SkolTech",
                LocationData(55.698964, 37.359287)
            ),
            Point(
                "1",
                "Post Office",
                LocationData(55.700725, 37.322044)
            ),
            Point(
                "1",
                "Na Rogakh",
                LocationData(55.695476, 37.324371)
            ),
            Point(
                "1",
                "Vkusvill",
                LocationData(55.696438, 37.323202)
            ),
            Point(
                "1",
                "Toilet",
                LocationData(55.694228, 37.328708)
            ),
            Point(
                "1",
                "New Parking",
                LocationData(55.692683, 37.325444)
            ),
            Point(
                "1",
                "Bakovka station",
                LocationData(55.682830, 37.315526)
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
            Point(
                "1",
                "first",
                LocationData(55.668129, 37.511553)
            ),
            Point(
                "2",
                "second",
                LocationData(55.668123, 37.512063)
            ),
            Point(
                "3",
                "third",
                LocationData(55.668158, 37.513007)
            ),
            Point(
                "4",
                "fourth",
                LocationData(55.667929, 37.513325)
            ),
            Point(
                "5",
                "fifth",
                LocationData(55.667473, 37.513726)
            ),
            Point(
                "6",
                "sixth",
                LocationData(55.667294, 37.512576)
            ),
            Point(
                "7",
                "seventh",
                LocationData(55.667262, 37.511976)
            ),
            Point(
                "8",
                "eighth",
                LocationData(55.667347, 37.511278)
            ),
            Point(
                "9",
                "ninth",
                LocationData(55.667635, 37.511111)
            ),
            Point(
                "19",
                "O'K",
                LocationData(55.666644, 37.514676)
            ),
            Point(
                "OFFICE",
                "Office",
                LocationData(55.667638, 37.511648)
            ),
            Point(
                "M",
                "Метро Пр.Вернадского",
                LocationData(55.676132, 37.505141)
            ),

            )
        val adapter = MyAdapter()
        adapter.list = jobPoints.map { ARLabelData(it) }.toMutableList()

        binding.labelsView.onCreate(object : ARLabelsDependencyProvider {
            override fun getARViewLifecycleOwner(): LifecycleOwner {
                return this@MainActivity
            }
            override fun getPermissionActivity(): Activity {
                return this@MainActivity
            }
        })
        binding.labelsView.adapter = adapter
        binding.slider.addOnChangeListener { _, value, _ ->
            binding.labelsView.maxDistance = value.roundToInt()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        binding.labelsView.onRequestPermissionResult(requestCode, permissions, grantResults)
    }
}
class MyAdapter : LabelsAdapter<MyAdapter.MyLabelViewHolder>() {

    var list = mutableListOf<ARLabelData>()

    override fun createViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup?): MyLabelViewHolder {
        return MyLabelViewHolder(MyLabelItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount() = list.size

    override fun getLocationData(listPosition: Int): LocationData {
        return list[listPosition].point.location
    }

    inner class MyLabelViewHolder(private val binding: MyLabelItemBinding) : LabelViewHolder(binding.root) {
        override fun bind(labelProperties: LabelProperties, listPosition: Int) {
            binding.root.setOnClickListener {
                list[listPosition].point.title += "1"
                binding.textview.text = list[listPosition].point.title
            }
            binding.textview.text = list[listPosition].point.title
            val c = Color.Black.copy(alpha = (labelProperties.alpha.toFloat()/256))
            binding.root.setBackgroundColor(c.toArgb())
        }
    }
}
data class Point(
        val id: String,
        var title: String,
        val location: LocationData,
)

data class ARLabelData(
        val point: Point,
        var additionalLabelProperties: Any? = null
)
