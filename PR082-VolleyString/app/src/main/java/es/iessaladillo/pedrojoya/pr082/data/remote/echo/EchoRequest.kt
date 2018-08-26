package es.iessaladillo.pedrojoya.pr082.data.remote.echo

import com.android.volley.Request
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest

private const val URL_ECO = "http://www.informaticasaladillo.es/echo.php"

class EchoRequest(private val params: Map<String, String>, listener: Listener<String>,
                           errorListener: ErrorListener) : StringRequest(Request.Method.POST, URL_ECO, listener, errorListener) {

    override fun getParams(): Map<String, String> {
        return params
    }

    override fun getTag(): Any {
        return ECHO_TAG
    }

    companion object {
        const val ECHO_TAG = "ECHO_TAG"
    }

}
