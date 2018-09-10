package es.iessaladillo.pedrojoya.pr211.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import es.iessaladillo.pedrojoya.pr211.data.Repository
import es.iessaladillo.pedrojoya.pr211.data.model.Student

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {

    private var students: LiveData<List<Student>>? = null

    fun getStudents(): LiveData<List<Student>> {
        if (students == null) {
            students = repository.getStudents()
        }
        return students as LiveData<List<Student>>
    }

}
