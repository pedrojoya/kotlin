package es.iessaladillo.pedrojoya.pr016.data.local

import es.iessaladillo.pedrojoya.pr016.data.local.model.Student
import java.util.*

object Database {

    private val students: Map<String, ArrayList<Student>> = mapOf(
            "CFGM Sistemas Microinformáticos y Redes" to arrayListOf(
                    Student("Baldomero", 16, "CFGM", "2º"),
                    Student("Sergio", 27, "CFGM", "1º"),
                    Student("Atanasio", 17, "CFGM", "1º"),
                    Student("Oswaldo", 26, "CFGM", "1º"),
                    Student("Rodrigo", 22, "CFGM", "2º"),
                    Student("Antonio", 16, "CFGM", "1º")
            ),
            "CFGS Desarrollo de Aplicaciones Multiplataforma" to arrayListOf(
                    Student("Pedro", 22, "CFGS", "2º"),
                    Student("Pablo", 22, "CFGS", "2º"),
                    Student("Rodolfo", 21, "CFGS", "1º"),
                    Student("Gervasio", 24, "CFGS", "2º"),
                    Student("Prudencia", 20, "CFGS", "2º"),
                    Student("Gumersindo", 17, "CFGS", "2º"),
                    Student("Gerardo", 18, "CFGS", "1º"),
                    Student("Óscar", 21, "CFGS", "2º")
            )
    )

    fun queryStudents() = students

}
