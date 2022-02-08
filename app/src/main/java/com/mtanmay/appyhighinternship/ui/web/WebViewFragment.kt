package com.mtanmay.appyhighinternship.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mtanmay.appyhighinternship.R
import com.mtanmay.appyhighinternship.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private lateinit var binding: FragmentWebViewBinding
    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        val url = args.url

        val webView = binding.webView
        val webSetting = binding.webView.settings
        webSetting.apply {
            builtInZoomControls = false
            javaScriptEnabled = true
        }

        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        return binding.root
    }

}