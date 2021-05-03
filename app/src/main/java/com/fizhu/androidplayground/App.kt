package com.fizhu.androidplayground

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate


/**
 * Created by fizhu on 06,October,2020
 * https://github.com/Fizhu
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        singleton = this
        createNotificationChannnel()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun createNotificationChannnel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
                /**
                 * The application [Context] made static.
                 * Do **NOT** use this as the context for a view,
                 * this is mostly used to simplify calling of resources
                 * (esp. String resources) outside activities.
                 */
        var context: Context? = null
            private set

        @SuppressLint("StaticFieldLeak")
        var singleton: App? = null
            private set

        private val getInstance: App?
            get() = singleton

        const val CHANNEL_ID = "ALARM_SERVICE_CHANNEL"
    }
}