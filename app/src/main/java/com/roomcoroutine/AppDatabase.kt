package com.roomcoroutine

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roomcoroutine.dao.UserDao
import com.roomcoroutine.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}