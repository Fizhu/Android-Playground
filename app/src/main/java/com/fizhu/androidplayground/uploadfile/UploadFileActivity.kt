package com.fizhu.androidplayground.uploadfile

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.androidplayground.databinding.ActivityUploadFileBinding

/**
 * Created by fizhu on 15 June 2021
 * https://github.com/Fizhu
 */
class UploadFileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadFileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        with(binding) {
            toolbar.setNavigationOnClickListener { finish() }
            btnStart.setOnClickListener {
                requestPermissionLauncher.launch(permissionList)
            }
        }
    }

    private val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it[permissionList[0]] == true && it[permissionList[1]] == true) {
            Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show()
        }
    }

    private fun chooseFile() {

    }
}