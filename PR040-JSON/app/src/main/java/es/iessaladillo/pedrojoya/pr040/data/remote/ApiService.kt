package es.iessaladillo.pedrojoya.pr040.data.remote

import es.iessaladillo.pedrojoya.pr040.base.Call
import es.iessaladillo.pedrojoya.pr040.base.Resource
import es.iessaladillo.pedrojoya.pr040.data.model.Student

interface ApiService {

   fun getStudents(): Call<Resource<List<Student>>>

}
