package pedrojoya.iessaladillo.es.pr230.data

import pedrojoya.iessaladillo.es.pr230.data.local.model.Student

interface Repository {

    fun queryStudents(): List<Student>

}
