package com.example.composeclock.ui.components

import android.util.Log
import androidx.compose.animation.core.AnimationConstants.Infinite
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.composeclock.ui.theme.ComposeClockTheme
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

//class LongPropKey : PropKey<Long, AnimationVector1D> {
//  override val typeConverter: TwoWayConverter<Long, AnimationVector1D>
//    get() = TwoWayConverter(
//      convertFromVector = { return@TwoWayConverter it.value.toLong() },
//      convertToVector = { AnimationVector1D(it.toFloat()) }
//    )
//}


val Seconds = FloatPropKey()

@Composable
fun Clock(
  timeZone: TimeZone = TimeZone.getDefault(),
  strokeWidth: Dp = 24.dp,
  color: Color = MaterialTheme.colors.primary,
  modifier: Modifier = Modifier,
  speedMultiplier: Float = 1f
) {

  val state = transition(
    definition = ClockTransition,
    initState = SecondState.START,
    toState = SecondState.END
  )


  Canvas(modifier = modifier) {

    val calendar = Calendar.getInstance(timeZone)
    val millis = calendar.get(Calendar.MILLISECOND)
    val second = calendar.get(Calendar.SECOND) + millis / 1000f
    val minute = calendar.get(Calendar.MINUTE) + second / 60f
    val hour = calendar.get(Calendar.HOUR) + minute / 60f

    Log.e("myTag", "${calendar.get(Calendar.SECOND)}, ${state[Seconds]} $second")

    drawCircle(
      color = color,
      style = Stroke(width = strokeWidth.value),
      center = center,
      radius = (size.width - strokeWidth.value) / 2,
    )

    drawHand(
      drawScope = this,
      color = color,
      strokeWidth = strokeWidth,
      location = hour * speedMultiplier,
      isHour = true
    )

    drawHand(
      drawScope = this,
      color = color,
      strokeWidth = strokeWidth,
      location = minute * speedMultiplier,
      isHour = false
    )

    drawHand(
      drawScope = this,
      color = color,
      strokeWidth = strokeWidth / 2,
      location = second * speedMultiplier,
      isHour = false
    )
  }
}


fun drawHand(
  drawScope: DrawScope,
  color: Color,
  strokeWidth: Dp,
  location: Float,
  isHour: Boolean
) {
  with(drawScope) {
    val angle =
      if (isHour)
        Math.PI * location / 6 - Math.PI / 2
      else
        Math.PI * location / 30 - Math.PI / 2
    val length = if (isHour) size.width / 4 else size.width / 3
    val end = Offset(
      (center.x + cos(angle) * length).toFloat(),
      (center.y + sin(angle) * length).toFloat()
    )
    drawLine(
      color = color,
      start = center,
      end = end,
      strokeWidth = strokeWidth.value,
      cap = StrokeCap.Round
    )
  }
}

private enum class SecondState { START, END }

private val ClockTransition = transitionDefinition<SecondState> {
  state(SecondState.START) {
    this[Seconds] = 0f
  }

  state(SecondState.END) {
    this[Seconds] = 1f
  }

  transition(fromState = SecondState.START, toState = SecondState.END) {
    Seconds using repeatable(
      iterations = Infinite,
      animation = tween(durationMillis = 1000)
    )
  }
}


@Preview(showBackground = true)
@Composable
fun PreviewClock() {
  ComposeClockTheme {
    Clock(modifier = Modifier.fillMaxSize())
  }
}
