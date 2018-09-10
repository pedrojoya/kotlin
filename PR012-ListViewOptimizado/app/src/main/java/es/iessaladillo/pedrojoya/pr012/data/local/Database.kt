package es.iessaladillo.pedrojoya.pr012.data.local

import es.iessaladillo.pedrojoya.pr012.R
import es.iessaladillo.pedrojoya.pr012.data.local.model.Student
import java.util.*

object Database {

    private val students: ArrayList<Student> = arrayListOf(
            Student(R.drawable.photo1,
                    "Dolores Fuertes de Barriga", 22, "CFGS DAM", "2º", true),
            Student(R.drawable.photo2, "Baldomero LLégate Ligero", 17,
                    "CFGM SMR", "2º", true),
            Student(R.drawable.photo3, "Jorge Javier Jiménez Jaén", 36,
                    "CFGM SMR", "1º", false),
            Student(R.drawable.photo4, "Fabián Gonzáles Palomino", 67,
                    "CFGS DAM", "1º", false),
            Student(R.drawable.photo, "Manuela González Cuevas", 17,
                    "CFGM SMR", "1º", true),
            Student(R.drawable.photo, "Rodolfo Valentino Martínez", 54,
                    "CFGS DAM", "2º", false),
            Student(R.drawable.photo, "Faemino López Acosta", 36,
                    "CFGM SMR", "2º", true))

    fun queryStudents() = students
}
