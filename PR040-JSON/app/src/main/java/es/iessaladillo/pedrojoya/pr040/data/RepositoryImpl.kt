package es.iessaladillo.pedrojoya.pr040.data

import es.iessaladillo.pedrojoya.pr040.base.Call
import es.iessaladillo.pedrojoya.pr040.base.Resource
import es.iessaladillo.pedrojoya.pr040.data.model.Student
import es.iessaladillo.pedrojoya.pr040.data.remote.ApiService

class RepositoryImpl(private val apiservice: ApiService) : Repository {

    override fun queryStudents(): Call<Resource<List<Student>>> {
        return apiservice.getStudents()
    }

}
