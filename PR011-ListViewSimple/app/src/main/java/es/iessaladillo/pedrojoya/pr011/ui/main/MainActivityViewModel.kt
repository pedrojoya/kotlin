package es.iessaladillo.pedrojoya.pr011.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr011.data.Repository

class MainActivityViewModel(private val repository: Repository) :
        ViewModel() {

    val data by lazy { repository.queryStudents() }

    fun addStudent(student: String) {
        repository.addStudent(student)
    }

    fun deleteStudent(student: String) {
        repository.deleteStudent(student)
    }

}
