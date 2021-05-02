package com.fizhu.androidplayground.alarm

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager

/**
 * Created by hafizhanbiya on 02,May,2021
 * https://github.com/Fizhu
 */
object AlarmMediaPlayer {
    private var mMediaPlayer: MediaPlayer? = null

    fun playMusic(context: Context?) {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        mMediaPlayer = MediaPlayer.create(context, alarmSound)
        mMediaPlayer?.start()
    }

    fun stopMusic() {
        mMediaPlayer?.stop()
        mMediaPlayer?.seekTo(0)
    }
}