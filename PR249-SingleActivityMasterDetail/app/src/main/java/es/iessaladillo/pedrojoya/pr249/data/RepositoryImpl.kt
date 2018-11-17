package es.iessaladillo.pedrojoya.pr249.data

import es.iessaladillo.pedrojoya.pr249.data.local.Database

class RepositoryImpl(private val database: Database) : Repository {

    override fun queryStudents(): List<String> = database.queryStudents()

}
