package es.iessaladillo.pedrojoya.pr086.data

import es.iessaladillo.pedrojoya.pr086.data.local.model.Student

interface Repository {

    fun queryStudents(): List<Student>

}
