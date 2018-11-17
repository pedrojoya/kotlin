package es.iessaladillo.pedrojoya.pr249.data.local

import java.util.*

object Database {

    private val students = ArrayList<String>()

    init {
        insertInitialData()
    }

    private fun insertInitialData() {
        students.addAll(
                Arrays.asList("Baldomero", "Sergio", "Pablo", "Rodolfo", "Atanasio", "Gervasio",
                        "Prudencia", "Oswaldo", "Gumersindo", "Gerardo", "Rodrigo", "Óscar",
                        "Filomeno", "Fulgencio", "Ambrosio"))
    }

    fun queryStudents(): List<String> = ArrayList(students)

}
