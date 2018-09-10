package es.iessaladillo.pedrojoya.pr016.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr012.data.Repository

class MainActivityViewModel(private val repository: Repository): ViewModel() {

    val students = repository.queryStudents()

}