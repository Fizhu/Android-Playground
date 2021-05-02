package com.fizhu.androidplayground.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.fizhu.androidplayground.MainActivity
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

        val musicUrl = "https://ia801804.us.archive.org/11/items/MisharyRasyidPerJuz/Mishary/01.mp3"
//        AlarmMediaPlayer.playMusic(context)
        AlarmMediaPlayer.playMusicFromUrl(context, musicUrl)

        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_SINGLE_TOP)


        val stopMusicIntent = Intent(context, AlarmPlayerStopper::class.java)
        val builder = NotificationCompat.Builder(context, "ChannelId")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Alarm nya idupppp !!!!!")
            .setContentText("Swipe to dismiss")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setContentIntent(
                PendingIntent.getBroadcast(
                    context,
                    0,
                    notificationIntent,
                    0
                )
            )
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