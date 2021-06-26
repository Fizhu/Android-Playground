package com.fizhu.androidplayground.features.speechtotext

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.androidplayground.databinding.ActivitySttBinding
import com.fizhu.androidplayground.utils.toast
import java.util.*

/**
 * Created by fizhu on 25 June 2021
 * https://github.com/Fizhu
 */
class SpeechToTextActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySttBinding
    private var languange = BAHASA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySttBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        with(binding) {
            toolbar.setNavigationOnClickListener { finish() }
            toogle.addOnButtonCheckedListener { _, checkedId, _ ->
                languange = when (checkedId) {
                    binding.button1.id -> BAHASA
                    binding.button2.id -> ENGLISH
                    else -> Locale.getDefault()
                }
                toast(languange.toString())
            }
            til.setEndIconOnClickListener {
                startSTT()
            }
        }
    }

    private val sttResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.let { data ->
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                if (!result.isNullOrEmpty()) {
                    binding.et.setText(result[0])
                }
            }
        }

    private fun startSTT() {
        val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languange)
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now !")
        try {
            sttResultLauncher.launch(i)
        } catch (e: ActivityNotFoundException) {
            toast("Your device does not support STT.")
        }
    }

    companion object {
        private val BAHASA = Locale("in", "ID")
        private val ENGLISH = Locale.ENGLISH
    }

}