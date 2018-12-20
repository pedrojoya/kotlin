package es.iessaladillo.pedrojoya.pr132.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr132.data.Repository

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {

    val students: LiveData<List<String>> = repository.queryStudents()

    fun deleteStudent(student: String) {
        repository.deleteStudent(student)
    }

}
