package es.iessaladillo.pedrojoya.pr086.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mooveit.library.Fakeit
import es.iessaladillo.pedrojoya.pr086.data.Repository
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import java.util.*

object Database : Repository {

    private const val BASE_URL = "https://picsum.photos/200/300?image="
    private val random = Random()
    private val students = ArrayList<Student>()
    private val studentsLiveData = MutableLiveData<List<Student>>()
    private var studentsAutoId: Long = 0

    init {
        insertInitialData()
    }

    fun newFakeStudent(): Student {
        return Student(0, Fakeit.name().name(), Fakeit.address().streetAddress(),
                Fakeit.phone().formats(), Fakeit.gameOfThrones().house(), BASE_URL + random.nextInt(1084),
                random.nextInt(10) + 16, random.nextBoolean())
    }

    private fun insertInitialData() {
        for (i in 0..9) {
            insertStudent(newFakeStudent())
        }
    }

    override fun queryStudents(): LiveData<List<Student>> {
        return studentsLiveData
    }

    @Synchronized
    override fun insertStudent(student: Student) {
        student.id = ++studentsAutoId
        students.add(student)
        updateStudentsLiveData()
    }

    @Synchronized
    override fun deleteStudent(student: Student) {
        students.remove(student)
        updateStudentsLiveData()
    }

    private fun updateStudentsLiveData() {
        studentsLiveData.postValue(ArrayList(students).sortedBy { student -> student.name })
    }

}