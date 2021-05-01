package com.fizhu.androidplayground.alarm

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager


/**
 * Created by hafizhanbiya on 01,May,2021
 * https://github.com/Fizhu
 */
class AlarmMusicControl(private var context: Context?) {
    private var sInstance: AlarmMusicControl? = null
    private var mMediaPlayer: MediaPlayer? = null
    private var mContext: Context? = null

    init {
        mContext = context
    }

    fun getInstance(): AlarmMusicControl? {
        if (sInstance == null) {
            sInstance = AlarmMusicControl(mContext)
        }
        return sInstance
    }


    fun playMusic() {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        mMediaPlayer = MediaPlayer.create(mContext, alarmSound)
        mMediaPlayer?.start()
    }

    fun stopMusic() {
        mMediaPlayer?.stop()
        mMediaPlayer?.seekTo(0)

    }
}