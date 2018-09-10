package es.iessaladillo.pedrojoya.pr011.data

interface Repository {

    fun queryStudents(): List<String>
    fun addStudent(student: String)
    fun deleteStudent(position: Int)

}
