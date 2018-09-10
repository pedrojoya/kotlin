package es.iessaladillo.pedrojoya.pr140.data.remote


import android.content.Context

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import es.iessaladillo.pedrojoya.pr140.base.SingletonHolder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

private const val BASE_URL = "http://informaticasaladillo.es/android/elecciones/"

class ApiService private constructor(context: Context) {

    val api: Api = buildAPIService(context)

    private fun buildAPIService(context: Context): Api {
        val logInterceptor = HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logInterceptor)
                .addInterceptor(StethoInterceptor())
                .addInterceptor(ChuckInterceptor(context))
                .build()
        @Suppress("DEPRECATION")
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                SimpleXmlConverterFactory.create()).client(client).build()
        return retrofit.create(Api::class.java)
    }

    companion object: SingletonHolder<ApiService, Context>(::ApiService)

}
