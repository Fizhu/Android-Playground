package com.fizhu.androidplayground

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizhu.androidplayground.databinding.ActivityMainBinding
import com.fizhu.androidplayground.features.alarm.AlarmActivity
import com.fizhu.androidplayground.features.speechtotext.SpeechToTextActivity
import com.fizhu.androidplayground.features.texttospeech.TextToSpeechActivity
import com.fizhu.androidplayground.features.uploadfile.UploadFileActivity
import com.fizhu.androidplayground.features.webview.WebviewActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainMenuAdapter by lazy { MainMenuAdapter { goto(it) } }

    private val listFeatures = listOf(
        AlarmActivity::class.java,
        UploadFileActivity::class.java,
        TextToSpeechActivity::class.java,
        SpeechToTextActivity::class.java,
        WebviewActivity::class.java
    )

    private val listFeatureTitle = listOf(
        R.string.alarm_manager,
        R.string.upload,
        R.string.text_to_speech,
        R.string.speech_to_text,
        R.string.webview
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        with(binding) {
            rv.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = mainMenuAdapter
            }
            mainMenuAdapter.setData(listFeatureTitle)
        }
    }

    private fun goto(pos: Int) {
        startActivity(Intent(this, listFeatures[pos]))
    }

}