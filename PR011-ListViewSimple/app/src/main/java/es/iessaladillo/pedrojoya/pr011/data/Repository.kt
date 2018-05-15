package es.iessaladillo.pedrojoya.pr011.data

interface Repository {

    fun queryStudents(): List<String>
    fun addStudent(student: String)
    fun deleteStudent(position: Int)

}

class RepositoryImpl(private val database: Database) : Repository {

    override fun queryStudents(): List<String> {
        return database.queryStudents()
    }

    override fun addStudent(student: String) {
        database.addStudent(student)
    }

    override fun deleteStudent(position: Int) {
        database.deleteStudent(position)
    }

}

