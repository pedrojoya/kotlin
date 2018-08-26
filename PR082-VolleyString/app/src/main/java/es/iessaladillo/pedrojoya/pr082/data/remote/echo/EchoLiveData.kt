package es.iessaladillo.pedrojoya.pr082.data.remote.echo

import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.Response
import es.iessaladillo.pedrojoya.pr082.base.Event
import es.iessaladillo.pedrojoya.pr082.base.RequestState
import java.text.SimpleDateFormat
import java.util.*

private const val KEY_NAME = "nombre"
private const val KEY_DATE = "fecha"

class EchoLiveData(private val requestQueue: RequestQueue) : MutableLiveData<RequestState>() {
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

    fun requestEcho(text: String) {
        try {
            postValue(RequestState.Loading(true))
            val params = HashMap<String, String>()
            params[KEY_NAME] = text
            params[KEY_DATE] = simpleDateFormat.format(Date())
            requestQueue.add(EchoRequest(params,
                    Response.Listener { response ->
                        postValue(RequestState.Result(
                                Event(response.trim())))
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
        requestQueue.cancelAll(EchoRequest.ECHO_TAG)
    }

}