package es.iessaladillo.pedrojoya.pr011.data

interface Repository {

    fun getStudents(): MutableList<String>
    fun addStudent(student: String)
    fun deleteStudent(position: Int)

}

object RepositoryImpl : Repository {

    override fun getStudents() = Database.getStudents()

    override fun addStudent(student: String) {
        Database.addStudent(student)
    }

    override fun deleteStudent(position: Int) {
        Database.deleteStudent(position)
    }

}