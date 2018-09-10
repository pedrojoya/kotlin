package pedrojoya.iessaladillo.es.pr201.data

import androidx.lifecycle.LiveData
import pedrojoya.iessaladillo.es.pr201.data.local.model.Student

interface Repository {

    fun queryStudents(): LiveData<List<Student>>

    fun addStudent(student: Student)

    fun deleteStudent(student: Student)

    fun updateStudent(student: Student, newStudent: Student)

}
