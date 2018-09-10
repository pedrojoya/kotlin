package es.iessaladillo.pedrojoya.pr195.data.remote

import es.iessaladillo.pedrojoya.pr195.data.model.Student
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @get:GET("datos.json")
    val students: Observable<List<Student>>

}
