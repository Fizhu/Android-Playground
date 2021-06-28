package com.fizhu.androidplayground.features.webview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.androidplayground.databinding.ActivityWebviewBinding

/**
 * Created by fizhu on 28 June 2021
 * https://github.com/Fizhu
 */
class WebviewActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding
    private var uploadMessage: ValueCallback<Uri>? = null
    private var uploadMessageAboveL: ValueCallback<Array<Uri>>? = null
    private var htmlString = "<p><span style=\"font-size: 18px;\"><strong>Berikut adalah:</strong></span></p>\n<p></p>\n<iframe width=\"auto\" height=\"auto\" src=\"https://www.youtube.com/embed/gyGsPlt06bo\" frameBorder=\"0\"></iframe>\n<p></p>\n<iframe width=\"auto\" height=\"auto\" src=\"https://www.youtube.com/embed/gyGsPlt06bo\" frameBorder=\"0\"></iframe>\n<p><a href=\"https://www.youtube.com/embed/gyGsPlt06bo\" target=\"_blank\">Contoh</a> </p>"
    private var urlString = "https://fizhu.github.io/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }

//        initWebView(isHtm = true, htmlString)
        initWebView(isHtm = false, urlString)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(isHtm: Boolean, url: String) {
        binding.webview.settings.useWideViewPort = true
        binding.webview.settings.loadWithOverviewMode = true
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.allowFileAccess = true
        binding.webview.settings.allowContentAccess = true
        binding.webview.settings.domStorageEnabled = true
        binding.webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.progressBar.progress = newProgress
                binding.progressBar.visibility = if (newProgress == 100) View.GONE else View.VISIBLE
            }

            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                uploadMessageAboveL = filePathCallback
                openImageChooserActivity()
                return true
            }
        }
        if (isHtm) {
            binding.webview.loadData(url, "text/html", "UTF-8")
        } else {
            binding.webview.loadUrl(url)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return
            val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data)
            } else if (uploadMessage != null) {
                uploadMessage?.onReceiveValue(result)
                uploadMessage = null
            }
        }
    }

    private fun onActivityResultAboveL(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return
        var results: Array<Uri>? = null
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                val dataString = intent.dataString
                val clipData = intent.clipData
                if (clipData != null) {
                    results = Array(clipData.itemCount) { i ->
                        clipData.getItemAt(i).uri
                    }
                }
                if (dataString != null)
                    results = arrayOf(Uri.parse(dataString))
            }
        }
        uploadMessageAboveL?.onReceiveValue(results)
        uploadMessageAboveL = null
    }

    private fun openImageChooserActivity() {
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "*/*"
        startActivityForResult(Intent.createChooser(i, "File Chooser"), FILE_CHOOSER_RESULT_CODE)
    }

    companion object {
        private const val FILE_CHOOSER_RESULT_CODE = 10000
    }
}