package pedrojoya.iessaladillo.es.pr227.data.local

import com.mooveit.library.Fakeit
import pedrojoya.iessaladillo.es.pr227.data.Repository
import pedrojoya.iessaladillo.es.pr227.data.local.model.Student
import java.util.*

private const val BASE_URL = "https://picsum.photos/100/100?image="

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
                "$BASE_URL${Random().nextInt(1084)}")
