package es.iessaladillo.pedrojoya.pr012.data

import es.iessaladillo.pedrojoya.pr012.R

object Database {

    private val students = mutableListOf(
            Student(R.drawable.foto1,
                    "Dolores Fuertes de Barriga", 22, "CFGS DAM", "2º", true),
            Student(R.drawable.foto2, "Baldomero LLégate Ligero", 17, "CFGM SMR", "2º", true),
            Student(R.drawable.foto3, "Jorge Javier Jiménez Jaén", 36,
                    "CFGM SMR", "1º", false),
    Student(R.drawable.foto4, "Fabián Gonzáles Palomino", 67,
    "CFGS DAM", "1º", false))

    fun getStudents() = students

}