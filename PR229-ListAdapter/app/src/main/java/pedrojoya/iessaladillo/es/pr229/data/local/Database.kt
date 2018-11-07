package pedrojoya.iessaladillo.es.pr229.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mooveit.library.Fakeit
import pedrojoya.iessaladillo.es.pr229.data.Repository
import pedrojoya.iessaladillo.es.pr229.data.local.model.Student
import java.util.*
import kotlin.collections.ArrayList

const val BASE_URL = "https://picsum.photos/100/100?image="

object Database: Repository {

    private val students: ArrayList<Student> = ArrayList()
    private val random: Random = Random()
    private val studentsLiveData: MutableLiveData<List<Student>> = MutableLiveData()
    private var autonumeric: Int = 1

    init {
        // Create initial students.
        for (i in 0..4) {
            students.add(newFakeStudent())
        }
    }

    override fun queryStudents(): LiveData<List<Student>> = studentsLiveData.apply { value =
            ArrayList(students) }

    @Synchronized
    override fun deleteStudent(student: Student) {
        students.remove(student)
        studentsLiveData.value = ArrayList(students)
    }

    @Synchronized
    override fun addStudent(student: Student) {
        students.add(student)
        studentsLiveData.value = ArrayList(students)
    }

    @Synchronized
    override fun updateStudent(student: Student, newStudent: Student) {
        val index = students.indexOf(student)
        if (index >= 0) {
            students[index] = newStudent
        }
        studentsLiveData.value = ArrayList(students)
    }

    fun newFakeStudent(): Student {
        val num = autonumeric++
        return Student(num, Fakeit.name().name(), Fakeit.address().streetAddress(),
                "$BASE_URL${random.nextInt(1084)}")
    }

}
