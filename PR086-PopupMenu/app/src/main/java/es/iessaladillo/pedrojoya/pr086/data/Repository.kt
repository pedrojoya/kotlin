package es.iessaladillo.pedrojoya.pr086.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student

interface Repository {

    fun queryStudents(): LiveData<List<Student>>
    fun insertStudent(student: Student)
    fun deleteStudent(student: Student)

}
