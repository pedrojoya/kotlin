@file:JvmName("WebViewExt")

package es.iessaladillo.pedrojoya.pr017.extensions

import android.webkit.WebView
import android.webkit.WebViewClient

inline fun WebView.onPageFinished(crossinline action: (WebView, String) -> Unit) {
    webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            action(view, url)
        }
    }
}


