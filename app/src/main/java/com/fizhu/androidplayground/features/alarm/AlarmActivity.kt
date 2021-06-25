package com.fizhu.androidplayground.features.alarm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
            btnStart.setOnClickListener {
                startService(Intent(this@AlarmActivity, AlarmService::class.java))
                Toast.makeText(this@AlarmActivity, "Alarm will rang in 15 sec", Toast.LENGTH_SHORT).show()
            }
        }
    }
}