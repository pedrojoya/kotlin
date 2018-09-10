package es.iessaladillo.pedrojoya.pr195.data.remote

import android.content.Context

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import es.iessaladillo.pedrojoya.pr195.base.SingletonHolder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://www.informaticasaladillo.es/"

class ApiService private constructor(context: Context) {

    val api: Api = buildAPIService(context)

    private fun buildAPIService(context: Context): Api {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder().run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(StethoInterceptor())
            addInterceptor(ChuckInterceptor(context.applicationContext))
            build()
        }
        val retrofit = Retrofit.Builder().run {
            baseUrl(BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build()
        }
        return retrofit.create(Api::class.java)
    }

    companion object: SingletonHolder<ApiService, Context>(::ApiService)

}
