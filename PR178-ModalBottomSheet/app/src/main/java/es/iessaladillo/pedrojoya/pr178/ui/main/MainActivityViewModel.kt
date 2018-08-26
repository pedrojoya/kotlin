package es.iessaladillo.pedrojoya.pr178.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr178.data.Repository

class MainActivityViewModel(private val repository: Repository): ViewModel(), Repository by repository {

    val students by lazy { repository.queryStudents() }

}
