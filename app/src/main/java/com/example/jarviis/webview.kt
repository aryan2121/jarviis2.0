package com.example.jarviis

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.DownloadListener
import android.webkit.WebView
import android.webkit.WebViewClient

class webview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        var  str:String =intent.getStringExtra("webVariable")

        val mywebView: WebView= findViewById(R.id.webview_id) // object created
        mywebView.loadUrl(str) // load url passed by main Activity

    mywebView.settings.javaScriptEnabled=true
        mywebView.webViewClient= WebViewClient()
        mywebView.setDownloadListener(DownloadListener  { url, userAgent, contentDisposition, mimtypr, contentLength ->
            val i=Intent(Intent.ACTION_VIEW)
            i .data= Uri.parse(url)
            startActivity(i)
        })
    }
}
