package com.fizhu.androidplayground.alarm

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Toast

/**
 * Created by hafizhanbiya on 02,May,2021
 * https://github.com/Fizhu
 */
object AlarmMediaPlayer {
    private var mMediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null

    fun playMusic(context: Context?) {
        startVibrator(context)
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        mMediaPlayer = MediaPlayer.create(context, alarmSound)
        mMediaPlayer?.start()
    }

    fun playMusicFromUrl(context: Context?, musicUrl: String) {
        startVibrator(context)
        try {
            mMediaPlayer = MediaPlayer()
            mMediaPlayer?.apply {
                setAudioAttributes(
                    AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                setDataSource(musicUrl)
                isLooping = true
                prepare()
            }
            mMediaPlayer?.start()
        } catch (e: Exception) {
            Log.e("Play Music Player", "playMusicFromUrl: $e")
            Toast.makeText(context, "Error while playing alarm music", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startVibrator(context: Context?) {
        vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator?.vibrate(
                VibrationEffect.createOneShot(
                    1000,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            );
        } else {
            vibrator?.vibrate(10000)
        }
    }

    fun stopMusic() {
        mMediaPlayer?.stop()
        mMediaPlayer?.seekTo(0)
    }
}