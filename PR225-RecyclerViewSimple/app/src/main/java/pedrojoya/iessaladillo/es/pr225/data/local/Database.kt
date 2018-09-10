package pedrojoya.iessaladillo.es.pr225.data.local

import com.mooveit.library.Fakeit
import pedrojoya.iessaladillo.es.pr225.data.Repository
import pedrojoya.iessaladillo.es.pr225.data.local.model.Student
import java.util.*

object Database: Repository {

    private val students = ArrayList<Student>()
    private var count = 0

    init {
        insertInitialData()
    }

    private fun insertInitialData() {
        for (i in 0..4) {
            addFakeStudent()
        }
    }

    override fun queryStudents() = students

    private fun addFakeStudent() {
        val student = Student(Fakeit.name().name(), Fakeit.address().streetAddress(),
                "http://lorempixel.com/100/100/abstract/" + (++count % 10 + 1) + "/")
        students.add(student)
    }

}
