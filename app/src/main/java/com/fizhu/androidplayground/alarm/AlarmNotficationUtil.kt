package com.fizhu.androidplayground.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.fizhu.androidplayground.R

/**
 * Created by hafizhanbiya on 30,April,2021
 * https://github.com/Fizhu
 */

object AlarmNotificationUtil {

    /**
     * Creates a Notification channel, for OREO and higher.
     */
    fun createNotificationChannel(context: Context) {
        // Create a notification manager object.
        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        AlarmMusicControl(context).getInstance()?.playMusic()
        val stopMusicIntent = Intent(context, AlarmPlayerStopper::class.java)
        val builder = NotificationCompat.Builder(context, "ChannelId")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Alarm nya idupppp !!!!!")
            .setContentText("Tap to dismiss")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setDeleteIntent(
                PendingIntent.getBroadcast(
                    context,
                    0,
                    stopMusicIntent,
                    0
                )
            )

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(
                "ChannelId",
                "Stand up notification",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifies every 15 minutes to stand up and walk"
            mNotificationManager.createNotificationChannel(notificationChannel)
        }

        mNotificationManager.notify(0, builder.build())
    }

}