package es.iessaladillo.pedrojoya.pr086.main

import android.arch.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr086.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr086.data.Student

class MainActivityViewModel(repository: RepositoryImpl) : ViewModel() {

    internal val students: List<Student> by lazy { repository.queryStudents() }

}
