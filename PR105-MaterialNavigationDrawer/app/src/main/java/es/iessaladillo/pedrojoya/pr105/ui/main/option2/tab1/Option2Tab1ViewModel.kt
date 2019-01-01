package es.iessaladillo.pedrojoya.pr105.ui.main.option2.tab1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr105.data.Repository
import es.iessaladillo.pedrojoya.pr105.data.local.model.Student

class Option2Tab1ViewModel(repository: Repository) : ViewModel() {

    val students: LiveData<List<Student>> = repository.queryStudents()

}
