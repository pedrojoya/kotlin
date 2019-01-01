package es.iessaladillo.pedrojoya.pr105.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr105.data.local.Database
import es.iessaladillo.pedrojoya.pr105.data.local.model.Student

class RepositoryImpl(private val database: Database) : Repository {

    override fun queryStudents(): LiveData<List<Student>> {
        return database.queryStudents()
    }

}
