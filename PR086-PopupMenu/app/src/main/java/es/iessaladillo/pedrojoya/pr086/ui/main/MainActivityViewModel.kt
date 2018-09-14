package es.iessaladillo.pedrojoya.pr086.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr086.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student

class MainActivityViewModel(private val repository: RepositoryImpl) : ViewModel() {

    private var students: List<Student>? = null

    fun getStudents(forceLoad: Boolean): List<Student> {
        if (students == null || forceLoad) {
            students = repository.queryStudents()
        }
        return students?: emptyList()
    }

}
