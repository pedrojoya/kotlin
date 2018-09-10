package es.iessaladillo.pedrojoya.pr083.data.remote

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson

import java.lang.reflect.Type

class GsonArrayRequest<T>(method: Int, url: String, private val type: Type,
                                   private val listener: Listener<T>, errorListener: ErrorListener, private val gson: Gson) : Request<T>(method, url, errorListener) {

    override fun parseNetworkResponse(response: NetworkResponse): Response<T> =
        try {
            // Se obtiene la cadena JSON a partir de la respuesta (con el
            // charset adecuado).
            val json = String(response.data,
                    charset(HttpHeaderParser.parseCharset(response.headers)))
            // Se procesa la cadena JSON.
            val data = gson.fromJson<T>(json, type)
            // Se crea y retorna la respuesta.
            Response.success(data,
                    HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }

    // Envía la respuesta al listener
    override fun deliverResponse(response: T) {
        // Se llama al método onResponse del listener pasándole la respuesta.
        listener.onResponse(response)
    }

}
