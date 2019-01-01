package es.iessaladillo.pedrojoya.pr105.data.local

import androidx.lifecycle.MutableLiveData
import com.mooveit.library.Fakeit
import es.iessaladillo.pedrojoya.pr105.data.local.model.Student
import java.util.*

private const val BASE_URL = "https://picsum.photos/100/100?image="

object Database {

    private val random: Random = Random()
    private val students = ArrayList<Student>()
    private val studentsLiveData: MutableLiveData<List<Student>> = MutableLiveData()
    private var studentsAutoId: Int = 0

    init {
        insertInitialData()
        updateLiveData()
    }

    private fun updateLiveData() {
        studentsLiveData.postValue(students.sortedBy { it.name })
    }

    private fun insertInitialData() {
        for (i in 0..15) {
            insertStudent(newFakeStudent())
        }
    }

    private fun insertStudent(student: Student) {
        student.id = (++studentsAutoId).toLong()
        students.add(student)
    }

    fun queryStudents() = studentsLiveData

    private fun newFakeStudent() =
            Student(0, Fakeit.name().name(),
                    Fakeit.address().streetAddress(),
                    "$BASE_URL${random.nextInt(180)}")

}
