package com.example.composeclock.ui.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
  Button(
    onClick = { updateCount(count + 1) },
    modifier = Modifier.fillMaxWidth().padding(24.dp)
  ) {
    Text("I've been clicked $count times")
  }
}
