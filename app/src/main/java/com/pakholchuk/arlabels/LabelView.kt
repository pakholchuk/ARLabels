package com.pakholchuk.arlabels

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun Labels(
    labels: List<LabelProperties>,
    modifier: Modifier = Modifier,
    onLabelClick: (unitId: String) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize().background(Color.Transparent)) {
        labels.forEach {
            Label(
                arLabelProperties = it,
                Modifier
                    .centerCoordinates(it.positionX.toInt(), it.positionY.toInt())
                    .clickable(onClick = { onLabelClick(it.unitId) })
            )
        }
    }
}

fun Modifier.centerCoordinates(x: Int, y: Int)
        = Modifier.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val placeableX = x - placeable.width/2
    val placeableY = y - placeable.height/2
    layout(placeable.width, placeable.height) {
        placeable.place(placeableX, placeableY)
    }
}
//
//fun Modifier.firstBaselineToTop(
//    firstBaselineToTop: Dp
//) = Modifier.layout { measurable, constraints ->
//    val placeable = measurable.measure(constraints)
//    // Check the composable has a first baseline
//    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
//    val firstBaseline = placeable[FirstBaseline]
//
//    // Height of the composable with padding - first baseline
//    val placeableY = firstBaselineToTop.toIntPx() - firstBaseline
//    val height = placeable.height + placeableY
//    layout(placeable.width, height) {
//        placeable.placeRelative(0, placeableY)
//    }
//}


@Composable
fun Label(arLabelProperties: LabelProperties, modifier: Modifier = Modifier) {
    Surface(
        color = Color.Green,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = arLabelProperties.title
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val labels = mutableListOf<LabelProperties>()
    for (i in -10..10) labels.add(
        LabelProperties(
            10, i*30, i*50, 50, "this is $i", "label$i"
        )
    )

    Labels(labels = labels, onLabelClick = {})

}
@Preview
@Composable
fun PreviewLabel(){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "SFSF", modifier = Modifier.centerCoordinates(100, 200))
    }
}