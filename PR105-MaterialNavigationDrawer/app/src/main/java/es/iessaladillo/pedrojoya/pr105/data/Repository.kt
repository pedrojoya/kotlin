package es.iessaladillo.pedrojoya.pr105.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr105.data.local.model.Student

interface Repository {

    fun queryStudents(): LiveData<List<Student>>

}
