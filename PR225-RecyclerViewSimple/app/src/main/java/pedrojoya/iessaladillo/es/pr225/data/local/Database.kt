package pedrojoya.iessaladillo.es.pr225.data.local

import com.mooveit.library.Fakeit
import pedrojoya.iessaladillo.es.pr225.data.Repository
import pedrojoya.iessaladillo.es.pr225.data.local.model.Student
import java.util.*

private const val BASE_URL = "https://picsum.photos/200/300?image="

object Database: Repository {

    private val students = ArrayList<Student>()
    private val random: Random = Random()

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
                "$BASE_URL${random.nextInt(180)}")
        students.add(student)
    }

}
