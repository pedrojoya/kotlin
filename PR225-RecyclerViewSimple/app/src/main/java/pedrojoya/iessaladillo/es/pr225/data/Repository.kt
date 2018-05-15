package pedrojoya.iessaladillo.es.pr225.data

interface Repository {

    fun queryStudents(): List<Student>

}

class RepositoryImpl internal constructor(private val database: Database) : Repository {

    override fun queryStudents() = database.queryStudents()

}

