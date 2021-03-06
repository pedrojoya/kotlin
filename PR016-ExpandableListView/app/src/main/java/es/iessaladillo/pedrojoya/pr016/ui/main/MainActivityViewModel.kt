package es.iessaladillo.pedrojoya.pr016.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr016.data.Repository

class MainActivityViewModel(repository: Repository): ViewModel() {

    val students = repository.queryStudents()

}