package es.iessaladillo.pedrojoya.pr132.data

import androidx.lifecycle.LiveData

interface Repository {

    fun queryStudents(): LiveData<List<String>>
    fun deleteStudent(student: String)

}
