package es.iessaladillo.pedrojoya.pr086.data.local

import es.iessaladillo.pedrojoya.pr086.data.Repository
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student

object Database: Repository {

    private val students: ArrayList<Student> = arrayListOf(
            Student("Baldomero Llégate Ligero", "La casa de Baldomero", "956956956",
                    "2º CFGS DAM"),
            Student("Germán Ginés de todos los Santos", "La casa de Germán",
                    "678678678", "1º CFGS DAM"),
            Student("Dolores Fuertes de Barriga", "La casa de Dolores", "666666666",
                    "1º CFGM SMR"),
            Student("Jorge Javier Jiménez Jaén", "La casa de Jorge", "688688688",
                    "1º CFGM SMR"),
            Student("Fabián Gonzáles Palomino", "La casa de Fabián", "999999999",
                    "2º CFGM SMR")
    )

    override fun queryStudents() = students
}