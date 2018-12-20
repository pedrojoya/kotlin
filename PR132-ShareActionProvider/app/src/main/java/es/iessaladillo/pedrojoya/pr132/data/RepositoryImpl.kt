package es.iessaladillo.pedrojoya.pr132.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr132.data.local.Database

class RepositoryImpl(private val database: Database) : Repository {

    override fun queryStudents(): LiveData<List<String>> = database.queryStudents()

    override fun deleteStudent(student: String) {
        database.deleteStudent(student)
    }

}
