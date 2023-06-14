package com.coroutinescope

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


fun AppCompatActivity.getMyScope() = CoroutineScope(Dispatchers.Main + Job())