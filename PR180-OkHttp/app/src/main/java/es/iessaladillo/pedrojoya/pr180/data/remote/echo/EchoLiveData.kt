package es.iessaladillo.pedrojoya.pr180.data.remote.echo

import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr180.base.Event
import es.iessaladillo.pedrojoya.pr180.base.RequestState
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

private const val KEY_NAME = "nombre"
private const val KEY_DATE = "fecha"

private const val ECHO_URL = "http://www.informaticasaladillo.es/echo.php"

class EchoLiveData(private val okHttpClient: OkHttpClient) : MutableLiveData<RequestState>() {

    private val simpleDateFormat = SimpleDateFormat(
            "dd/MM/yyyy " + "HH:mm:ss", Locale.getDefault())
    private var echoCall: Call? = null

    fun requestEcho(text: String) {
        try {
            postValue(RequestState.Loading(true))
            val url = URL(ECHO_URL)
            val formBody = FormBody.Builder().addEncoded(KEY_NAME, text).addEncoded(
                    KEY_DATE, simpleDateFormat.format(Date())).build()
            val request = Request.Builder().url(url).post(formBody).build()
            echoCall = okHttpClient.newCall(request)
            echoCall!!.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    postValue(RequestState.Error(Event(e)))
                }

                override fun onResponse(call: Call, response: Response) {
                    // Simulate latency
                    try {
                        Thread.sleep(2000)
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                val content = responseBody.string().trim { it <= ' ' }
                                postValue(RequestState.Result(Event(content)))
                            }
                        } else {
                            postValue(RequestState.Error(Event(Exception(response.message()))))
                        }
                    } catch (e: Exception) {
                        postValue(RequestState.Error(Event(e)))
                    }
                }
            })
        } catch (e: Exception) {
            postValue(RequestState.Error(Event(e)))
        }
    }

    fun cancel() {
        echoCall?.let {
            it.cancel()
            echoCall = null
        }
    }

}