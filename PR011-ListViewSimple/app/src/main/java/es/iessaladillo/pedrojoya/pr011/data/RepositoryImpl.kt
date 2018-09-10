package es.iessaladillo.pedrojoya.pr011.data

import es.iessaladillo.pedrojoya.pr011.data.local.Database

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
