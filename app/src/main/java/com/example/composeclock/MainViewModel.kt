package com.example.composeclock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
  private val _time = MutableLiveData<Long>()
  val time: LiveData<Long> = _time

  fun setTime(time: Long) {
    _time.value = time
  }

  fun addSecond() {
    _time.value = (_time.value ?: 0) + 1000
  }
}
