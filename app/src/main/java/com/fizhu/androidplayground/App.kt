package com.fizhu.androidplayground

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
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

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
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
    }
}