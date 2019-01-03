package es.iessaladillo.pedrojoya.pr178.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr178.data.Repository
import es.iessaladillo.pedrojoya.pr178.data.local.model.Student

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {

    val students: LiveData<List<Student>> = this.repository.queryStudents()

    fun deleteStudent(student: Student) {
        repository.deleteStudent(student)
    }

    fun addStudent(student: Student) {
        repository.insertStudent(student)
    }

}
