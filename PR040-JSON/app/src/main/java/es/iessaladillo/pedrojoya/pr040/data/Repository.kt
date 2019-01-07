package es.iessaladillo.pedrojoya.pr040.data

import es.iessaladillo.pedrojoya.pr040.base.Call
import es.iessaladillo.pedrojoya.pr040.base.Resource
import es.iessaladillo.pedrojoya.pr040.data.model.Student

interface Repository {
    fun queryStudents(): Call<Resource<List<Student>>>
}
