package es.iessaladillo.pedrojoya.pr012.data

import es.iessaladillo.pedrojoya.pr012.data.local.model.Student

interface Repository {

    fun queryStudents(): List<Student>

}
