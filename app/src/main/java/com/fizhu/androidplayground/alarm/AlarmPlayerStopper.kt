package com.fizhu.androidplayground.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by hafizhanbiya on 01,May,2021
 * https://github.com/Fizhu
 */
class AlarmPlayerStopper : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.stopService(Intent(p0, AlarmRingingService::class.java))
    }
}