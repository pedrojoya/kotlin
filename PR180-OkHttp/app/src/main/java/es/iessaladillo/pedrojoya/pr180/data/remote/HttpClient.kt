package es.iessaladillo.pedrojoya.pr180.data.remote

import android.content.Context

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor

import okhttp3.OkHttpClient

object HttpClient {

    private var instance: OkHttpClient? = null

    fun getInstance(context: Context): OkHttpClient {
        if (instance == null) {
            synchronized(HttpClient::class.java) {
                if (instance == null) {
                    instance = buildOkHttpClient(context.applicationContext)
                }
            }
        }
        return instance!!
    }

    private fun buildOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder().addNetworkInterceptor(
                StethoInterceptor()).addInterceptor(ChuckInterceptor(context)).build()
    }

}
