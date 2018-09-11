package es.iessaladillo.pedrojoya.pr048.data.local

import es.iessaladillo.pedrojoya.pr048.data.local.model.Student
import java.util.*

object Database {

    private val random = Random()
    val students = ArrayList<Student>().apply {
                for (i in 0..4) {
                    add(Student(
                            "Student $i",
                            "c/ Address, nº $i",
                            "https://picsum.photos/200/300?image=${random.nextInt(1084)}"))
                }
            }

}