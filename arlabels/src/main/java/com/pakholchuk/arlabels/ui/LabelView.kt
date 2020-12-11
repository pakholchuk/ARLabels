package com.pakholchuk.arlabels.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.pakholchuk.arlabels.LabelProperties

@Composable
fun Labels(
        labelsList: List<LabelProperties?>,
        modifier: Modifier = Modifier,
        onLabelClick: ((listPosition: Int) -> Unit)? = null,
        content: @Composable (LabelProperties, listPosition: Int) -> Unit
) {
    Box(modifier = modifier.fillMaxSize().background(Color.Transparent)) {
        val indexedLabels = labelsList.mapIndexed { index, labelProperties ->
            IndexedValue(index, labelProperties)
        }.sortedByDescending { it.value?.distance }
        indexedLabels.forEach {
            if (it.value != null)
            Surface(Modifier
                .centerCoordinates(it.value!!.positionX, it.value!!.positionY)
                .clickable(onClick = { onLabelClick?.let { onLabelClick -> onLabelClick(it.index) } })) {
                content(it.value!!, it.index)
            }
        }
    }
}

fun Modifier.centerCoordinates(x: Int, y: Int) = Modifier.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val placeableX = x - placeable.width / 2
    val placeableY = y - placeable.height / 2
    layout(placeable.width, placeable.height) {
        placeable.place(placeableX, placeableY)
    }
}

@Composable
fun Label(labelProperties: LabelProperties, pos: Int) {
    Surface(
        color = Color.White.copy(alpha = labelProperties.alpha.toFloat()/256),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                text = "${labelProperties.distance} m",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    val labels = mutableListOf<LabelProperties>()
//    for (i in 0..10) labels.add(
//        LabelProperties(
//            100, i * 30, i * 50, 50, "this is long title $i", "label$i"
//        )
//    )
//    Labels(labels = labels)

}

@Preview
@Composable
fun PreviewLabel() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "SFSF", modifier = Modifier.centerCoordinates(100, 200))
    }
}