package es.iessaladillo.pedrojoya.pr057.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr057.data.local.Database
import es.iessaladillo.pedrojoya.pr057.data.local.model.Student

class RepositoryImpl(private val database: Database) : Repository {

    override fun queryStudents(): LiveData<List<Student>> = database.queryStudents()

    override fun insertStudent(student: Student) = database.insertStudent(student)

    override fun deleteStudent(student: Student) = database.deleteStudent(student)

}
