package es.iessaladillo.pedrojoya.pr211.data

import androidx.lifecycle.LiveData

import es.iessaladillo.pedrojoya.pr211.data.local.model.Student

interface Repository {

    fun queryStudents(): LiveData<List<Student>>
    fun queryStudent(studentId: Long): LiveData<Student>
    fun insertStudent(student: Student)
    fun updateStudent(student: Student)
    fun deleteStudent(student: Student)

}
