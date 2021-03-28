package pedrojoya.iessaladillo.es.pr230.data.local

import com.mooveit.library.Fakeit
import pedrojoya.iessaladillo.es.pr230.data.Repository
import pedrojoya.iessaladillo.es.pr230.data.local.model.Student
import java.util.*

const val BASE_URL = "https://picsum.photos/100/100?image="

object Database: Repository {

    private val students = ArrayList<Student>()
    private val random: Random = Random()
    private var autonumeric = 1

    init {
        for (i in 0..4) {
            students.add(newFakeStudent())
        }
    }

    override fun queryStudents(): List<Student> {
        return ArrayList(students)
    }

    private fun newFakeStudent(): Student {
        val num = autonumeric++
        return Student(num.toLong(), Fakeit.name().name(), Fakeit.address().streetAddress(),
                "$BASE_URL${random.nextInt(1084)}")
    }

}
