package com.example.composeclock.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.composeclock.ui.components.Clock
import com.example.composeclock.ui.theme.ComposeClockTheme
import java.util.*

@Composable
fun HomeScreen() {

  Column(modifier = Modifier.fillMaxHeight()) {
    Clock(
      strokeWidth = 48.dp,
      modifier = Modifier.weight(1f).fillMaxWidth().padding(48.dp),
      color = if (MaterialTheme.colors.isLight) Color.Black else Color.White
    )
    Clock(
      timeZone = TimeZone.getTimeZone("UTC"),
      strokeWidth = 12.dp,
      modifier = Modifier.weight(1f).fillMaxWidth().padding(48.dp),
      color = if (MaterialTheme.colors.isLight) Color.Black else Color.White
    )
  }
}


@Preview(showBackground = true, name = "Home Preview")
@Composable
fun DefaultPreview() {
  ComposeClockTheme {
    HomeScreen()
  }
}
