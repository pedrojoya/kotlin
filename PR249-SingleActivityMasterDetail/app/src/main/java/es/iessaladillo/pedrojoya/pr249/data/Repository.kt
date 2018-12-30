package es.iessaladillo.pedrojoya.pr249.data

import androidx.lifecycle.LiveData

interface Repository {

    fun queryStudents(): LiveData<List<String>>

}
