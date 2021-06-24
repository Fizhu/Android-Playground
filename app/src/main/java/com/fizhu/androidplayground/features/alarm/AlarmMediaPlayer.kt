package com.fizhu.androidplayground.features.alarm

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast

/**
 * Created by hafizhanbiya on 02,May,2021
 * https://github.com/Fizhu
 */
object AlarmMediaPlayer {
    private var mMediaPlayer: MediaPlayer? = null

    fun playMusicFromUrl(context: Context?, musicUrl: String) {
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

    fun stopMusic() {
        mMediaPlayer?.stop()
        mMediaPlayer?.seekTo(0)
    }
}