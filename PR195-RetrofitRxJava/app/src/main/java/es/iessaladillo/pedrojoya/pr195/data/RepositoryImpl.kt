package es.iessaladillo.pedrojoya.pr195.data

import es.iessaladillo.pedrojoya.pr195.data.model.Student
import es.iessaladillo.pedrojoya.pr195.data.remote.Api
import io.reactivex.Observable

class RepositoryImpl(private val api: Api) : Repository {

    override fun getStudents(): Observable<List<Student>> = api.students

}
