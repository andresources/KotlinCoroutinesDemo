package com.kotlincoroutinesdemo

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

class A1_ViewModel : ViewModel() {
    //View Model is always executed in Main UI Thread...
    fun withContextFun(textView: TextView){
        //Suspend function 'withContext' should be called only from a coroutine or another suspend function
        var s1:String
        viewModelScope.launch(Dispatchers.IO) {
           val time= measureTimeMillis {
                val defRes1 = withContext(Dispatchers.IO) {
                    longRunningTaskOne()
                }
                val defRes2 = withContext(Dispatchers.IO) { longRunningTaskTwo()
                }
                s1 = defRes1+ defRes2
            }
            //If you use withContext(which return some result from a task.) ,then tasks are excuted in sequencially.
            Log.i("Time Taken","Time : "+time+ " Result : "+s1) //Takes 5 Secs
        }
    }
    fun asyncFun(tv:TextView){
        var s1:String
        viewModelScope.launch(Dispatchers.IO){
            val time= measureTimeMillis {
                val defRes1 = async{ longRunningTaskOne() }
                val defRes2 = async { longRunningTaskTwo() }
                s1 = defRes1.await()+ defRes2.await()
            }

            //If you use async coroutine builder ,then tasks are excuted in parally.
            Log.i("Time Taken","Time : "+time+ " Result : "+s1)
        }
    }
    //Runs a new coroutine and blocks the current thread interruptibly until its completion.
    fun runBlockingFun() = runBlocking{
        var s1:String
            val time= measureTimeMillis {
                val defRes1 = viewModelScope.async{ longRunningTaskOne() }
                val defRes2 = viewModelScope.async { longRunningTaskTwo() }
                s1 = defRes1.await()+ defRes2.await()
            }
//Suspend function 'await' should be called only from a coroutine or another suspend function
            //If you use async coroutine builder ,then tasks are excuted in parally.
            Log.i("Time Taken","Time : "+time+ " Result : "+s1)
    }

    suspend fun longRunningTaskOne() : String{
        delay(10000)
        return "Hello"
    }

    suspend fun longRunningTaskTwo() : String{
        delay(5000)
        return "World"
    }

}