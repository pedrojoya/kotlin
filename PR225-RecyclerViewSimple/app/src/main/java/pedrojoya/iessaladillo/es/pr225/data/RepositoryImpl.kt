package pedrojoya.iessaladillo.es.pr225.data

import pedrojoya.iessaladillo.es.pr225.data.local.Database

class RepositoryImpl internal constructor(private val database: Database) : Repository {

    override fun queryStudents() = database.queryStudents()

}
