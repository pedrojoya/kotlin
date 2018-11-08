package pedrojoya.iessaladillo.es.pr201.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mooveit.library.Fakeit
import pedrojoya.iessaladillo.es.pr201.data.Repository
import pedrojoya.iessaladillo.es.pr201.data.local.model.Student
import java.util.*
import kotlin.collections.ArrayList

const val BASE_URL = "https://picsum.photos/100/100?image="

object Database: Repository {

    private val students: ArrayList<Student> = ArrayList()
    private val random: Random = Random()
    private val studentsLiveData: MutableLiveData<List<Student>> = MutableLiveData()
    private val studentsLiveDataDesc = MutableLiveData<List<Student>>()
    private var autonumeric: Int = 1

    init {
        updateLiveDatas()
        insertInitialData()
    }

    private fun updateLiveDatas() {
        studentsLiveData.postValue(orderByName(students, false))
        studentsLiveDataDesc.postValue(orderByName(students, true))
    }

    private fun insertInitialData() {
        for (i in 0..4) {
            insertStudent(newFakeStudent())
        }
    }

    private fun orderByName(students: List<Student>, desc: Boolean): List<Student> =
        if (desc)
            students.sortedByDescending { it.name }
        else
            students.sortedBy { it.name }

    override fun queryStudentsOrderedByName(desc: Boolean): LiveData<List<Student>> =
            if (desc) studentsLiveDataDesc else studentsLiveData

    override fun insertStudent(student: Student) {
        students.add(student)
        updateLiveDatas()
    }

    override fun deleteStudent(student: Student) {
        students.remove(student)
        updateLiveDatas()
    }

    override fun updateStudent(student: Student, newStudent: Student) {
        val index = students.indexOf(student)
        if (index >= 0) {
            students[index] = newStudent
        }
        updateLiveDatas()
    }

    fun newFakeStudent(): Student {
        val num = autonumeric++
        return Student(num, Fakeit.name().name(), Fakeit.address().streetAddress(),
                "$BASE_URL${random.nextInt(1084)}")
    }

}
