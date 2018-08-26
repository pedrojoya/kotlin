package es.iessaladillo.pedrojoya.pr082.data.remote.search

import com.android.volley.Request
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import java.util.*

class SearchRequest(nombre: String, listener: Listener<String>,
                             errorListener: ErrorListener) : StringRequest(Request.Method.GET, "https://www.google.es/search?hl=es&q=\""
        + nombre + "\"", listener, errorListener) {

    override fun getHeaders(): Map<String, String> {
        val params = HashMap<String, String>()
        params["User-Agent"] = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"
        return params
    }

    override fun getTag(): Any {
        return SEARCH_TAG
    }

    companion object {
        const val SEARCH_TAG = "SEARCH_TAG"
    }

}
