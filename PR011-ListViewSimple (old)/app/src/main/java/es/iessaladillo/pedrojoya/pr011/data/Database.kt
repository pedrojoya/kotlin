package es.iessaladillo.pedrojoya.pr011.data

object Database {

    private val students = mutableListOf<String>()

    fun getStudents() = students

    fun addStudent(student: String) {
        students.add(student)
    }

    fun deleteStudent(position: Int) {
        students.removeAt(position)
    }

}