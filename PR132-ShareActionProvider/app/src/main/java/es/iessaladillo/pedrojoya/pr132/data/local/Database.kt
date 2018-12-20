package es.iessaladillo.pedrojoya.pr132.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Database {

    private val students = ArrayList<String>()
    private val studentsLiveData = MutableLiveData<List<String>>()

    init {
        insertInitialData()
        refreshStudentsLiveData()
    }

    private fun insertInitialData() {
        students.addAll(
                Arrays.asList("Baldomero", "Sergio", "Pablo", "Rodolfo", "Atanasio", "Gervasio",
                        "Prudencia", "Oswaldo", "Gumersindo", "Gerardo", "Rodrigo", "Óscar"))
    }

    fun queryStudents(): LiveData<List<String>> {
        return studentsLiveData
    }

    private fun refreshStudentsLiveData() {
        studentsLiveData.postValue(ArrayList(students))
    }

    fun deleteStudent(student: String) {
        students.remove(student)
        refreshStudentsLiveData()
    }

}
