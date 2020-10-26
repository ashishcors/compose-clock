package com.example.composeclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.example.composeclock.ui.HomeScreen
import com.example.composeclock.ui.theme.ComposeClockTheme

class MainActivity : AppCompatActivity() {

  private var broadcastReceiver: BroadcastReceiver? = null
  private val viewModel by viewModels<MainViewModel>()
  private var secondsHandler: Handler = Handler()
  private lateinit var secondsRunnable: Runnable

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyApp {
        HomeScreen()
      }
    }
    viewModel.setTime(System.currentTimeMillis())
    startTicker()
    addBroadcastReceiver()
  }

  private fun startTicker() {
    secondsRunnable = object : Runnable {
      override fun run() {
        viewModel.addSecond()
        secondsHandler.postDelayed(this, SECOND_IN_MILLIS)
      }
    }
//    secondsHandler.postDelayed(secondsRunnable, SECOND_IN_MILLIS)
  }

  private fun addBroadcastReceiver() {
    broadcastReceiver = object : BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action?.compareTo(Intent.ACTION_TIME_TICK) == 0) {
          viewModel.setTime(System.currentTimeMillis())
        }
      }
    }
    registerReceiver(broadcastReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
  }

  override fun onDestroy() {
    if (broadcastReceiver != null) unregisterReceiver(broadcastReceiver)
    secondsHandler.removeCallbacks(secondsRunnable)
    super.onDestroy()
  }

  companion object {
    const val SECOND_IN_MILLIS = 1000L
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
