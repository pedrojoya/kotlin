package es.iessaladillo.pedrojoya.pr178.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mooveit.library.Fakeit
import es.iessaladillo.pedrojoya.pr178.data.Repository
import es.iessaladillo.pedrojoya.pr178.data.local.model.Student
import java.util.*

private const val BASE_URL = "https://picsum.photos/100/100?image="

object Database: Repository {

    private val random = Random()

    private val students = ArrayList<Student>()
    private val studentsLiveData = MutableLiveData<List<Student>>()
    private var studentsAutoId: Long = 0

    init {
        insertInitialData()
    }

    private fun insertInitialData() {
        for (i in 0..4) {
            insertStudent(newFakeStudent())
        }
    }

    override fun queryStudents(): LiveData<List<Student>> {
        return studentsLiveData
    }

    override fun insertStudent(student: Student) {
        student.id = ++studentsAutoId
        students.add(student)
        updateLiveData()
    }

    private fun updateLiveData() {
        studentsLiveData.value = students.sortedBy { it.name }
    }

    override fun deleteStudent(student: Student) {
        students.remove(student)
        updateLiveData()
    }

    fun newFakeStudent(): Student {
        return Student(0, Fakeit.name().name(), Fakeit.address().streetAddress(),
                BASE_URL + random.nextInt(1084))
    }

}