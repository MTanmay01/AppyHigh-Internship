package com.mtanmay.appyhighinternship.ui.web

import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
        if (url != null)
            view.loadUrl(url)
        return true
    }
}