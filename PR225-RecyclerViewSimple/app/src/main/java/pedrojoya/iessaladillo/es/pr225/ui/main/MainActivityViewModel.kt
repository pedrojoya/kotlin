package pedrojoya.iessaladillo.es.pr225.ui.main

import androidx.lifecycle.ViewModel
import pedrojoya.iessaladillo.es.pr225.data.Repository

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    val students by lazy { repository.queryStudents() }

}
