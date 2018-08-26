package es.iessaladillo.pedrojoya.pr211.data

import androidx.lifecycle.LiveData

import es.iessaladillo.pedrojoya.pr211.data.model.Student

interface Repository {

    fun getStudents(): LiveData<List<Student>>
    fun getStudent(studentId: Long): LiveData<Student>
    fun insertStudent(student: Student): Long
    fun updateStudent(student: Student): Int
    fun deleteStudent(student: Student): Int

}
