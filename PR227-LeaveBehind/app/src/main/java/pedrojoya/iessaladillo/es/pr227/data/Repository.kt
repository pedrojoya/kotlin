package pedrojoya.iessaladillo.es.pr227.data

import pedrojoya.iessaladillo.es.pr227.data.local.model.Student

interface Repository {

    fun queryStudents(): List<Student>

    fun addStudent(student: Student)

    fun deleteStudent(student: Student)

}
