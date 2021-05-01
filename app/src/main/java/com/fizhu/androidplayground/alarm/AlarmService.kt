package com.fizhu.androidplayground.alarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hafizhanbiya on 30,April,2021
 * https://github.com/Fizhu
 */
class AlarmService : Service() {

    override fun onCreate() {
        super.onCreate()
        setScheduleNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("SimpleDateFormat")
    fun setScheduleNotification() {
        // membuat objek calendar dan inisialisasi parameter waktunya
        val calendar = Calendar.getInstance()
//        val hour = 20
//        val minute = 29
//        val second = 0

        // lakukan konfigurasi berdasarkan waktu yang sudah ditetapkan sebelumnya
        calendar.apply {
            set(Calendar.SECOND, calendar.time.seconds + 15)
        }

        // membuat objek intent yang mana akan menjadi target selanjutnya
        // bisa untuk berpindah halaman dengan dan tanpa data
        val intent = Intent(applicationContext, AlarmBroadcastReceiver::class.java)
//        intent.putExtra("validationTime", SimpleDateFormat("HH:mm:ss").format(calendar.time))

        // membuat objek PendingIntent yang berguna sebagai penampung intent dan aksi yang akan dikerjakan
        val requestCode = 0
        val pendingIntent =
            PendingIntent.getBroadcast(applicationContext, requestCode, intent, 0)

        // membuat objek AlarmManager untuk melakukan pendaftaran alarm yang akan dijadwalkan
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // kita buat alarm yang dapat berfungsi tepat waktu dan juga walaupun dalam kondisi HP idle
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }
}