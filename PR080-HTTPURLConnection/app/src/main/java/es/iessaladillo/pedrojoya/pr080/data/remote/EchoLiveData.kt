package es.iessaladillo.pedrojoya.pr080.data.remote

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.RequestState
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

private const val KEY_NAME = "nombre"
private const val KEY_DATE = "fecha"

class EchoLiveData : MutableLiveData<RequestState>() {

    private var task: AsyncTask<String, Void, Void?>? = null

    fun requestEcho(text: String) {
        task = EchoAsyncTask().execute(text)
    }

    fun cancel() {
        task?.cancel(true)
    }

    @SuppressLint("StaticFieldLeak")
    inner class EchoAsyncTask : AsyncTask<String, Void, Void?>() {

        private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

        override fun doInBackground(vararg params: String): Void? {
            postValue(RequestState.Loading(true))
            // Simulate latency
            Thread.sleep(2000)
            val connection = URL("http://www.informaticasaladillo.es/echo.php").openConnection()
                    as HttpURLConnection
            connection.run {
                try {
                    requestMethod = "POST"
                    doInput = true
                    doOutput = true
                    PrintWriter(connection.outputStream).use {
                        it.write(KEY_NAME + "=" + URLEncoder.encode(params[0], "UTF-8"))
                        it.write("&$KEY_DATE=" + URLEncoder.encode(simpleDateFormat.format(Date()), "UTF-8"))
                        it.flush()
                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        postValue(RequestState.Result(Event(
                                inputStream.use {
                                    it.bufferedReader().use { reader ->
                                        reader.readText().trim()
                                    }
                                }
                        )))
                    } else {
                        postValue(RequestState.Error(Event(Exception(responseMessage))))
                    }
                } catch (e: Exception) {
                    postValue(RequestState.Error(Event(e)))
                }
            }
            return null
        }

    }

}