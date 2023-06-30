package com.cancellation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.kotlincoroutinesdemo.R
import kotlinx.coroutines.*

class CancellationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancellation)
    }

    fun funLaunchCoroutine(view: View) {
        lifecycleScope.launch{
            val job1 = launch(Dispatchers.IO) {
                var i=0
               while (i<10){
                   i++

                   println("Job1 Count : ${i} - $isActive")
                   delay(500)
               }
            }
            delay(1000L)
            println("Cancel!")
            job1.cancel()
            println("Done!")
        }
    }
}