package es.iessaladillo.pedrojoya.pr040.data.remote

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import es.iessaladillo.pedrojoya.pr040.base.Event
import es.iessaladillo.pedrojoya.pr040.base.RequestState
import es.iessaladillo.pedrojoya.pr040.data.model.Student
import es.iessaladillo.pedrojoya.pr040.utils.NetworkUtils
import org.json.JSONArray
import org.json.JSONException
import java.util.*

private const val DATA_URL = "http://www.informaticasaladillo.es/datos.json"
private const val TIMEOUT = 5000

class StudentsLiveData : LiveData<RequestState>() {

    private var task: LoadStudentsAsyncTask? = null

    init {
        loadData()
    }

    fun loadData() {
        task = LoadStudentsAsyncTask()
        task!!.execute()
    }

    fun cancel() {
        task?.cancel(true)
    }

    @SuppressLint("StaticFieldLeak")
    inner class LoadStudentsAsyncTask : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            postValue(RequestState.Loading(true))
            val content: String
            val data: List<Student>
            try {
                // Simulate latency
                Thread.sleep(2000)
                content = NetworkUtils.loadUrl(DATA_URL, TIMEOUT)
                data = parseWithGson(content)
                postValue(RequestState.Result(data))
            } catch (e: Exception) {
                postValue(RequestState.Error(Event(e)))
            }
            return null
        }

        @Suppress("unused")
        @Throws(JSONException::class)
        private fun parseJson(content: String): List<Student> {
            val students = ArrayList<Student>()
            val studentsJSONArray = JSONArray(content)
            for (i in 0 until studentsJSONArray.length()) {
                students.add(Student(studentsJSONArray.getJSONObject(i)))
            }
            return students
        }

        @Throws(JsonSyntaxException::class)
        private fun parseWithGson(content: String): List<Student> {
            val gson = Gson()
            val studentListType = object : TypeToken<List<Student>>() {}.type
            return gson.fromJson<ArrayList<Student>>(content, studentListType)
        }

    }

}
