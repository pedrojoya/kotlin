package es.iessaladillo.pedrojoya.pr057.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr057.data.Repository
import es.iessaladillo.pedrojoya.pr057.data.local.model.Student

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {

    val students: LiveData<List<Student>> = repository.queryStudents()

    fun addStudent(student: Student) {
        repository.insertStudent(student)
    }

    fun deleteStudent(student: Student) {
        repository.deleteStudent(student)
    }

}
