package com.fizhu.androidplayground.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast

/**
 * Created by hafizhanbiya on 30,April,2021
 * https://github.com/Fizhu
 */
class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
                context?.startService(Intent(context, AlarmService::class.java));
                Toast.makeText(context, "Alarm Restarted", Toast.LENGTH_SHORT).show()
            } else {
                startAlarm(context)
            }
        }
    }

    private fun startAlarm(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context?.startForegroundService(Intent(context, AlarmRingingService::class.java));
        } else {
            context?.startService(Intent(context, AlarmRingingService::class.java));
        }
        context?.stopService(Intent(context, AlarmService::class.java))
    }
}