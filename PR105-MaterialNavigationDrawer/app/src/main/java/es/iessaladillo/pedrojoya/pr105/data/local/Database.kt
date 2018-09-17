package es.iessaladillo.pedrojoya.pr105.data.local

import com.mooveit.library.Fakeit
import es.iessaladillo.pedrojoya.pr105.data.local.model.Student
import java.util.*

private const val BASE_URL = "https://picsum.photos/100/100?image="

object Database {

    private val random: Random = Random()
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

    private fun newFakeStudent() =
            Student(Fakeit.name().name(),
                    Fakeit.address().streetAddress(),
                    "$BASE_URL${random.nextInt(180)}")

}
