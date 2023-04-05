package com

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.realtimecoroutineretrofit.ApiHelper
import com.realtimecoroutineretrofit.exception_exceptionhandler.ExceptionHandlerViewModel
import com.realtimecoroutineretrofit.exception_supervisor.IgnoreErrorAndContinueViewModel
import com.realtimecoroutineretrofit.exception_trycatch.TryCatchViewModel
import com.realtimecoroutineretrofit.parallel.ParallelNetworkCallsViewModel
import com.realtimecoroutineretrofit.series.SeriesNetworkCallsViewModel
import com.realtimecoroutineretrofit.single.SingleNetworkCallViewModel
import com.roomcoroutine.DatabaseHelper
import com.roomcoroutine.RoomDBViewModel

class ViewModelFactory1(private val apiHelper: ApiHelper,private val dbHelper: DatabaseHelper) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper,dbHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}
