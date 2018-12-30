package es.iessaladillo.pedrojoya.pr249.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.ArrayList

object Database {

    private val students = ArrayList<String>()
    private val studentsLiveData: MutableLiveData<List<String>> = MutableLiveData()

    init {
        insertInitialData()
        studentsLiveData.postValue(ArrayList<String>(students))
    }

    private fun insertInitialData() {
        students.addAll(
                Arrays.asList("Baldomero", "Sergio", "Pablo", "Rodolfo", "Atanasio", "Gervasio",
                        "Prudencia", "Oswaldo", "Gumersindo", "Gerardo", "Rodrigo", "Óscar",
                        "Filomeno", "Fulgencio", "Ambrosio"))
    }

    fun queryStudents(): LiveData<List<String>> = studentsLiveData

}
