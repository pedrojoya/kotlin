package es.iessaladillo.pedrojoya.pr194.data.remote

import android.content.Context

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import es.iessaladillo.pedrojoya.pr194.base.SingletonHolder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
            addInterceptor(ChuckInterceptor(context))
            build()
        }
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(Api::class.java)
    }

    companion object : SingletonHolder<ApiService, Context>(::ApiService)

}
