package com.example.webviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_second, container, false)

        val myWebView: WebView = view.findViewById(R.id.webview)
        myWebView.settings.javaScriptEnabled = true // JavaScriptを有効にする
        WebView.setWebContentsDebuggingEnabled(true) // インスタンスではなく、プロパティメソッド

        val postData = ""
        myWebView.postUrl("http://10.0.2.2:8080/", postData.toByteArray());

        val id = "1234567890"
        val name = "fiotto"

        myWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(webView: WebView, url: String) {
                super.onPageFinished(webView, url)

                val script = "window.setValue('$id','$name')"
                println(script)

                webView.evaluateJavascript(script, null)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}