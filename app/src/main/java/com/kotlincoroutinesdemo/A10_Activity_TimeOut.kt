package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class A10_Activity_TimeOut : AppCompatActivity() {
    //https://medium.com/androiddevelopers/coroutines-on-android-part-i-getting-the-background-3e0e54d20bb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a10_time_out)
        lifecycleScope.launch {
            try {
                withTimeout(5000) {
                    for (i in 1..10) {
                        Log.i("Cor", "${i}")
                        delay(1000)
                    }
                }
            }catch (ex: Exception){
                Log.i("Cor","exception : "+ex.localizedMessage)
            }
        }
    }
}