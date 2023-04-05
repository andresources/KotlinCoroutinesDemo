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


class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(TryCatchViewModel::class.java)) {
            return TryCatchViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(ExceptionHandlerViewModel::class.java)) {
            return ExceptionHandlerViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(IgnoreErrorAndContinueViewModel::class.java)) {
            return IgnoreErrorAndContinueViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}