package es.iessaladillo.pedrojoya.pr012.data

import es.iessaladillo.pedrojoya.pr016.data.local.model.Student

interface Repository {

    fun queryStudents(): Map<String, ArrayList<Student>>

}
