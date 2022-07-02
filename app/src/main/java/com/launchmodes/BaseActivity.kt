package com.launchmodes

import androidx.appcompat.app.AppCompatActivity
import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo


open class BaseActivity : AppCompatActivity() {
    fun getActivityCount() : Int{
        val mngr = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val tasks = mngr.appTasks
        val taskList = mngr.getRunningTasks(10)
        taskList.size
        return taskList.get(0).numActivities
    }
}