package com.kat4x.alyxnews.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kat4x.alyxnews.R
import kotlinx.android.synthetic.main.link_fragment.*

class LinkFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.link_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initUi()
    }

    private fun initViewModel() {
        activity?.let {
            viewModel = ViewModelProvider(it).get(NewsViewModel::class.java)
        } ?: throw Exception("Invalid activity")
    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun initUi() {

        Log.d("Link", "${viewModel.link.value}")
        web_view.webViewClient = WebViewClient()
        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true
        web_view.loadUrl(viewModel.link.value)
        web_view.canGoBack()
    }

}
