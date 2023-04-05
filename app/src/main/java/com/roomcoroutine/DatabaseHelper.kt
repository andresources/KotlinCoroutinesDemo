package com.roomcoroutine

import com.roomcoroutine.entity.User

interface DatabaseHelper {
    suspend fun getUsers(): List<User>
    suspend fun insertAll(users: List<User>)
}