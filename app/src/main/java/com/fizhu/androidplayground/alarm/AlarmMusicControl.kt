package com.fizhu.androidplayground.alarm

import android.content.Context

import android.media.MediaPlayer
import android.media.RingtoneManager


/**
 * Created by hafizhanbiya on 01,May,2021
 * https://github.com/Fizhu
 */
class AlarmMusicControl constructor(private val context: Context?) {
    private var sInstance: AlarmMusicControl? = null
    private var mMediaPlayer: MediaPlayer? = null

    fun getInstance(): AlarmMusicControl? {
        if (sInstance == null) {
            sInstance = AlarmMusicControl(context)
        }
        return sInstance
    }

    fun playMusic() {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        mMediaPlayer = MediaPlayer.create(context, alarmSound)
        mMediaPlayer?.start()
    }

    fun stopMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.stop()
            mMediaPlayer?.seekTo(0)
        }
    }
}