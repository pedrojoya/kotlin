package pedrojoya.iessaladillo.es.pr201.data

interface Repository {

    fun queryStudents(order: Int = 1): List<Student>

    fun addStudent(student: Student)

    fun deleteStudent(student: Student)

    fun updateStudent(student: Student, newStudent: Student)

}

class RepositoryImpl internal constructor(private val database: Database)
    : Repository by database

