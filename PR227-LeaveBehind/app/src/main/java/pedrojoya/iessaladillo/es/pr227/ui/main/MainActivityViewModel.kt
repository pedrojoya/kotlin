package pedrojoya.iessaladillo.es.pr227.ui.main

import androidx.lifecycle.ViewModel
import pedrojoya.iessaladillo.es.pr227.data.Repository

class MainActivityViewModel(private val repository: Repository): ViewModel(), Repository by repository {

    val students by lazy { repository.queryStudents() }

}
