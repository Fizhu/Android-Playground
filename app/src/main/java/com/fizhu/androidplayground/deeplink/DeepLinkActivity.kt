package com.fizhu.androidplayground.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.androidplayground.databinding.ActivityDeeplinkBinding

/**
 * Created by fizhu on 23 June 2021
 * https://github.com/Fizhu
 */
class DeepLinkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeeplinkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeeplinkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        with(binding) {
        }
    }
}