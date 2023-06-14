package com.coroutinescope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlincoroutinesdemo.R
import kotlinx.coroutines.*

class ScopeActivity : AppCompatActivity() {
    private var myScope = getMyScope() //this is extension fun.
    //private var myScope =  CoroutineScope(Dispatchers.Main + Job())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scope)
    }
    lateinit var job1:Job
    lateinit var job2:Job
    fun StartCoroutineScope(view: View) {
        job1 = myScope.launch {
            printNNumnersWithDelay()
        }
        job2 = myScope.launch {
            printNNumnersWithDelay1()
        }
    }
    suspend fun printNNumnersWithDelay(){
        var i = 0
        while (true){
            println("Num : $i")
            delay(1000L)
            i++
        }
    }
    suspend fun printNNumnersWithDelay1(){
        var i = 100
        while (true){
            println("Num : $i")
            delay(1000L)
            i++
        }
    }

    fun StopCoroutineScope(view: View) {
        myScope.cancel() //Cancelled all coroutines within it
        println("myScope : "+myScope.isActive)
    }

    fun StopJob1(view: View) {
        job1.cancel()
    }
    fun StopJob2(view: View) {
        job2.cancel()
    }
}