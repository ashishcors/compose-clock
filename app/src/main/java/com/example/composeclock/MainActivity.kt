package com.example.composeclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.example.composeclock.ui.HomeScreen
import com.example.composeclock.ui.theme.ComposeClockTheme

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyApp {
        HomeScreen()
      }
    }
  }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
  ComposeClockTheme {
    Surface(color = MaterialTheme.colors.background) {
      content()
    }
  }
}
