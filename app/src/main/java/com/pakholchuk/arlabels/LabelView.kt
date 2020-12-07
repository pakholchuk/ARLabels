package com.pakholchuk.arlabels

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.pakholchuk.arlabels.ui.purple200

@Composable
fun Labels(
    labels: List<LabelProperties>,
    modifier: Modifier = Modifier,
    onLabelClick: (unitId: String) -> Unit,
    label: @Composable ((LabelProperties, Modifier) -> Unit)? = null
) {
    Box(modifier = modifier.fillMaxSize().background(Color.Transparent)) {
        if (label == null)
            labels.forEach {
                Label(
                    arLabelProperties = it,
                    Modifier
                        .centerCoordinates(it.positionX.toInt(), it.positionY.toInt())
                        .clickable(onClick = { onLabelClick(it.pointID) })
                )
            }
        else labels.forEach {
            label(it, Modifier)
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

@Composable
fun Label(arLabelProperties: LabelProperties, modifier: Modifier = Modifier) {
    Surface(
        color = Color.getTransparentColor(arLabelProperties.alpha.toFloat(), Color.White),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(2.dp, purple200),
        modifier = modifier
    ) {
        Column {
            Text(
                modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally),
                text = arLabelProperties.title,
            )
            Text(
                modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = "${arLabelProperties.distance} m",
            )
        }
    }
}

private fun Color.Companion.getTransparentColor(alpha: Float, color: Color): Color {
    return Color(color.red, color.green, color.blue, alpha/256)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val labels = mutableListOf<LabelProperties>()
    for (i in 0..10) labels.add(
        LabelProperties(
            100, i*30, i*50, 50, "this is long title $i", "label$i"
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