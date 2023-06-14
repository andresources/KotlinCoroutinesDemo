package com.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.kotlincoroutinesdemo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LunchDunActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch_dun)
    }

    suspend fun launchcoroutine(view: View) {
        println("fun Start")
        var j = lifecycleScope.launch {
            println("Co - Start")
            delay(2000L)
            println("Co - End")
        }
        j.join()


        println("fun End")
    }

}