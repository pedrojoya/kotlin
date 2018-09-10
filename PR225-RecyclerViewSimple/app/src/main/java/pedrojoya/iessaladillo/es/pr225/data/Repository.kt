package pedrojoya.iessaladillo.es.pr225.data

import pedrojoya.iessaladillo.es.pr225.data.local.model.Student

interface Repository {

    fun queryStudents(): List<Student>

}
