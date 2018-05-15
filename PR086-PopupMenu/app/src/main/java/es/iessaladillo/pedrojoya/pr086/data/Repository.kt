package es.iessaladillo.pedrojoya.pr086.data

interface Repository {

    fun queryStudents(): List<Student>

}

class RepositoryImpl(private val database: Database) : Repository by database

