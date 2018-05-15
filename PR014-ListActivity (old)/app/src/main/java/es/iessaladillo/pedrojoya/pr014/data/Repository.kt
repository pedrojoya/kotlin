package es.iessaladillo.pedrojoya.pr014.data

interface Repository {

    fun getStudents(): MutableList<Student>
    fun addStudent(student: Student)
    fun deleteStudent(position: Int)

}

object RepositoryImpl : Repository {

    override fun getStudents() = Database.getStudents()

    override fun addStudent(student: Student) {
        Database.addStudent(student)
    }

    override fun deleteStudent(position: Int) {
        Database.deleteStudent(position)
    }

}