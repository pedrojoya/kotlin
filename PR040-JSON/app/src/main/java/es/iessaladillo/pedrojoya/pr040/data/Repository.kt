package es.iessaladillo.pedrojoya.pr040.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr040.base.Resource
import es.iessaladillo.pedrojoya.pr040.data.model.Student

interface Repository {
    fun queryStudents(): LiveData<Resource<List<Student>>>
}
