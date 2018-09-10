package es.iessaladillo.pedrojoya.pr212.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr212.data.Repository
import es.iessaladillo.pedrojoya.pr212.data.model.Student

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {

    private var students: List<Student>? = null

    fun getStudents(forceLoad: Boolean): List<Student> {
        if (forceLoad) {
            students = repository.getStudents()
        }
        return students!!
    }

}
