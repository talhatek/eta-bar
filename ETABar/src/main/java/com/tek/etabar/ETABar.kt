package com.tek.etabar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

/**
 *  @param elapsedPercentage Percentage of elapsed time/total time
 *  @param inactiveBarColor Color of the remainder
 *  @param activeBarColor Color of [elapsedPercentage]
 *  @param imageBitmap ImageBitmap of the icon
 *  @param modifier Modifier to be applied to the ETABar
 *  @param strokeWidth Stroke width to be applied to the Arc's.
 *  Don't forget to proportion the stroke width to the icon width.
 *  Default icon width 24dp and stroke width 8dp
 */
@Composable
fun ETABar(
    elapsedPercentage: Float,
    inactiveBarColor: Color,
    activeBarColor: Color,
    imageBitmap: ImageBitmap,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val center = Offset(size.width / 2f, size.height / 2f)
    val beta = (250f * elapsedPercentage + 145f) * (Math.PI / 180f).toFloat()
    val rotate = round((325) + (256 * elapsedPercentage))
    val radius = size.width / 2f
    val xOfCurrentPoint = cos(beta) * radius
    val yOfCurrentPoint = sin(beta) * radius
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * elapsedPercentage,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )



            rotate(
                rotate,
                pivot = Offset(
                    ((center.x + xOfCurrentPoint)),
                    ((center.y + yOfCurrentPoint))
                )
            ) {
                drawImage(
                    image = imageBitmap,
                    topLeft = Offset(
                        ((center.x + xOfCurrentPoint) - imageBitmap.width / 2),
                        ((center.y + yOfCurrentPoint) - imageBitmap.height / 2)
                    )
                )
            }
        }
    }
}
