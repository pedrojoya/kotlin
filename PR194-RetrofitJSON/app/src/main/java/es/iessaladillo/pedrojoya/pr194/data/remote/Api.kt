package es.iessaladillo.pedrojoya.pr194.data.remote

import es.iessaladillo.pedrojoya.pr194.data.model.Student
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @get:GET("datos.json")
    val students: Call<List<Student>>

}
