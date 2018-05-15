package es.iessaladillo.pedrojoya.pr012.data

interface Repository {
    fun getStudents(): MutableList<Student>
}

object RespositoryImpl : Repository {
    override fun getStudents() = Database.getStudents()
}