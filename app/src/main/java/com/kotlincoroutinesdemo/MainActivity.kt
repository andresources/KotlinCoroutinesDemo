package com.kotlincoroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
     lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv=findViewById(R.id.tv)
        Log.i("Thread","First Statement"+Thread.currentThread().name)

    }
    fun clickme(view: View){
        GlobalScope.launch {
            Log.i("Thread","Second Statement"+Thread.currentThread().name)
            launch(coroutineContext) {
                Log.i("Thread","Third Statement"+Thread.currentThread().name)
            }
        }
        //startActivity(Intent(applicationContext,SecondActivity::class.java))
    }


}