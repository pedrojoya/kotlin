package es.iessaladillo.pedrojoya.pr012.data

interface Repository {

    fun queryStudents(): List<Student>

}

class RepositoryImpl (private val database: Database) : Repository {

    override fun queryStudents() = database.queryStudents()

}

