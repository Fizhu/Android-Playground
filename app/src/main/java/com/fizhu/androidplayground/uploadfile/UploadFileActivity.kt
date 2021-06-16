package com.fizhu.androidplayground.uploadfile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fizhu.androidplayground.databinding.ActivityUploadFileBinding
import java.io.File

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
                chooseFile()
            }
        }
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val i = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            i.addCategory("android.intent.category.DEFAULT")
            i.data = Uri.parse(
                String.format(
                    "package:%s",
                    this.applicationContext.packageName
                )
            )
            openFileLauncher.launch(i)
        } else {
            requestPermissionLauncher.launch(permissionList)
        }
    }

    private val permissionList = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_FILE) {
            if (grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                openFileChooser()
            } else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it[permissionList[0]] == true && it[permissionList[1]] == true) {
            chooseFile()
        } else {
            Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show()
        }
    }

    private val openFileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.data?.let { data ->
                val path = RealPathUtil.getFilePath(this, data)
                var file: File? = null
                path?.let { p ->
                    file = File(p)
                }
                binding.tvName.text = "Path : $path\nFile Name : ${file?.name} "
            }
        }
    }


    private fun chooseFile() {
        if (ContextCompat.checkSelfPermission(
                this,
                permissionList[0]
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                permissionList[1]
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openFileChooser()
        } else checkPermission()
    }

    private fun openFileChooser() {
        var i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "*/*"
        i = Intent.createChooser(i, "Pilih File")
        openFileLauncher.launch(i)
    }

    companion object {
        const val PERMISSION_REQUEST_FILE = 5566
    }
}