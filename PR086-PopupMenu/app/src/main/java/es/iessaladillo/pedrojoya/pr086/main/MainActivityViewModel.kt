package es.iessaladillo.pedrojoya.pr086.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr086.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr086.data.Student

class MainActivityViewModel(repository: RepositoryImpl) : ViewModel() {

    val students: List<Student> by lazy { repository.queryStudents() }

}
