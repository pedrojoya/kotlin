package es.iessaladillo.pedrojoya.pr249.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr249.data.local.Database

class RepositoryImpl(private val database: Database) : Repository {

    override fun queryStudents(): LiveData<List<String>> = database.queryStudents()

}
