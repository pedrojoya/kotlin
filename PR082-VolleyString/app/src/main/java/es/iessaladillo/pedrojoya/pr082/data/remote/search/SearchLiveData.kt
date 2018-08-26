package es.iessaladillo.pedrojoya.pr082.data.remote.search

import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.Response
import es.iessaladillo.pedrojoya.pr082.base.Event
import es.iessaladillo.pedrojoya.pr082.base.RequestState
import java.net.URLEncoder

class SearchLiveData(private val requestQueue: RequestQueue) : MutableLiveData<RequestState>() {

    fun search(text: String) {
        try {
            postValue(RequestState.Loading(true))
            requestQueue.add(SearchRequest(URLEncoder.encode(text, "UTF-8"),
                    Response.Listener { response ->
                        postValue(RequestState.Result(
                                Event(extractResultFromContent(response))))
                        // Simulate delay
                        try {
                            Thread.sleep(4000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener { volleyError ->
                        postValue(RequestState.Error(
                                Event(Exception(volleyError.message))))
                    }
            ))
        } catch (e: Exception) {
            postValue(RequestState.Error(Event(e)))
        }

    }

    fun cancel() {
        requestQueue.cancelAll(SearchRequest.SEARCH_TAG)
    }

    private fun extractResultFromContent(content: String): String {
        var result = ""
        val ini = content.indexOf("Aproximadamente")
        if (ini != -1) {
            val end = content.indexOf(" ", ini + 16)
            result = content.substring(ini + 16, end)
        }
        return result
    }

}