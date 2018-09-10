package es.iessaladillo.pedrojoya.pr195.data

import es.iessaladillo.pedrojoya.pr195.data.model.Student
import io.reactivex.Observable

interface Repository {
    fun getStudents(): Observable<List<Student>>
}
