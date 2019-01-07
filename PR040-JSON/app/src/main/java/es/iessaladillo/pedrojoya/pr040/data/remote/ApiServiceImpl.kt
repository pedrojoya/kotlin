package es.iessaladillo.pedrojoya.pr040.data.remote

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import es.iessaladillo.pedrojoya.pr040.base.Call
import es.iessaladillo.pedrojoya.pr040.base.Resource
import es.iessaladillo.pedrojoya.pr040.data.model.Student
import es.iessaladillo.pedrojoya.pr040.utils.NetworkUtils

private const val BASE_URL = "http://my-json-server.typicode.com/pedrojoya/jsonserver/"
private const val TIMEOUT = 5000

class ApiServiceImpl : ApiService {

    override fun getStudents(): Call<Resource<List<Student>>> =
        object: Call<Resource<List<Student>>>() {
            override fun doAsync() {
                postValue(Resource.loading())
                val content: String
                try {
                    // Simulate latency
                    Thread.sleep(2000)
                    content = NetworkUtils.loadUrl(BASE_URL + "students", TIMEOUT)
                    postValue(Resource.success(parseWithGson(content)))
                } catch (e: Exception) {
                    postValue(Resource.error(e))
                }
            }
        }

    @Throws(JsonSyntaxException::class)
    private fun parseWithGson(content: String): List<Student> {
        val gson = Gson()
        val studentListType = object : TypeToken<List<Student>>() {}.type
        return gson.fromJson<List<Student>>(content, studentListType)
    }

}
