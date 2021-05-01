package com.fizhu.androidplayground.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.fizhu.androidplayground.alarm.AlarmNotificationUtil.createNotificationChannel

/**
 * Created by hafizhanbiya on 30,April,2021
 * https://github.com/Fizhu
 */
class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (context != null) {
                createNotificationChannel(context)
                context.stopService(Intent(context, AlarmService::class.java))
            }
        }
    }
}