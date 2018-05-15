package es.iessaladillo.pedrojoya.pr105.data.local

import com.mooveit.library.Fakeit
import java.util.*

object Database {

    private val students = ArrayList<Student>()

    init {
        insertInitialData()
    }

    private fun insertInitialData() {
        for (i in 0..12) {
            students.add(newFakeStudent())
        }
    }

     fun queryStudents() = students

}

fun newFakeStudent() =
    Student(Fakeit.name().name(),
            Fakeit.address().streetAddress(),
            "http://lorempixel.com/100/100/abstract/" + (Random().nextInt(10) + 1).toString() + "/")
