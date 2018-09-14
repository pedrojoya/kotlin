package es.iessaladillo.pedrojoya.pr083.data.remote

import androidx.lifecycle.LiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.iessaladillo.pedrojoya.pr083.base.Event
import es.iessaladillo.pedrojoya.pr083.base.RequestState
import es.iessaladillo.pedrojoya.pr083.data.model.Student
import org.json.JSONArray
import org.json.JSONException
import java.util.*

private const val TAG_STUDENTS = "TAG_STUDENTS"
private const val DATA_URL = "http://www.informaticasaladillo.es/datos.json"

class StudentsLiveData(private val requestQueue: RequestQueue) : LiveData<RequestState>() {

    init {
        loadData()
    }

    fun loadData() {
        //sendJsonRequest();
        sendGsonRequest()
    }

    @Suppress("unused")
    private fun sendJsonRequest() {
        postValue(RequestState.Loading(true))
        val request = JsonArrayRequest(DATA_URL, { response ->
            try {
                postValue(RequestState.Result(parseJson(response)))
            } catch (e: Exception) {
                postValue(RequestState.Error(Event(e)))
            }
        }, { error -> postValue(RequestState.Error(Event(Exception(error.message)))) })
        request.tag = TAG_STUDENTS
        requestQueue.add(request)
    }

    private fun sendGsonRequest() {
        postValue(RequestState.Loading(true))
        val gson = Gson()
        val type = object : TypeToken<List<Student>>() {

        }.type

        requestQueue.add(GsonArrayRequest<Any>(Request.Method.GET, DATA_URL, type,
                Response.Listener { response ->
                    @Suppress("UNCHECKED_CAST")
                    postValue(RequestState.Result(response as List<Student>))
                },
                Response.ErrorListener { error ->
                    postValue(RequestState.Error(Event(Exception(error.message))))
                },
                gson))
    }

    @Throws(JSONException::class)
    private fun parseJson(studentsJSONArray: JSONArray): List<Student> {
        val students = ArrayList<Student>()
        for (i in 0 until studentsJSONArray.length()) {
            students.add(Student(studentsJSONArray.getJSONObject(i)))
        }
        return students
    }

    fun cancel() {
        requestQueue.cancelAll(TAG_STUDENTS)
    }

}
