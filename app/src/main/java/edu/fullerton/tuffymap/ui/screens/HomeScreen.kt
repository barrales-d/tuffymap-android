//@file:OptIn(MapboxExperimental::class)

package edu.fullerton.tuffymap.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
//import com.mapbox.maps.MapboxExperimental
import edu.fullerton.tuffymap.R
import edu.fullerton.tuffymap.ui.theme.TuffyMapTheme


@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Tuffy Map")
        ZoomImage()
//        MapScreen()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TuffyMapTheme {
        HomeScreen()
    }
}
@Composable
private fun ZoomImage() {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    Image(
        painter = painterResource(id = R.drawable.csuf_campus_1),
        contentDescription = "CSUF Campus Map",
        modifier  = Modifier
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    // Update the scale based on zoom gestures.
                    scale *= zoom

                    // Limit the zoom levels within a certain range (optional).
                    scale = scale.coerceIn(0.8f, 3f)

                    // Update the offset to implement panning when zoomed.
                    offset = if (scale == 1f) Offset(0f, 0f) else offset + pan
                }
            }
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offset.x, translationY = offset.y
            )
    )
}


//@Composable
//private fun MapScreen() {
//    MapboxMap(
//        modifier = Modifier.fillMaxSize(),
//        mapViewportState = MapViewportState().apply {
//            setCameraOptions {
//                zoom(10.0)
//                center(Point.fromLngLat(33.8823, -117.8851))
//                pitch(0.0)
//                bearing(0.0)
//            }
//        }
//    )
//}