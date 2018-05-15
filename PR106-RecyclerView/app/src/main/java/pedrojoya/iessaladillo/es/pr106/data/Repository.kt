package pedrojoya.iessaladillo.es.pr106.data

interface Repository {

    fun queryStudents(): List<Student>

    fun addStudent(student: Student)

    fun deleteStudent(student: Student)

}

class RepositoryImpl internal constructor(private val database: Database)
    : Repository by database

