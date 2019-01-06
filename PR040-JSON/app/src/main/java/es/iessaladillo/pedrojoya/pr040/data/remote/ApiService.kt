package es.iessaladillo.pedrojoya.pr040.data.remote

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr040.base.Resource
import es.iessaladillo.pedrojoya.pr040.data.model.Student

interface ApiService {

   fun getStudents(): LiveData<Resource<List<Student>>>

}
