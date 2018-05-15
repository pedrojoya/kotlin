package pedrojoya.iessaladillo.es.pr106.data

import com.mooveit.library.Fakeit
import java.util.*

object Database: Repository {

    private val students = ArrayList<Student>()

    init {
        insertInitialData()
    }

    private fun insertInitialData() {
        for (i in 0..4) {
            students.add(newFakeStudent())
        }
    }

    override fun queryStudents() = students

    override fun addStudent(student: Student) {
        students.add(student)
    }

    override fun deleteStudent(student: Student) {
        students.remove(student)
    }

}

fun newFakeStudent() =
    Student(Fakeit.name().name(),
            Fakeit.address().streetAddress(),
            "http://lorempixel.com/100/100/abstract/" + (Random().nextInt(10) + 1).toString() + "/")
