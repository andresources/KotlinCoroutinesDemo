package com.exceptions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.kotlincoroutinesdemo.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineExceptionHandlernActivity : AppCompatActivity() {
    /*AppCompatActivity(),CoroutineScope
    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + Job()*/

    companion object{
        private const val TAG = "ExceptionsActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_exception_handlern)
    }

    fun funLaunchException(view: View) {
        lifecycleScope.launch {
            Log.i(TAG,"Started")
            throw Exception("My Error")
            Log.i(TAG,"Ended")
        }
    }
    fun funAsyncException(view: View) {
        lifecycleScope.async{
            Log.i(TAG,"Started")
            throw Exception("My Error")
            Log.i(TAG,"Ended")
        }
    }
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i(TAG,"Issue : ${throwable.message}")
    }
    fun funLaunchCoroutineExceptionHandler(view: View) {
        lifecycleScope.launch(exceptionHandler) {
            Log.i(TAG,"Started")
            throw Exception("Invalid Acc. No")
            Log.i(TAG,"Ended")
        }
    }

    fun funAsyncCoroutineExceptionHandler(view: View) {
        lifecycleScope.launch {
            var myJob = lifecycleScope.async(exceptionHandler) {
                Log.i(TAG,"Started")
                throw Exception("Invalid Mob. No")
                Log.i(TAG,"Ended")
            }

            myJob.await()
        }

    }
}