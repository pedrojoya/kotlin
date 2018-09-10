package es.iessaladillo.pedrojoya.pr169.data.remote

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import es.iessaladillo.pedrojoya.pr169.BuildConfig
import es.iessaladillo.pedrojoya.pr169.base.SingletonHolder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/"
const val PARAM_API_KEY = "key"
//    public static final String API_KEY = "trnsl.1.1.20160220T103052Z.21098ec5be47c8b8"
//            + ".8d34747b27b8d2bb59a223a1f49d50d2265b80e3";
const val PARAM_TEXT = "text"
const val PARAM_LANG = "lang"
const val LANG = "es-en"

class ApiService private constructor(context: Context) {

    var api: Api = buildAPIService(context)
        private set

    private fun buildAPIService(context: Context): Api {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(
                httpLoggingInterceptor).addInterceptor(StethoInterceptor()).addInterceptor(
                ChuckInterceptor(context)).build()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(Api::class.java)
    }

    @Suppress("unused")
    private fun addApiKey(builder: OkHttpClient.Builder) {
        builder.addInterceptor { chain ->
            val request = chain.request()
            val newUrl = request.url().newBuilder().addQueryParameter(PARAM_API_KEY,
                    BuildConfig.YANDEX_API_KEY).build()
            val newRequest: Request
            newRequest = request.newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }
    }

    companion object: SingletonHolder<ApiService, Context>(::ApiService)

}
