package com.realtimecoroutineretrofit

import com.realtimecoroutineretrofit.model.ApiUser
import retrofit2.http.GET

interface ApiHelper {
    suspend fun getUsers() :  List<ApiUser>
    suspend fun getMoreUsers(): List<ApiUser>
    suspend fun getUsersWithError(): List<ApiUser>
}