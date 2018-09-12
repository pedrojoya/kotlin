package es.iessaladillo.pedrojoya.pr212.data

import es.iessaladillo.pedrojoya.pr212.data.local.model.Student

interface Repository {

    fun getStudents(): List<Student>
    fun getStudent(studentId: Long): Student?
    fun addStudent(student: Student): Long
    fun updateStudent(student: Student): Boolean
    fun deleteStudent(studentId: Long): Boolean
    fun onDestroy()

}
