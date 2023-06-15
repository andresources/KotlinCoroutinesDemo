package com.coroutinescope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlincoroutinesdemo.R
import kotlinx.coroutines.*

class ScopeCancellationActivity : AppCompatActivity() {
    private val myScope = getMyScope()
    private lateinit var job1:Job
    private lateinit var job2:Job
    private lateinit var jobParent:Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scope_cancellation)
    }

    fun funCoroutineScopeStart(view: View) {
        var job1Counter = 0
        var job2Counter = 0
        var fg_job1 = true
        var fg_job2 = true
        jobParent = myScope.launch {

            job1 = launch {
                while (fg_job1) {
                    job1Counter++
                    delay(1000L)
                    println("Job1 : $job1Counter coroutineScope : ${myScope.isActive}")
                    if (job1Counter == 5) {
                        //fg_job1 = false
                        throw Exception("Teest")
                    }
                }
            }
            job2 = launch {
                while (fg_job2) {
                    job2Counter++
                    delay(2000L)
                    println("Job2 : $job2Counter  coroutineScope : ${myScope.isActive}")
                    if (job2Counter == 5) {
                        fg_job2 = false
                    }
                }
            }

        }

        jobParent.invokeOnCompletion {
            println("Scope is completed - ${myScope.isActive}, cancel Status: ${jobParent.isCancelled} Complete Status: ${jobParent.isCompleted}")
        }

    }
    fun funCoroutineScopeStop(view: View) {
        //For Cancel : isCancelled - true and isCompleted - true
        //For Completed : isCancelled - false and isCompleted - true
        jobParent.invokeOnCompletion {
            println("Scope is stopped, cancel Status: ${jobParent.isCancelled} Complete Status: ${jobParent.isCompleted}")
        }
    }

    fun funJob1Stop(view: View) {
        job1.cancel()
    }
    fun funJob2Stop(view: View) {
        job2.cancel()

    }
}