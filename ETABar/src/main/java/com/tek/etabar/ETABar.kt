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
import com.tek.etabar.Const.IMAGE_ROTATION_FIXER
import com.tek.etabar.Const.START_ANGLE
import com.tek.etabar.Const.SWEEP_ANGLE
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

/**
 *  @param elapsedPercentage Percentage of elapsed time/total time
 *  @param inactiveBarColor Color of the remainder
 *  @param activeBarColor Color of [elapsedPercentage]
 *  @param imageBitmap ImageBitmap of the icon. Don't forget that direction of the icon is important.
 *  @param modifier Modifier to be applied to the ETABar
 *  @param strokeWidth Stroke width to be applied to the Arc's.
 *  Don't forget to proportion the stroke width to the icon width.
 *  Default icon width 24dp and stroke width 8dp
 */
@Suppress("unused")
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
    val radius by remember(size) {
        mutableStateOf(size.width / 2f)
    }

    val xOfCurrentPoint =
        radius + cos((toRadians((START_ANGLE + (SWEEP_ANGLE * elapsedPercentage)).toDouble()))) * radius
    val yOfCurrentPoint =
        radius + sin(toRadians(((START_ANGLE + (SWEEP_ANGLE * elapsedPercentage)).toDouble()))) * radius

    val rotate =
        round(START_ANGLE + (SWEEP_ANGLE * elapsedPercentage)) + IMAGE_ROTATION_FIXER

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
                startAngle = START_ANGLE,
                sweepAngle = SWEEP_ANGLE,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeBarColor,
                startAngle = START_ANGLE,
                sweepAngle = SWEEP_ANGLE * elapsedPercentage,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            rotate(
                rotate,
                pivot = Offset(
                    (xOfCurrentPoint).toFloat(),
                    (yOfCurrentPoint).toFloat()
                )

            ) {
                drawImage(
                    image = imageBitmap,
                    topLeft = Offset(
                        (((xOfCurrentPoint) - imageBitmap.width / 2).toFloat()),
                        (((yOfCurrentPoint) - imageBitmap.height / 2).toFloat())
                    )
                )
            }
        }
    }
}
