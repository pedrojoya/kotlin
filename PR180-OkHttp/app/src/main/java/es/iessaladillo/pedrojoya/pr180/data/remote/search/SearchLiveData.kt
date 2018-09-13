package es.iessaladillo.pedrojoya.pr180.data.remote.search

import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr180.base.Event
import es.iessaladillo.pedrojoya.pr180.base.RequestState
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.net.URLEncoder

private const val SEARCH_URL = "https://www.google.es/search?hl=es&q=\""

class SearchLiveData(private val okHttpClient: OkHttpClient) : MutableLiveData<RequestState>() {

    private var searchCall: Call? = null

    fun search(text: String) {
        try {
            postValue(RequestState.Loading(true))
            val url = URL("$SEARCH_URL${URLEncoder.encode(text, "UTF-8")}\"")
            val request = Request.Builder().url(url).header("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5" + ".1)").build()
            searchCall = okHttpClient.newCall(request)
            searchCall!!.enqueue(object : Callback {
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
                                val content = responseBody.string()
                                postValue(RequestState.Result(
                                        Event(extractResultFromContent(content))))
                            }
                        } else {
                            postValue(RequestState.Error(
                                    Event(Exception(response.message()))))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        } catch (e: Exception) {
            postValue(RequestState.Error(Event(e)))
        }
    }

    private fun extractResultFromContent(content: String): String {
        var result = ""
        val ini = content.indexOf("Aproximadamente")
        if (ini != -1) {
            // Search for next blank space afeter Aproximadamente.
            val fin = content.indexOf(" ", ini + 16)
            // Result is after Aproximadamente until next blank space
            result = content.substring(ini + 16, fin)
        }
        return result
    }

    fun cancel() {
        searchCall?.let {
            it.cancel()
            searchCall = null
        }
    }

}