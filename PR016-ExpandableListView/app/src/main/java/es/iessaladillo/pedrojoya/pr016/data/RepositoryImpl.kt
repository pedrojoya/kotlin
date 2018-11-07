package es.iessaladillo.pedrojoya.pr016.data

import es.iessaladillo.pedrojoya.pr016.data.local.Database

class RepositoryImpl(private val database: Database) : Repository {

    override fun queryStudents() = database.queryStudents()

}
