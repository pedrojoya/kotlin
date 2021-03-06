package pedrojoya.iessaladillo.es.pr229.data

import androidx.lifecycle.LiveData
import pedrojoya.iessaladillo.es.pr229.data.local.model.Student

interface Repository {

    fun queryStudentsOrderedByName(desc: Boolean): LiveData<List<Student>>

    fun insertStudent(student: Student)

    fun deleteStudent(student: Student)

    fun updateStudent(student: Student, newStudent: Student)

}
