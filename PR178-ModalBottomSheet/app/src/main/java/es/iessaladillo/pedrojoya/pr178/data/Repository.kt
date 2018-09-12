package es.iessaladillo.pedrojoya.pr178.data

import es.iessaladillo.pedrojoya.pr178.data.local.model.Student

interface Repository {

    fun queryStudents(): List<Student>

    fun addStudent(student: Student)

    fun deleteStudent(student: Student)

}
