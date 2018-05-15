package es.iessaladillo.pedrojoya.pr014.data

object Database {

    private val students = mutableListOf<Student>(Student("Pedro", 22, "CFGS", "2"),
            Student("Baldomero", 16, "CFGM", "2º"), Student("Sergio", 27, "CFGM", "1º"),
            Student("Pablo", 22, "CFGS", "2º"), Student("Rodolfo", 21, "CFGS", "1º"),
            Student("Atanasio", 17, "CFGM", "1º"),
            Student("Gervasio", 24, "CFGS", "2º"),
            Student("Prudencia", 20, "CFGS", "2º"),
            Student("Oswaldo", 26, "CFGM", "1º"),
            Student("Gumersindo", 17, "CFGS", "2º"),
            Student("Gerardo", 18, "CFGS", "1º"), Student("Rodrigo", 22, "CFGM", "2º"),
            Student("Óscar", 21, "CFGS", "2º"))

    fun getStudents() = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun deleteStudent(position: Int) {
        students.removeAt(position)
    }

}