package com.fizhu.androidplayground.alarm

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import com.fizhu.androidplayground.MainActivity
import com.fizhu.androidplayground.R

/**
 * Created by hafizhanbiya on 02,May,2021
 * https://github.com/Fizhu
 */
class AlarmRingingService : Service() {
    private var vibrator: Vibrator? = null

    override fun onCreate() {
        super.onCreate()
        vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val musicUrl = "https://ia801804.us.archive.org/11/items/MisharyRasyidPerJuz/Mishary/01.mp3"
        AlarmMediaPlayer.playMusicFromUrl(this, musicUrl)

        val pattern = longArrayOf(0, 100, 1000, 1000, 1000, 1000, 1000, 1000)
        vibrator?.vibrate(pattern, 0)

        startForeground(1, createNotification(this))

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotification(context: Context): Notification {
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val stopMusicIntent = Intent(context, AlarmPlayerStopper::class.java)
        return NotificationCompat.Builder(context, "ChannelId")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Alarm nya idupppp !!!!!")
            .setContentText("Tap to dismiss")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(
                PendingIntent.getBroadcast(
                    context,
                    1,
                    stopMusicIntent,
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
            ).build()
    }

    override fun onDestroy() {
        super.onDestroy()
        AlarmMediaPlayer.stopMusic()
        vibrator?.cancel()
    }
}