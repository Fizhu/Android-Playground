package com.fizhu.androidplayground.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.androidplayground.databinding.ActivityDeeplinkBinding

/**
 * Created by fizhu on 23 June 2021
 * https://github.com/Fizhu
 */

/**
 * For now we only handle deeplinking from https/androidplayground.com/deeplink
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
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        val appLinkAction: String? = intent?.action
        val appLinkData: Uri? = intent?.data
        showDeepLinkOffer(appLinkAction, appLinkData)
    }

    private fun showDeepLinkOffer(appLinkAction: String?, appLinkData: Uri?) {
        if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null) {
            val data = appLinkData.getQueryParameter("data")
            data?.let {
                binding.tvData.text = it
            }

        }
    }

}