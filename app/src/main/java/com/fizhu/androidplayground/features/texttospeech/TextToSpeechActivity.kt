package com.fizhu.androidplayground.features.texttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.androidplayground.databinding.ActivityTtsBinding
import java.util.*

/**
 * Created by fizhu on 25 June 2021
 * https://github.com/Fizhu
 */
class TextToSpeechActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityTtsBinding
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTtsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        textToSpeech = TextToSpeech(this, this)
        with(binding) {
            toolbar.setNavigationOnClickListener { finish() }
            btnSpeak.setOnClickListener {
                val text = et.text.toString()
                if (text.isNotEmpty() && text != "") {
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
                }
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "onInit: The Language specified is not supported!")
            } else {
                binding.btnSpeak.isEnabled = true
            }
        }
    }

    override fun onDestroy() {
        textToSpeech.stop()
        textToSpeech.shutdown()
        super.onDestroy()
    }

}