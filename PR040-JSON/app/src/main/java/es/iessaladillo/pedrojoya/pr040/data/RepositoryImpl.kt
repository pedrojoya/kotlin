package es.iessaladillo.pedrojoya.pr040.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr040.base.Resource
import es.iessaladillo.pedrojoya.pr040.data.model.Student
import es.iessaladillo.pedrojoya.pr040.data.remote.ApiService

class RepositoryImpl(private val apiservice: ApiService) : Repository {

    override fun queryStudents(): LiveData<Resource<List<Student>>> {
        return apiservice.getStudents()
    }

}
