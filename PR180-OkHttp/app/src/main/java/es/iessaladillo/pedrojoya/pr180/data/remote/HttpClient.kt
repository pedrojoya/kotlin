package es.iessaladillo.pedrojoya.pr180.data.remote

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import es.iessaladillo.pedrojoya.pr180.base.SingletonHolder
import okhttp3.OkHttpClient

object HttpClient: SingletonHolder<OkHttpClient, Context>(::buildOkHttpClient)

private fun buildOkHttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder().addNetworkInterceptor(
            StethoInterceptor()).addInterceptor(ChuckInterceptor(context)).build()
}
