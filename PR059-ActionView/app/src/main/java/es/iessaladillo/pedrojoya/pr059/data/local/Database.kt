package es.iessaladillo.pedrojoya.pr059.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Database {

    private val students = ArrayList<String>()

    init {
        insertInitialData()
    }

    private fun insertInitialData() {
        students.addAll(
                Arrays.asList("Baldomero", "Sergio", "Pablo", "Rodolfo", "Atanasio", "Gervasio",
                        "Prudencia", "Oswaldo", "Gumersindo", "Gerardo", "Rodrigo", "Óscar"))
    }

    fun queryStudents(criteria: String): LiveData<List<String>> =
            MutableLiveData<List<String>>().apply {
                postValue(students.filter { student ->
                    student.toUpperCase().contains(criteria.toUpperCase())
                })
            }

}
