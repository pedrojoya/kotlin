package es.iessaladillo.pedrojoya.pr080.data.remote

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.RequestState
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

private const val SEARCH_URL = "https://www.google.es/search?hl=es&q=\""

class SearchLiveData: MutableLiveData<RequestState>() {

    private var task: AsyncTask<String, Void, Void?>? = null

    fun search(text: String) {
        task = SearchAsyncTask().execute(text)
    }

    fun cancel() {
        task?.cancel(true)
    }

    @SuppressLint("StaticFieldLeak")
    inner class SearchAsyncTask : AsyncTask<String, Void, Void?>() {

        override fun doInBackground(vararg params: String): Void? {
            postValue(RequestState.Loading(true))
            // Simulate latency
            Thread.sleep(2000)
            val url = URL("$SEARCH_URL${URLEncoder.encode(params[0], "UTF-8")}\"")
            val connection = url.openConnection() as HttpURLConnection
            try {
                connection.run {
                    requestMethod = "GET"
                    setRequestProperty("User-Agent",
                            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)")
                    connect()
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        postValue(RequestState.Result(Event(extractResultFromContent(
                                inputStream.use {
                                    it.bufferedReader().use { reader ->
                                        reader.readText()
                                    }
                                }
                        ))))
                    } else {
                        postValue(RequestState.Error(Event(Exception(responseMessage))))
                    }
                }
            } catch (e: Exception) {
                postValue(RequestState.Error(Event(e)))
            }
            return null
        }

        private fun extractResultFromContent(content: String): String {
            var result = ""
            val ini = content.indexOf("Aproximadamente")
            if (ini != -1) {
                // Se busca el siguiente espacio en blanco después de
                // Aproximadamente.
                val end = content.indexOf(" ", ini + 16)
                // El resultado corresponde a lo que sigue a
                // Aproximadamente, hasta el siguiente espacio en blanco.
                result = content.substring(ini + 16, end)
            }
            return result
        }

    }

}