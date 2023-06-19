package com.supervisorjob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlincoroutinesdemo.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SupervisorJobLaunchActivity : AppCompatActivity() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _,e ->
        when(e){
            is Job1Exception -> println("Job1 Exception : ${e.message}")
            is Job2Exception -> println("Job2 Exception : ${e.message}")
            else -> println("My Exception : ${e.message}")
        }
    }
    private val coroutineContext: CoroutineContext = Dispatchers.Main + Job() + coroutineExceptionHandler
    private val coroutineScope : CoroutineScope = CoroutineScope(coroutineContext)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervisor_job_launch)
    }

    fun funLaunchSupervisorJob(view: View) {
        println("Program Start")
        coroutineScope.launch {
            // supervisorScope = CoroutineScope + SupervisorJob()
            supervisorScope { //Create a sub scope with supervisor job
                var job1 = launch {
                    println("Job1 Start")
                    var i = 0
                    var fgJob1 = true
                    while (fgJob1) {
                        i++
                        println("Job1 Count : $i")
                        delay(2000)
                        if (i == 10)
                            fgJob1 = false
                    }
                    println("Job1 End")
                }
                var job2 = launch {
                    println("Job2 Start")
                    delay(1000L)
                    throw Exception("Invalid Account!")
                    println("Job2 End")
                }

                var list = listOf(job1, job2)
                list.forEach {
                        it.join()
                }
            }
        }
        println("Program End")
    }

    fun funAsycExceptionHandler(view: View) {
        coroutineScope.launch {
            supervisorScope {
                var job1 = async {
                    println("Job1 Start")
                    var i = 0
                    var fgJob1 = true
                    while (fgJob1) {
                        i++
                        println("Job1 Count : $i")
                        delay(2000)
                        if (i == 10)
                            fgJob1 = false
                    }
                    println("Job1 End")
                }
                var job2 = async {
                    println("Job2 Start")
                    delay(1000L)
                    throw Job2Exception("Invalid Account!")
                    println("Job2 End")
                }
                job1.await()
                job2.await()
            }

        }
    }
}

private class Job1Exception(var msg:String) : Exception(msg)
private class Job2Exception(var msg:String) : Exception(msg)