package com.fizhu.androidplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.androidplayground.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        with(binding) {
            toolbar.setNavigationOnClickListener { finish() }
        }
    }
}