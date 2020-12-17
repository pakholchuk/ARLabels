package com.pakholchuk.arlabels

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pakholchuk.arlabels.adapter.LabelViewHolder
import com.pakholchuk.arlabels.adapter.LabelsAdapter
import com.pakholchuk.arlabels.databinding.MyLabelItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ARLabelsAdapter2 : LabelsAdapter<ARLabelsAdapter2.UnitLabelViewHolder>() {

    var items = mutableListOf<ARUnit>()
    private lateinit var binding: MyLabelItemBinding

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(1000)
                if (items.isNotEmpty()) items.removeFirst()
                if (items.isEmpty()) items = homePoints.toMutableList()
            }
        }
    }

    override fun onCreateViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup?
    ): UnitLabelViewHolder {
        binding = MyLabelItemBinding.inflate(layoutInflater)
        Log.d(TAG, "createViewHolder: ${binding.root}")
        return UnitLabelViewHolder(binding)
    }

    override fun onDrawLabel(viewHolder: UnitLabelViewHolder, labelProperties: LabelProperties) {
        val s = items.find { it.locationData.id == labelProperties.id }
        viewHolder.binding.textview.text = s?.name
    }

    override fun getLocationsList(): List<LocationData> {
        return items.map { it.locationData }
    }

    inner class UnitLabelViewHolder(val binding: MyLabelItemBinding) : LabelViewHolder(binding.root)
}

val homePoints = arrayListOf<ARUnit>(
    ARUnit(
        LocationData("1", 55.696153, 37.325086),
        "House"
    ),
    ARUnit(

        LocationData("2",55.694620, 37.331619),
        "Perekrestok"
    ),
    ARUnit(

        LocationData("3",55.697598, 37.323788),
        "Dixie"
    ),
    ARUnit(

        LocationData("4",55.695553, 37.319179),
        "New 5'ka"
    ),
    ARUnit(

        LocationData("5",55.695278, 37.333144),
        "Old 5'ka"
    ),
    ARUnit(

        LocationData("6",55.696173, 37.335708),
        "Ex-Lenta"
    ),
    ARUnit(

        LocationData("7",55.699940, 37.342510),
        "Skolkovo Station"
    ),
    ARUnit(

        LocationData("8",55.705159, 37.318376),
        "Nik's House"
    ),
    ARUnit(

        LocationData("9",55.698964, 37.359287),
        "SkolTech"
    ),
    ARUnit(

        LocationData("10",55.700725, 37.322044),
        "Post Office"
    ),
    ARUnit(

        LocationData("11",55.695476, 37.324371),
        "Na Rogakh"
    ),
    ARUnit(

        LocationData("12",55.696438, 37.323202),
        "Vkusvill"
    ),
    ARUnit(

        LocationData("13",55.694228, 37.328708),
        "Toilet"
    ),
    ARUnit(

        LocationData("14",55.692683, 37.325444),
        "New Parking"
    ),
    ARUnit(

        LocationData("15",55.682830, 37.315526),
        "Bakovka station"
    ),
)

data class ARUnit(
    val locationData: LocationData,
    val name: String? = null,
)
