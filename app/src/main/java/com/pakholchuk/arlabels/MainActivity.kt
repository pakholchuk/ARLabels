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

       val homePoints = mutableListOf<Point>(
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


//        val jobPoints = mutableListOf(
//            Point(
//                "1",
//                "first",
//                LocationData(55.668129, 37.511553)
//            ),
//            Point(
//                "2",
//                "second",
//                LocationData(55.668123, 37.512063)
//            ),
//            Point(
//                "3",
//                "third",
//                LocationData(55.668158, 37.513007)
//            ),
//            Point(
//                "4",
//                "fourth",
//                LocationData(55.667929, 37.513325)
//            ),
//            Point(
//                "5",
//                "fifth",
//                LocationData(55.667473, 37.513726)
//            ),
//            Point(
//                "6",
//                "sixth",
//                LocationData(55.667294, 37.512576)
//            ),
//            Point(
//                "7",
//                "seventh",
//                LocationData(55.667262, 37.511976)
//            ),
//            Point(
//                "8",
//                "eighth",
//                LocationData(55.667347, 37.511278)
//            ),
//            Point(
//                "9",
//                "ninth",
//                LocationData(55.667635, 37.511111)
//            ),
//            Point(
//                "19",
//                "O'K",
//                LocationData(55.666644, 37.514676)
//            ),
//            Point(
//                "OFFICE",
//                "Office",
//                LocationData(55.667638, 37.511648)
//            ),
//            Point(
//                "M",
//                "Метро Пр.Вернадского",
//                LocationData(55.676132, 37.505141)
//            ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.664944, 37.555621)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.668916, 37.556578)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.672449, 37.551181)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676572, 37.547239)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.681053, 37.545669)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.676132, 37.505141)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.693123, 37.516607)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.695161, 37.511425)
//                ),
//                Point(
//                        "M",
//                        "Метро Пр.Вернадского",
//                        LocationData(55.694478, 37.499913)
//                ),
//                Point(
//                        "M",
//                        "папа джонс",
//                        LocationData(55.676102, 37.519051)
//                ),
//                Point(
//                        "M",
//                        "газпромбанк",
//                        LocationData(55.673569, 37.503241)
//                ),
//                Point(
//                        "M",
//                        "гбк 31",
//                        LocationData(55.669514, 37.501875)
//                ),
//                Point(
//                        "M",
//                        "старый сычуань",
//                        LocationData(55.667032, 37.495139)
//                ),
//                Point(
//                        "M",
//                        "МедВет",
//                        LocationData(55.664790, 37.500965)
//                ),
//                Point(
//                        "M",
//                        "загс",
//                        LocationData(55.664910, 37.508157)
//                ),
//                Point(
//                        "M",
//                        "рио",
//                        LocationData(55.663369, 37.511009)
//                ),
//
//            )
        val adapter = MyAdapter()
//        adapter.list = jobPoints.map { ARLabelData(it) }.toMutableList()

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
class MyAdapter : LabelsAdapter<MyAdapter.MyLabelViewHolder>() {

    val json = "[[55.67538882797054,37.49599021220507],[55.67660084296581,37.49779243678568],[55.67790977680356,37.50113942529259],[55.678927806045365,37.50620281816198],[55.67936409614649,37.51195277277642],[55.679848857193655,37.51770272739082],[55.67936409614649,37.521822097860834],[55.678685420544106,37.52431088418646],[55.67752194914096,37.527572052475236],[55.67926714321304,37.53392274861652],[55.678055211181466,37.540015984103455],[55.673061653465325,37.54181820868407],[55.67344952553728,37.54937038787912],[55.671267694898454,37.54859800591598],[55.66971609654703,37.54602339937222],[55.66874631619874,37.54327715239219],[55.66709763421022,37.54499355675471],[55.66704914250746,37.550056949624135],[55.66467297514724,37.552116634859146],[55.661375196704476,37.54559429828159],[55.65962920102364,37.54722488242598],[55.6572040772647,37.546881601553494],[55.6563309957749,37.54181820868407],[55.656088469668425,37.53769883821405],[55.65502133687441,37.53400856883466],[55.65414820649656,37.53100486120028],[55.65405119080301,37.52516908636775],[55.652886983648784,37.52353850222336],[55.65264443611573,37.5196765924077],[55.65293549297433,37.516329603900786],[55.65211082623146,37.51375499735703],[55.651868273869724,37.509463986450754],[55.65167423089387,37.50620281816198],[55.65089804933301,37.50268418921885],[55.650752513570104,37.50139688594695],[55.651431675815985,37.49607603242316],[55.6524018870737,37.493072324788784],[55.65361461719431,37.49015443737253],[55.65555490692296,37.48594924668439],[55.65570042475961,37.48088585381496],[55.65390566680999,37.47977019097933],[55.65628249067431,37.4759941013818],[55.65865916962603,37.47710976421744],[55.660938705116955,37.477023943999306],[55.662781636344455,37.48002765163372],[55.6649154478426,37.481057494251225],[55.667631038957836,37.4807142133787],[55.66932818730424,37.48251643795935],[55.669182720342505,37.48517686472124],[55.67136466774376,37.48749401061065],[55.672770746877035,37.49075517889942],[55.671655584831484,37.49341560566127],[55.67359497656863,37.495132010023795]]"
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
