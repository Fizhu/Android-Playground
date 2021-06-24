package com.fizhu.androidplayground

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.androidplayground.databinding.ActivityMainBinding
import com.fizhu.androidplayground.features.uploadfile.UploadFileActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        with(binding) {
            btnAlarmManager.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        AlarmActivity::class.java
                    )
                )
            }
            btnUploadFile.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        UploadFileActivity::class.java
                    )
                )
            }
        }
    }
}